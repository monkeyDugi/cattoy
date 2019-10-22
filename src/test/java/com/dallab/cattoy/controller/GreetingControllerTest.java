package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
* 스프링 장점
* - 알아서 객체를 생성해주므로 편한다.(annotation)
* - MokcMvc는 스프링 컨트롤러 테스트하는 거
* */

@RunWith(SpringRunner.class)           // 스프링을 테스트 돌리는 거
@WebMvcTest(GreetingController.class)  // 해당 컨트롤러만 테스트가 가능하기 때문에, @SpyBean사용 : 해당 클래스 이외는 테스트를 못하기 때문에
public class GreetingControllerTest {

    @Autowired // new MockMvc 이거를 안해주는거
    private MockMvc mockMvc;

    @SpyBean
    private GreetingService greetingService;

    @Test
    public void hello() throws Exception {

        //GET /hello --> 200(OK), 404(NOT FOUND)
        //.param("name", "JOKER")) -> 파라미터로 JOKER를 넘겨준다.
        mockMvc.perform(get("/hello").param("name", "JOKER"))
                .andExpect(status().isOk()) // 200(OK)이 나와야 통과
                // 온전한 hello가 있는지
                //.andExpect(content().string("Hello"));
                // hello, JOKER가 포함되어 있는지 -> containsString(요안에 substring이 적용되기 때문에)
                .andExpect(content().string(containsString("Hello, JOKER")));
    }
}