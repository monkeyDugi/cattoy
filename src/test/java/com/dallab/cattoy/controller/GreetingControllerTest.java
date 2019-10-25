package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.GreetingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
* 스프링 장점
* - 알아서 객체를 생성해주므로 편한다.(annotation)
* - MokcMvc는 스프링 컨트롤러 테스트하는 거
* */
/*
* MockBean은 기본적으로 테스트 단계를 3단계를 거친다.
* 1. given : 테스트 데이터 생성
* 2. when  : 어떤 테스트를 할지
* 3. then  : 테스트의 결과 검증
* */
@RunWith(SpringRunner.class)           // 스프링을 테스트 돌리는 거
@WebMvcTest(GreetingController.class)  // 해당 컨트롤러 테스트
@ActiveProfiles("test")
public class GreetingControllerTest {

    @Autowired // new MockMvc 이거를 안해주는거
    private MockMvc mockMvc;

    /*
    * @SpyBean이란
    * - Service가 Controller에 의존해 있는 테스트를 하기 때문에 사용한다??
    * - 진짜객체
    * */
    @SpyBean
    /*
    * @MockBean이란
    * - 가짜각체
    * - given으로 가짜객체를 생성하고, willReturn으로 리턴값을 개발자 마음대로 지정한다.
    * - 서버와 연결되어 있다고 가정할 때 서버가 다운 되었다면 Service 테스트를 할 수 없다면 어떻게 할까?
    *   -> 이게 MockBean의 사용이유이다. 실제 Service객체가 아닌 가짜로 만들어서 willReturn으로 개발자 마음대로 리턴값을
    *      정하여 테스트 할 수 있다. 실제 @Test에서 .andExpect(content().string(containsString("Hello"))); 이 내용은
    *      Controller에서 Service에 의해 리턴되는 값인데, MockBean으로 가짜 객체를 만들어 얼마든지 개발자 원하는 대로
    *      willRetrun을 사용하여 리턴 값을 반환하여 테스트 할 수 있게 된다. 즉, 실제 Service는 사용되지 않는 것!!
    *      given(greetingService.getMessage(null)).willReturn("Hello")에서 가짜 Service객체를 생성하여
    *      파라미터가 null인 테스트를 진행하는 hello() 테스트가 진행되고,
    *      .andExpect(content().string(containsString("Hello")));의 "Hello"가 통과가 되는 것이다!!
    *      이렇게 되면 서버가 다운 되어도 진짜 Service객체를 사용하지 않으므로
    *      테스트가 가능하게 된다. 즉, DB에 연결되어 있는 경우도 굳이 DB에서 데이터를 받아와 테스트 할 필요가 없을 때
    *      사용해되 될 것 같다.
    * */
    //@MockBean
    private GreetingService greetingService;

//    @Before
//    public void mockGreetingService() {
//
//        given(greetingService.getMessage(null)).willReturn("Hello");
//        given(greetingService.getMessage("JOKER")).willReturn("Hello, JOKER");
//    }

    @Test
    public void hello() throws Exception {

        //GET /hello --> 200(OK), 404(NOT FOUND)
        //.param("name", "JOKER")) -> 파라미터로 JOKER를 넘겨준다.
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk()) // 200(OK)이 나와야 통과
                // 온전한 hello가 있는지
                //.andExpect(content().string("Hello"));
                // hello, JOKER가 포함되어 있는지 -> containsString(요안에 substring이 적용되기 때문에)
                .andExpect(content().string(containsString("Hello")));
        // MockBean(가짜객체)의 getMessage() 메서드가 잘 실행된건지 확인
        //verify(greetingService).getMessage(null);
    }

    @Test
    public void helloWithName() throws Exception {

        //GET /hello --> 200(OK), 404(NOT FOUND)
        //.param("name", "JOKER")) -> 파라미터로 JOKER를 넘겨준다.
        mockMvc.perform(get("/hello").param("name", "JOKER"))
                .andExpect(status().isOk()) // 200(OK)이 나와야 통과
                .andExpect(content().string(containsString("Hello, JOKER")));
        //verify(greetingService).getMessage("JOKER");
    }
}