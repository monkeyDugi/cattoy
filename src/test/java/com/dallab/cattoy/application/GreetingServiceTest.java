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

    @Test
    public void getMessage() {

        assertThat(
                greetingservice.getMessage()
        ).isEqualTo("Hello");
    }
}
