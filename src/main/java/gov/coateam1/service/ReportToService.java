package gov.coateam1.service;

import gov.coateam1.model.ReportTo;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.payload.ReportToDTO;
import gov.coateam1.payload.SignatoryDTO;

import java.util.List;

public interface ReportToService {


    List<ReportToDTO> findAll();
    ReportToDTO findByName(String name);

    ReportToDTO add(ReportToDTO reportToDTO);

    ReportToDTO update(ReportToDTO reportToDTO, Long id);

    void delete(Long id);



}
