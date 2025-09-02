package org.delivery.koreatestdata.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchemaField {
    private String fieldName;
    private String mockDataType;
    private Integer fieldOrder;
    private Integer blankPercent;

    private String typeJsonOption; // {"min", "max"}
    private String forceValue;

}
