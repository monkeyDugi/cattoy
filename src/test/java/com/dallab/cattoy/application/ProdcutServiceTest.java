package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.Product;
import com.dallab.cattoy.domain.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class ProdcutServiceTest {

    private ProdcutService prodcutService;

    /*
     * Repository는 전혀 테스트할 생각이 없기 때문에 @Mock사용, 컨트롤러에서는 MockBean사용이 가능하지만
     * 여기서는 불가능 하기 때문에 @Mock을 사용하고 @MockBean과는 다르게 초기화를 직접 해주어야 한다.
     */
    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        // 이렇게 직접 초기화를 해주어야 가짜객체 생성이 가능하다. @Mock붙은 this
        MockitoAnnotations.initMocks(this);
        prodcutService = new ProdcutService(productRepository);
    }

    @Test
    public void getProductsWithEmpty() throws Exception {
        List<Product> products = new ArrayList<>();

        given(productRepository.findAll()).willReturn(products);

        assertThat(prodcutService.getProducts()).isEmpty();
    }

    @Test
    public  void getProductsWithOneProduct() {
        List<Product> products = new ArrayList<>();

        products.add(Product.builder().name("쥐돌이").build());

        given(productRepository.findAll()).willReturn(products);

        assertThat(prodcutService.getProducts()).isNotEmpty();
    }

    @Test
    public void addProdcut() throws Exception {
        Product product = Product.builder()
                .name("쥐돌이")
                .maker("달랩")
                .price(5000)
                .build();

        prodcutService.addProduct(product);

        verify(productRepository).save(any());
    }
}
