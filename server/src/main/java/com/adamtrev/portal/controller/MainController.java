package com.adamtrev.portal.controller;

import com.adamtrev.portal.apimodel.TestPojo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/tests")
    public TestPojo testsApi(@RequestParam(value = "name", defaultValue = "Trevor") final String name) {
        return TestPojo.builder()
                .name(name)
                .age(28)
                .build();
    }
}
