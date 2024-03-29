package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.GreetingService;
import com.dallab.cattoy.dto.GreetingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/hello")
    public GreetingDto hello(
            // @RequestParam(defaultValue = "world") : param이 null 이면 "world" 있으면 name
            // @RequestParam(required = false) : null 아니야! 라고 강제
            @RequestParam(required = false) String name
    ) {
        GreetingDto greeting = new GreetingDto();
        greeting.setName("dugi");
        // Serivce의 메서드를 가져옴. 즉, 컨트롤러(UI)레이어에서 Service(Application)레이어를 사용
        greeting.setMessage(greetingService.getMessage(name));

        return greeting;
    }
}
