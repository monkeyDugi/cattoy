package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.GreetingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
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
public class GreetingControllerTest {

    @Autowired // new MockMvc 이거를 안해주는거
    private MockMvc mockMvc;

    //@SpyBean // @WebMvcTest에서 지정한 클래스만 테스트가 가능하기 때문에,
             // @SpyBean사용 : 진짜 객체며, Controller에서 GreetingService를 포함하기 때문에 이렇게 사용해 주어야 한다.
             // 그렇지 않으면 Service를 통과히지 못한다.
    @MockBean // 가짜 객체, MockBean객체를 테스트 하기 위해서는 given을 사용해야 한다.
    private GreetingService greetingService;

    // MockBean은 가짜 객체로써 실제 Service객체를 생성하지 않고 통과만 시키는 것이다.
    // 즉, DB까지 연동 된다면, 실제 DB를 불러오지 않고 형태만 갖추고 있는 것이다. 그러면 DB까지 갈 이유가 없다면
    // 효율적인 테스팅 속도를 낼 수 있지 않을까?
    // 보는 바와 같이 JOKER11과 비교하는데 아무런 에러 없이 테스트가 통과된다. 실제 Service에서는 "JOKER"를 넘기면
    // Hello, JOKER를 반환한다.
    @Before
    public void mockGreetingService() {

        given(greetingService.getMessage(null)).willReturn("Hello");
        given(greetingService.getMessage("JOKER")).willReturn("Hello, JOKER11");
    }

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