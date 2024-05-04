package gov.coateam1.controller;


import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.PositionDTO;
import gov.coateam1.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;


    @GetMapping("/all")
    public ResponseEntity<List<PositionDTO>> getAllPosition(){
        List<PositionDTO> positions = positionService.findAll();

        if(positions.isEmpty()){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
            return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<PositionDTO> getPositionByName(@PathVariable("name") String name){
        return new ResponseEntity<>(positionService.findByName(name), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<PositionDTO> savePosition( @Valid @RequestBody PositionDTO positionDTO){
        return new ResponseEntity<>(positionService.add(positionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionDTO> updatePosition(@Valid @RequestBody PositionDTO positionDTO, @PathVariable("id") Long id){
        return  new ResponseEntity<>(positionService.update(positionDTO,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deletePosition(@PathVariable("id")Long id){
        return new ResponseEntity<>(new APIResponse("Delete successful!", true, HttpStatus.OK.value()),HttpStatus.OK);
    }
}
