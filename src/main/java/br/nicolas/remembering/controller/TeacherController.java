package br.nicolas.remembering.controller;

import br.nicolas.remembering.dto.teacher.TeacherCreateDTO;
import br.nicolas.remembering.dto.teacher.TeacherResponseDTO;
import br.nicolas.remembering.dto.teacher.TeacherUpdateDTO;
import br.nicolas.remembering.service.AcademicOrchestrator;
import br.nicolas.remembering.service.TeacherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/api/v1/teacher")
@AllArgsConstructor
@Validated
public class TeacherController {

    private TeacherService service;

    private AcademicOrchestrator academicOrchestrator;

    @PostMapping (value = "/save")
    public ResponseEntity<TeacherResponseDTO> save (@Valid @RequestBody TeacherCreateDTO createDTO) {
        TeacherResponseDTO teacherResponse = academicOrchestrator.saveTeacher(createDTO);

        return new ResponseEntity<>(teacherResponse, HttpStatus.CREATED);
    }

    @PreAuthorize ("hasRole('ADMIN') or (hasRole('TEACHER') and @securityRoleFilterUtil.isIdOwner(authentication, #teacherId))")
    @GetMapping (value = "/find/{teacherId}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable Long teacherId) {
        TeacherResponseDTO teacherResponse = service.getTeacherById(teacherId);

        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }

    @PreAuthorize ("hasAnyRole('ADMIN') or (hasRole('TEACHER') and @securityRoleFilterUtil.isIdOwner(authentication, #teacherId))")
    @PutMapping (value = "/update/{teacherId}")
    public ResponseEntity<TeacherResponseDTO> update (@Positive @PathVariable Long teacherId,
                                                      @Valid @RequestBody TeacherUpdateDTO updateDTO) {
        TeacherResponseDTO teacherResponse = academicOrchestrator.updateTeacher(teacherId, updateDTO);

        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }

    @PreAuthorize ("hasRole('ADMIN') or (hasRole('TEACHER') and @securityRoleFilterUtil.isIdOwner(authentication, #teacherId))")
    @DeleteMapping (value = "/delete/{teacherId}")
    public ResponseEntity<Void> deleteById (@Positive @PathVariable Long teacherId) {
        academicOrchestrator.deleteTeacher(teacherId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
