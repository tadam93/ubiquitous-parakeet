package com.adamtrev.portal.apimodel;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestPojo {
    private String name;
    private int age;
}
