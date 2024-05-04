package gov.coateam1.controller;


import com.sun.net.httpserver.HttpsServer;
import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.ReportToDTO;
import gov.coateam1.service.ReportToService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report_tos")
@RequiredArgsConstructor
public class ReportToController {

    private final ReportToService reportToService;

    @GetMapping("/all")
    public ResponseEntity<List<ReportToDTO>> getAllReportTo(){
        List<ReportToDTO> reportTos = reportToService.findAll();

        if(reportTos.isEmpty()){
            return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(reportTos,HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ReportToDTO> getReportToById(@PathVariable("name") String name){
        return new ResponseEntity<>(reportToService.findByName(name),HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<ReportToDTO> saveReportTo(@Valid @RequestBody ReportToDTO reportToDTO){
        return new ResponseEntity<>(reportToService.add(reportToDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportToDTO> updateReportTo(@Valid @RequestBody ReportToDTO reportToDTO, @PathVariable("id") Long id){
        return new ResponseEntity<>(reportToService.update(reportToDTO,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteReportTo(@PathVariable("id") Long id){
        return  new ResponseEntity<>(new APIResponse("Delete successful!", true, HttpStatus.OK.value()), HttpStatus.OK);
    }
}
