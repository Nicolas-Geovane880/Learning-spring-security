package br.nicolas.remembering.controller;

import br.nicolas.remembering.dto.ApiResponse;
import br.nicolas.remembering.dto.clasS.ClassCreateDTO;
import br.nicolas.remembering.dto.clasS.ClassResponseDTO;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.mapper.ClassMapper;
import br.nicolas.remembering.service.ClassService;
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
    public ResponseEntity<ApiResponse<ClassResponseDTO>> save (@RequestBody ClassCreateDTO createDTO) {
        br.nicolas.remembering.entity.Class saved = service.save(mapper.parseToEntity(createDTO), createDTO.getTeacherId());

        return new ResponseEntity<>(new ApiResponse<ClassResponseDTO>()
                .setData(mapper.parseToResponse(saved))
                .addMeta("message", "class created successfully"),
                HttpStatus.CREATED);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<ApiResponse<ClassResponseDTO>> findById (@PathVariable Long id) {
        Class found = service.findById(id);

        return new ResponseEntity<>(new ApiResponse<ClassResponseDTO>()
                .setData(mapper.parseToResponse(found))
                .addMeta("message", "class found successfully"),
                HttpStatus.OK);
    }
}
