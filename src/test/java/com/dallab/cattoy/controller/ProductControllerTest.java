package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.ProdcutService;
import com.dallab.cattoy.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdcutService productService;

    @Before
    public void mockProductService() {

        List<Product> products = new ArrayList<>();
        products.add(Product.builder().name("쥐이").build());

        given(productService.getProducts()).willReturn(products);
    }

    @Test
    public void list() throws Exception {

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("쥐이")));

        verify(productService).getProducts();
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(post("/products")
//               POST에서 JSON방식으로 던지기 아직 실제 컨트롤러에서는 받는게 없기 때문에 실제 컨트롤러는 하드코딩 되어있음.
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"낚시대\", \"maker\":\"달랩\", \"price\":5000}")
        )
                .andExpect(status().isCreated());

        verify(productService).addProduct("낚시대", "달랩", 5000);
    }
}
