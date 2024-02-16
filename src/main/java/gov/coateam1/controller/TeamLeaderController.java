package gov.coateam1.controller;

import gov.coateam1.model.TeamLeader;
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
    public ResponseEntity<List<TeamLeader>> getAllTeamLeaders(){
        return new ResponseEntity<>(teamLeaderService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<TeamLeader> saveTeamLeader(@RequestBody TeamLeader teamLeader){
        return new ResponseEntity<>(teamLeaderService.add(teamLeader),HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<TeamLeader> updateTeamLeader(@RequestBody TeamLeader teamLeader){
        return new ResponseEntity<>(teamLeaderService.update(teamLeader), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeamLeader(@PathVariable("id")Long id){
        teamLeaderService.delete(id);
        return new ResponseEntity<>("Team Leader has been successfully deleted!",HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateTeamLeaderStatus(@PathVariable Long id, @RequestBody boolean active){
        teamLeaderService.updateByActiveStatus(active,id);
        return new ResponseEntity<>("Update success!",HttpStatus.OK);
    }

    @GetMapping("find/{name}")
    public ResponseEntity<?> findTeamLeaderByName(@PathVariable("name")String name){
        try{
            return new ResponseEntity<>(teamLeaderService.findByName(name),HttpStatus.FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
