package gov.coateam1.mapper;

import gov.coateam1.model.ReportTo;
import gov.coateam1.payload.BasicDTO;
import gov.coateam1.payload.ReportToDTO;
import org.springframework.stereotype.Component;

@Component

public class ReportToMapper {

    public ReportToDTO mapToDTO(ReportTo reportTo){
        return new ReportToDTO(reportTo.getId(),reportTo.getName());
    }

    public ReportTo mapToModel(BasicDTO basicDTO){
        return new ReportTo(basicDTO.getId(),basicDTO.getName());
    }
}
