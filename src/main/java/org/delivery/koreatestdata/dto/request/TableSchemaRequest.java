package org.delivery.koreatestdata.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.koreatestdata.dto.TableSchemaDto;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class TableSchemaRequest{

    private String schemaName;
    private String userId;
    private List<SchemaFieldRequest> schemaFields;


    public static TableSchemaRequest of(String schemaName, String userId, List<SchemaFieldRequest> schemaFields) {
        return new TableSchemaRequest(schemaName, userId, schemaFields);
    }

    public TableSchemaDto toDto() {
        return TableSchemaDto.of(schemaName, userId, null,
                schemaFields.stream().map(SchemaFieldRequest::toDto).
                        collect(java.util.stream.Collectors.toUnmodifiableSet()));
    }
}
