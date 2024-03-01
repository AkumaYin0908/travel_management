package gov.coateam1.controller;

import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.SignatoryDTO;
import gov.coateam1.service.TeamLeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teamleaders")
public class TeamLeaderController {

    private final TeamLeaderService teamLeaderService;


    @GetMapping("/all")
    public ResponseEntity<List<SignatoryDTO>> getAllTeamLeaders(){
        return new ResponseEntity<>(teamLeaderService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SignatoryDTO> saveTeamLeader(@RequestBody SignatoryDTO signatoryDTO) throws Exception {
        return new ResponseEntity<>(teamLeaderService.add(signatoryDTO),HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SignatoryDTO> updateTeamLeader(@RequestBody SignatoryDTO signatoryDTO, @PathVariable("id")Long id){
        return new ResponseEntity<>(teamLeaderService.update(signatoryDTO,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteTeamLeader(@PathVariable("id")Long id){
        teamLeaderService.delete(id);
        return new ResponseEntity<>(new APIResponse("Team Leader has been successfully deleted!",
                true,HttpStatus.OK.value()),HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<SignatoryDTO> updateTeamLeaderStatus(@PathVariable Long id, @RequestParam(value = "active") boolean active){

        return new ResponseEntity<>(teamLeaderService.updateByActiveStatus(active,id),HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<SignatoryDTO> findTeamLeaderByName(@PathVariable("name")String name){
            return new ResponseEntity<>(teamLeaderService.findByName(name),HttpStatus.FOUND);
    }


}
