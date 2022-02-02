package com.adamtrev.portal.apimodel;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestDTO {
    private String firstName;
    private String lastName;
    private int age;
}
