package gov.coateam1.controller;

import gov.coateam1.model.Approver;
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
    public ResponseEntity<List<Approver>> getAllApprovers(){
        return new ResponseEntity<>(approverService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Approver> saveApprover(@RequestBody Approver approver){
        return new ResponseEntity<>(approverService.add(approver),HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Approver> updateApprover(@RequestBody Approver approver){
        return new ResponseEntity<>(approverService.update(approver), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApprover(@PathVariable("id")Long id){
        approverService.delete(id);
        return new ResponseEntity<>("Team Leader has been successfully deleted!",HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateApproverStatus(@PathVariable Long id, @RequestParam("active") boolean active){
        approverService.updateByActiveStatus(active,id);
        return new ResponseEntity<>("Update success!",HttpStatus.OK);
    }

    @GetMapping("find/{name}")
    public ResponseEntity<?> findApproverByName(@PathVariable("name")String name){
        try{
            return new ResponseEntity<>(approverService.findByName(name),HttpStatus.FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(ex,HttpStatus.NOT_FOUND);
        }
    }
}
