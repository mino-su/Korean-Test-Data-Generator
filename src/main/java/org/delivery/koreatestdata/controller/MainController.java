package org.delivery.koreatestdata.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.koreatestdata.repository.TableSchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainView() {
        return "forward:/table-schema";
    }



}
