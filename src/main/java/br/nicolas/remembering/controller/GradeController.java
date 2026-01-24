package br.nicolas.remembering.controller;

import br.nicolas.remembering.dto.student.GradeDTO;
import br.nicolas.remembering.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/api/v1/grade")
@AllArgsConstructor
public class GradeController {

    private GradeService service;


    @PreAuthorize ("hasRole('ADMIN') or (hasRole('TEACHER') and @securityRoleFilterUtil.isIdOwner(authentication, #gradeDTO))")
    @PostMapping (value = "/set-grades")
    public ResponseEntity<Void> setStudentGrades (@RequestBody GradeDTO gradeDTO) {
        service.setStudentGrades(gradeDTO);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
