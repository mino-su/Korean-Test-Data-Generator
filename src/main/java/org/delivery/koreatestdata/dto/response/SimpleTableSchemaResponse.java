package org.delivery.koreatestdata.dto.response;

import org.delivery.koreatestdata.dto.TableSchemaDto;

public record SimpleTableSchemaResponse(String schemaName, String userId) {
    public static SimpleTableSchemaResponse fromDto(TableSchemaDto dto) {
        return new SimpleTableSchemaResponse(dto.schemaName(), dto.userId());
    }
}
