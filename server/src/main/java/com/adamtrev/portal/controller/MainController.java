package com.adamtrev.portal.controller;

import com.adamtrev.portal.apimodel.TestDTO;
import com.adamtrev.portal.datamodel.TestPojo;
import com.adamtrev.portal.repository.TestPojoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class MainController {
    private TestPojoRepository testPojoRepository;

    @GetMapping("/test")
    public TestDTO testsApi(@RequestParam(value = "firstName", defaultValue = "Trevor") final String firstName,
                            @RequestParam(value = "lastName", defaultValue = "Malone") final String lastName) {
        testPojoRepository.create(TestPojo.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                .build());
        return TestDTO.builder()
                .age(28)
                .build();
    }
}
