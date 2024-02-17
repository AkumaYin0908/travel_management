package gov.coateam1.mapper;

import gov.coateam1.dto.SignatoryDTO;
import gov.coateam1.model.signatory.SignatoryModel;
import org.springframework.stereotype.Component;


@Component
public class SignatoryMapper {

    public <T extends SignatoryModel> SignatoryDTO mapToDTO(T signatory){
        return new SignatoryDTO(signatory.getId(),signatory.getName(),signatory.getPosition(),signatory.isActive());
    }

    public <T extends SignatoryModel> T mapToModel(SignatoryDTO signatoryDTO,Class<T> instance) throws Exception{
        T signatory = instance.getDeclaredConstructor().newInstance();
        signatory.setId(signatoryDTO.getId());
        signatory.setName(signatoryDTO.getName());
        signatory.setPosition(signatoryDTO.getPosition());
        signatory.setActive(signatoryDTO.isActive());

        return signatory;
    }

    public <T extends SignatoryModel> void mapToModel(SignatoryDTO signatoryDTO, T signatory){
        signatory.setName(signatoryDTO.getName());
        signatory.setName(signatoryDTO.getName());
        signatory.setPosition(signatoryDTO.getPosition());
        signatory.setActive(signatoryDTO.isActive());
    }
}
