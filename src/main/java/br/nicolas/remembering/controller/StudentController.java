package br.nicolas.remembering.controller;

import br.nicolas.remembering.dto.student.StudentCreateDTO;
import br.nicolas.remembering.dto.student.StudentResponseDTO;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.mapper.StudentMapper;
import br.nicolas.remembering.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/api/v1/student")
@AllArgsConstructor
public class StudentController {

    private StudentService service;

    private StudentMapper mapper;


    @PostMapping (value = "/save")
    public ResponseEntity<StudentResponseDTO> save (@Valid @RequestBody StudentCreateDTO createDTO) {
        Student saved = service.save(mapper.parseToEntity(createDTO), createDTO.getClassId());

        return new ResponseEntity<>(mapper.parseToResponse(saved), HttpStatus.CREATED);
    }


    @GetMapping (value = "/find/{id}")
    @PreAuthorize ("hasRole('ADMIN') or (hasRole('STUDENT') and @securityRoleFilterUtil.isIdOwner(authentication, #id))")
    public ResponseEntity<StudentResponseDTO> findById (@Positive @PathVariable Long id) {
        Student found = service.findById(id);

        return new ResponseEntity<>(mapper.parseToResponse(found), HttpStatus.OK);
    }
}
