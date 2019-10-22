package com.dallab.cattoy.application;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingServiceTest {
    private GreetingService greetingservice;

    @Before
    public void setUp() {
        greetingservice = new GreetingService();
    }

    // 이름이 없는 경우 다른 인사 메세지 다르게 처리
    @Test
    public void getMessage() throws Exception{

        assertThat(
                greetingservice.getMessage(null)
        ).isEqualTo("Hello");
    }

    // 이름이 있는 경우 다른 인사 메세지 다르게 처리
    @Test
    public void getMessageWithName() throws Exception{

        assertThat(
                greetingservice.getMessage("JOKER")
        ).isEqualTo("Hello, JOKER");
    }
}
