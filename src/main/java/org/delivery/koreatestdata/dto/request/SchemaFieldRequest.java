package org.delivery.koreatestdata.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.koreatestdata.domain.constant.MockDataType;
import org.delivery.koreatestdata.dto.SchemaFieldDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SchemaFieldRequest {

    private MockDataType mockDataType;
    private String fieldName;
    private Integer fieldOrder;
    private Integer blankPercent;
    private String typeOptionJson;
    private String forceValue;


    public static SchemaFieldRequest of(
            String fieldName,
            MockDataType mockDataType,
            Integer fieldOrder,
            Integer blankPercent,
            String typeOptionJson,
            String forceValue
    ) {
        return new SchemaFieldRequest(mockDataType, fieldName, fieldOrder, blankPercent, typeOptionJson, forceValue);
    }


    public SchemaFieldDto toDto() {
        return SchemaFieldDto.of(this.fieldName, this.mockDataType, this.fieldOrder, this.blankPercent, this.typeOptionJson, this.forceValue);
    }
}
