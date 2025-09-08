package org.delivery.koreatestdata.dto.response;

import org.delivery.koreatestdata.domain.constant.MockDataType;
import org.delivery.koreatestdata.dto.SchemaFieldDto;

public record SchemaFieldResponse(
        String fieldName,
        MockDataType mockDataType,
        Integer fieldOrder,
        Integer blankPercent,
        String typeOptionJson,
        String forceValue) {

    public static SchemaFieldResponse fromDto(SchemaFieldDto dto) {
        return new SchemaFieldResponse(
                dto.fieldName(),
                dto.mockDataType(),
                dto.fieldOrder(),
                dto.blankPercent(),
                dto.typeOptionJson(),
                dto.forceValue()
        );
    }
}
