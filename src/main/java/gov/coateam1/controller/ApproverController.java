package gov.coateam1.controller;

import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.SignatoryDTO;
import gov.coateam1.service.ApproverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/approvers")
public class ApproverController {

    private final ApproverService approverService;

    @GetMapping("/all")
    public ResponseEntity<List<SignatoryDTO>> getAllApprover(){
        return new ResponseEntity<>(approverService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SignatoryDTO> saveApprover(@RequestBody SignatoryDTO signatoryDTO) throws Exception {
        return new ResponseEntity<>(approverService.add(signatoryDTO),HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SignatoryDTO> updateApprover(@RequestBody SignatoryDTO signatoryDTO, @PathVariable("id")Long id){
        return new ResponseEntity<>(approverService.update(signatoryDTO,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteApprover(@PathVariable("id")Long id){
        approverService.delete(id);
        return new ResponseEntity<>(new APIResponse("Approver has been successfully deleted!",
                true,HttpStatus.OK.value()),HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<SignatoryDTO> updateApproverStatus(@PathVariable("id") Long id, @RequestParam("active") boolean active){

        return new ResponseEntity<>( approverService.updateByActiveStatus(active,id),HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<SignatoryDTO> findApproverByName(@PathVariable("name")String name){
            return new ResponseEntity<>(approverService.findByName(name),HttpStatus.FOUND);
    }
}
