package gov.coateam1.mapper;

import gov.coateam1.model.Purpose;
import gov.coateam1.payload.PurposeDTO;
import org.springframework.stereotype.Component;

@Component
public class PurposeMapper {

    public Purpose mapToModel(PurposeDTO purposeDTO) {
        return new Purpose(purposeDTO.getId(), purposeDTO.getPurpose());
    }

    public PurposeDTO mapToDTO(Purpose purpose) {
        return new PurposeDTO(purpose.getId(), purpose.getPurpose());
    }

    public void  mapToModel(PurposeDTO purposeDTO, Purpose purpose){
        purpose.setPurpose(purposeDTO.getPurpose());
    }
}
