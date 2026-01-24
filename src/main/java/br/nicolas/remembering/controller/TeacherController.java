package br.nicolas.remembering.controller;

import br.nicolas.remembering.dto.teacher.TeacherCreateDTO;
import br.nicolas.remembering.dto.teacher.TeacherResponseDTO;
import br.nicolas.remembering.dto.teacher.TeacherUpdateDTO;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.mapper.TeacherMapper;
import br.nicolas.remembering.service.TeacherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/api/v1/teacher")
@AllArgsConstructor
public class TeacherController {

    private TeacherService service;

    private TeacherMapper mapper;


    @PostMapping (value = "/save")
    public ResponseEntity<TeacherResponseDTO> save (@Valid @RequestBody TeacherCreateDTO createDTO) {
        Teacher saved = service.save(mapper.parseToEntity(createDTO));

        return new ResponseEntity<>(mapper.parseToResponse(saved), HttpStatus.CREATED);
    }


    @PreAuthorize ("hasRole('ADMIN') or (hasRole('TEACHER') and @securityRoleFilterUtil.isIdOwner(authentication, #id))")
    @GetMapping (value = "/find/{id}")
    public ResponseEntity<TeacherResponseDTO> findById (@PathVariable Long id) {
        Teacher found = service.findById(id);

        return new ResponseEntity<>(mapper.parseToResponse(found), HttpStatus.OK);
    }


    @PreAuthorize ("hasAnyRole('ADMIN') or (hasRole('TEACHER') and @securityRoleFilterUtil.isIdOwner(authentication, #id))")
    @PutMapping (value = "/update/{id}")
    public ResponseEntity<TeacherResponseDTO> update (@Positive @PathVariable Long id, @RequestBody TeacherUpdateDTO updateDTO) {
        Teacher updated = service.update(id, mapper.parseToEntity(updateDTO));

        return new ResponseEntity<>(mapper.parseToResponse(updated), HttpStatus.OK);
    }


    @PreAuthorize ("hasRole('ADMIN') or (hasRole('TEACHER') and @securityRoleFilterUtil.isIdOwner(authentication, #id))")
    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<Void> deleteById (@Positive @PathVariable Long id) {
        service.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
