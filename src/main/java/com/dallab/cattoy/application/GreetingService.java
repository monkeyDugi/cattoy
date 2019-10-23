package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.Greeting;
import lombok.Setter;
import org.springframework.stereotype.Service;

// service는 실제 기능은 appication 부분이다. Service 레이어 부분
// @Service : 컨트롤러(UI)와 Serivce(Application)의 연동
@Service
public class GreetingService {

    public String getMessage(String name) {
        Greeting greeting = Greeting.builder()
                .name(name)
                .build();

        return greeting.getMessage();
    }
}
