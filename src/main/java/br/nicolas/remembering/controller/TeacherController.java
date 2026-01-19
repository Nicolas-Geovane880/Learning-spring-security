package br.nicolas.remembering.controller;

import br.nicolas.remembering.dto.ApiResponse;
import br.nicolas.remembering.dto.teacher.TeacherCreateDTO;
import br.nicolas.remembering.dto.teacher.TeacherResponseDTO;
import br.nicolas.remembering.dto.teacher.TeacherUpdateDTO;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.mapper.TeacherMapper;
import br.nicolas.remembering.service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/api/v1/teacher")
@AllArgsConstructor
public class TeacherController {

    private TeacherService service;

    private TeacherMapper mapper;

    @PostMapping (value = "/save")
    public ResponseEntity<ApiResponse<TeacherResponseDTO>> save (@Valid @RequestBody TeacherCreateDTO createDTO) {
        Teacher saved = service.save(mapper.parseToEntity(createDTO));

        return new ResponseEntity<>(new ApiResponse<TeacherResponseDTO>()
                .setData(mapper.parseToResponse(saved))
                .addMeta("message", "teacher created successfully"),
                HttpStatus.CREATED);
    }

    @PreAuthorize ("hasRole('ADMIN') or (hasRole('TEACHER') and @securityRoleFilterUtil.isIdOwner(authentication, #id))")
    @GetMapping (value = "/find/{id}")
    public ResponseEntity<ApiResponse<TeacherResponseDTO>> findById (@PathVariable Long id) {
        Teacher found = service.findById(id);

        return new ResponseEntity<>(new ApiResponse<TeacherResponseDTO>()
                .setData(mapper.parseToResponse(found))
                .addMeta("message", "teacher found successfully"),
                HttpStatus.OK);
    }


    @PreAuthorize ("hasAnyRole('ADMIN') or (hasRole('TEACHER') and @securityRoleFilterUtil.isIdOwner(authentication, #id))")
    @PutMapping (value = "/update/{id}")
    public ResponseEntity<ApiResponse<TeacherResponseDTO>> update (@PathVariable Long id, @RequestBody TeacherUpdateDTO updateDTO) {
        Teacher updated = service.update(id, mapper.parseToEntity(updateDTO));

        return new ResponseEntity<>(new ApiResponse<TeacherResponseDTO>()
                .setData(mapper.parseToResponse(updated))
                .addMeta("message", "teacher updated successfully"),
                HttpStatus.OK);
    }

    @PreAuthorize ("hasAnyRole('ADMIN', 'TEACHER')")
    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById (@PathVariable Long id) {
        service.deleteById(id);

        return new ResponseEntity<>(new ApiResponse<Void>()
                .addMeta("message", "teacher deleted successfully"),
                HttpStatus.OK);
    }
}
