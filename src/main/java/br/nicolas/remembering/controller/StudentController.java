package br.nicolas.remembering.controller;

import br.nicolas.remembering.dto.student.StudentCreateDTO;
import br.nicolas.remembering.dto.student.StudentResponseDTO;
import br.nicolas.remembering.dto.student.StudentUpdateDTO;
import br.nicolas.remembering.service.AcademicOrchestrator;
import br.nicolas.remembering.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/api/v1/student")
@AllArgsConstructor
@Validated
public class StudentController {

    private StudentService service;

    private AcademicOrchestrator academicOrchestrator;

    @PostMapping (value = "/save")
    public ResponseEntity<StudentResponseDTO> save (@Valid @RequestBody StudentCreateDTO createDTO) {
        StudentResponseDTO studentResponse = academicOrchestrator.saveStudentWithClass(createDTO);

        return new ResponseEntity<>(studentResponse, HttpStatus.CREATED);
    }

    @GetMapping (value = "/find/{studentId}")
    @PreAuthorize ("hasRole('ADMIN') or (hasRole('STUDENT') and @securityRoleFilterUtil.isIdOwner(authentication, #studentId))")
    public ResponseEntity<StudentResponseDTO> getStudentById(@Positive @PathVariable Long studentId) {
        System.out.println("getting the student");
        StudentResponseDTO studentResponse = service.getStudentById(studentId);

        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @PreAuthorize ("hasRole('ADMIN') or (hasRole('STUDENT') and @securityRoleFilterUtil.isIdOwner(authentication, #studentId))")
    @PutMapping (value = "/update/{studentId}")
    public ResponseEntity<StudentResponseDTO> update (@Positive @PathVariable Long studentId,
                                                      @Valid @RequestBody StudentUpdateDTO updateDTO) {

        System.out.println("DTO no Controller: " + updateDTO.getName());

        StudentResponseDTO studentResponse = academicOrchestrator.updateStudent(studentId, updateDTO);

        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @PreAuthorize ("hasRole('ADMIN') or (hasRole('STUDENT') and @securityRoleFilterUtil.isIdOwner(authentication, #studentId))")
    @DeleteMapping (value = "/delete/{studentId}")
    public ResponseEntity<Void> delete (@Positive @PathVariable Long studentId) {
        service.delete(studentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping (value = "/passed-by-class/{classId}")
    public ResponseEntity<Page<StudentResponseDTO>> getAllPassedStudentsByClassId (@PathVariable Long classId,
                                                                                   @RequestParam (required = false, defaultValue = "0") int page,
                                                                                   @RequestParam (required = false, defaultValue = "10") int size) {

        Page<StudentResponseDTO> allPassedStudentsByClassId = academicOrchestrator.getAllPassedStudentsByClassId(classId, page, size);

        return new ResponseEntity<>(allPassedStudentsByClassId, HttpStatus.OK);
    }
}
