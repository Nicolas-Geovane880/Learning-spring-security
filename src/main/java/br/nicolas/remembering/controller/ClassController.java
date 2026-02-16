package br.nicolas.remembering.controller;

import br.nicolas.remembering.dto.classes.ClassCreateDTO;
import br.nicolas.remembering.dto.classes.ClassResponseDTO;
import br.nicolas.remembering.dto.classes.ClassUpdateDTO;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.mapper.ClassMapper;
import br.nicolas.remembering.service.AcademicOrchestrator;
import br.nicolas.remembering.service.ClassService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/class")
@AllArgsConstructor
@Validated
public class ClassController {

    private ClassService service;

    private AcademicOrchestrator academicOrchestrator;

    @PostMapping(value = "/save")
    public ResponseEntity<ClassResponseDTO> save (@Valid @RequestBody ClassCreateDTO createDTO) {
        ClassResponseDTO classResponse = academicOrchestrator.saveClassWithTeacher(createDTO);

        return new ResponseEntity<>(classResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/find/{classId}")
    public ResponseEntity<ClassResponseDTO> getClassById(@Positive @PathVariable Long classId) {
        ClassResponseDTO classResponse = service.getClassById(classId);

        return new ResponseEntity<>(classResponse, HttpStatus.OK);
    }

    @PutMapping (value = "/update/{classId}")
    public ResponseEntity<ClassResponseDTO> update (@Positive @PathVariable Long classId,
                                                    @Valid @RequestBody ClassUpdateDTO updateDTO) {
        ClassResponseDTO classResponse = academicOrchestrator.updateClass(classId, updateDTO);

        return new ResponseEntity<>(classResponse, HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{classId}")
    public ResponseEntity<Void> delete (@Positive @PathVariable Long classId) {
        academicOrchestrator.deleteClass(classId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping (value = "/all-by-discipline/{disciplineStr}")
    public ResponseEntity<Page<ClassResponseDTO>> getAllClassesByDiscipline (@PathVariable String disciplineStr,
                                                                             @RequestParam (required = false, defaultValue = "0") int page,
                                                                             @RequestParam (required = false, defaultValue = "10") int size) {

        Page<ClassResponseDTO> allClassesByDiscipline = service.getAllClassesByDiscipline(disciplineStr, page, size);

        return new ResponseEntity<>(allClassesByDiscipline, HttpStatus.OK);
    }
}
