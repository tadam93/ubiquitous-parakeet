package com.adamtrev.portal.apimodel;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

@Builder
@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private int age;
    private DateTime lastModifiedDate;
    private Long recordVersion;
    private DateTime createdDate;
}
