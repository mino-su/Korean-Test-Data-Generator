package org.delivery.koreatestdata.dto.request;

import org.delivery.koreatestdata.domain.constant.MockDataType;
import org.delivery.koreatestdata.dto.SchemaFieldDto;

public record SchemaFieldRequest(
        MockDataType mockDataType,
        String fieldName,
        Integer fieldOrder,
        Integer blankPercent,
        String typeOptionJson,
        String forceValue
) {
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
