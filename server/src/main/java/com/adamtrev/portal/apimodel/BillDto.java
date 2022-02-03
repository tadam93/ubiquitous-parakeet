package com.adamtrev.portal.apimodel;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

@Builder
@Data
public class BillDto {
    private String companyName;
    private DateTime payableDate;
    private Double cost;
    private DateTime lastModifiedDate;
    private Long recordVersion;
    private DateTime createdDate;
}
