package org.delivery.koreatestdata.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.delivery.koreatestdata.domain.constant.MockDataType;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchemaField {
    private String fieldName;
    private MockDataType mockDataType;
    private Integer fieldOrder;
    private Integer blankPercent;

    private String typeJsonOption; // {"min", "max"}
    private String forceValue;

}
