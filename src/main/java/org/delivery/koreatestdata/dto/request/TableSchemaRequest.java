package org.delivery.koreatestdata.dto.request;

import org.delivery.koreatestdata.dto.TableSchemaDto;

import java.util.List;

public record TableSchemaRequest(
        String schemaName,
        String userId,
        List<SchemaFieldRequest> schemaFields
) {
    public static TableSchemaRequest of(String schemaName, String userId, List<SchemaFieldRequest> schemaFields) {
        return new TableSchemaRequest(schemaName, userId, schemaFields);
    }

    public TableSchemaDto toDto() {
        return TableSchemaDto.of(schemaName, userId, null,
                schemaFields.stream().map(SchemaFieldRequest::toDto).
                        collect(java.util.stream.Collectors.toUnmodifiableSet()));
    }
}
