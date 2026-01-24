package br.nicolas.remembering.controller;

import br.nicolas.remembering.dto.classes.ClassCreateDTO;
import br.nicolas.remembering.dto.classes.ClassResponseDTO;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.mapper.ClassMapper;
import br.nicolas.remembering.service.ClassService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/class")
@AllArgsConstructor
public class ClassController {

    private ClassService service;

    private ClassMapper mapper;


    @PostMapping(value = "/save")
    public ResponseEntity<ClassResponseDTO> save (@Valid @RequestBody ClassCreateDTO createDTO) {
        Class saved = service.save(mapper.parseToEntity(createDTO), createDTO.getTeacherId());

        return new ResponseEntity<>(mapper.parseToResponse(saved), HttpStatus.CREATED);
    }


    @GetMapping(value = "/find/{id}")
    public ResponseEntity<ClassResponseDTO> findById (@Positive @PathVariable Long id) {
        Class found = service.findById(id);

        return new ResponseEntity<>(mapper.parseToResponse(found), HttpStatus.OK);
    }
}
