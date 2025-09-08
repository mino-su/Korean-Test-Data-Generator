package org.delivery.koreatestdata.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.koreatestdata.domain.constant.ExportFileType;
import org.delivery.koreatestdata.dto.TableSchemaDto;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Data
public class TableSchemaExportRequest {
    private String schemaName;
    private Integer rowCount;
    private ExportFileType fileType;
    private List<SchemaFieldRequest> schemaFields;

    public TableSchemaDto toDto(String userId) {
        return TableSchemaDto.of(
                schemaName, userId, null, schemaFields.stream()
                        .map(SchemaFieldRequest::toDto)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }
}
