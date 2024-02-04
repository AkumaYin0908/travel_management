package gov.coateam1.service;

import gov.coateam1.model.ReportTo;
import gov.coateam1.model.TravelOrder;

import java.util.List;

public interface ReportToService {


    ReportTo findReportToByName(String name);

    ReportTo addReportTo(ReportTo reportTo);

    ReportTo updateReportTo(ReportTo reportTo);

    void deleteReportTo(Long id);



}
