package org.delivery.koreatestdata.controller;

import org.delivery.koreatestdata.dto.request.TableSchemaRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class TableSchemaController {
    @GetMapping("/table-schema")
    public String tableSchema() {
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
            TableSchemaRequest tableSchemaRequest
    ) {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=table-schema.txt")
                .body("download complete!");
    }
}
