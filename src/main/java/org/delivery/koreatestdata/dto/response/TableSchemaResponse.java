package org.delivery.koreatestdata.dto.response;

import org.delivery.koreatestdata.dto.TableSchemaDto;

import java.util.List;

public record TableSchemaResponse(String SchemaName, String UserId, List<SchemaFieldResponse> schemaFields) {
    public static TableSchemaResponse fromDto(TableSchemaDto dto) {
        return new TableSchemaResponse(
                dto.schemaName(),
                dto.userId(),
                dto.schemaFields().stream().map(SchemaFieldResponse::fromDto).toList());
    }
}
