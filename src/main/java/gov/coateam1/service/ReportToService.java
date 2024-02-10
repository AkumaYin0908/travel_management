package gov.coateam1.service;

import gov.coateam1.model.ReportTo;
import gov.coateam1.model.TravelOrder;

import java.util.List;

public interface ReportToService {


    List<ReportTo> findAll();
    ReportTo findByName(String name);

    ReportTo add(ReportTo reportTo);

    ReportTo update(ReportTo reportTo);

    void delete(Long id);



}
