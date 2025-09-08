package org.delivery.koreatestdata.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.delivery.koreatestdata.domain.TableSchema;
import org.delivery.koreatestdata.domain.constant.ExportFileType;
import org.delivery.koreatestdata.domain.constant.MockDataType;
import org.delivery.koreatestdata.dto.request.TableSchemaExportRequest;
import org.delivery.koreatestdata.dto.request.TableSchemaRequest;
import org.delivery.koreatestdata.dto.response.SchemaFieldResponse;
import org.delivery.koreatestdata.dto.response.TableSchemaResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class TableSchemaController {

    private final ObjectMapper objectMapper;

    @GetMapping("/table-schema")
    public String tableSchema(Model model) {
        var tableSchema = defaultTableSchema();


        model.addAttribute("tableSchema", tableSchema);
        model.addAttribute("mockDataTypes", MockDataType.toObjects());
        model.addAttribute("fileTypes", Arrays.stream(ExportFileType.values()).toList());

        return "table-schema";
    }



    @PostMapping("/table-schema")
    public String createOrUpdateTableSchema(
            TableSchemaRequest tableSchemaRequest,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute(tableSchemaRequest);
        // addAttribute로 쓰면 query parameter로 노출되기때문에 addFlashAttributes()로 호출

        return "redirect:/table-schema";
    }

    @GetMapping("/table-schema/my-schemas")
    public String mySchemas() {
        return "my-schemas";
    }

    @PostMapping("/table-schema/my-schemas/{schemaName}")
    public String deleteMySchema(@PathVariable String schemaName, RedirectAttributes redirectAttributes) {
        redirectAttributes.getFlashAttributes();
        return "redirect:/my-schemas";
    }


    @GetMapping("/table-schema/export")
    public ResponseEntity<String> exportTableSchema(
            TableSchemaExportRequest tableSchemaExportRequest
    ) {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=table-schema.txt")
                .body(json(tableSchemaExportRequest));
    }

    private String json(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }

    }


    private static TableSchemaResponse defaultTableSchema() {
        return new TableSchemaResponse(
                "schema_name",
                "uno",
                List.of(
                        new SchemaFieldResponse("fieldName1", MockDataType.STRING, 1, 0, null, null),
                        new SchemaFieldResponse("fieldName2", MockDataType.NUMBER, 2, 10, null, null),
                        new SchemaFieldResponse("fieldName3", MockDataType.NAME, 3, 20, null, null)

                )
        );
    }
}
