package br.nicolas.remembering.controller;

import br.nicolas.remembering.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/api/v1/student")
@AllArgsConstructor
public class StudentController {

    private StudentService service;


}
