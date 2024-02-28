package gov.coateam1.mapper;

import gov.coateam1.payload.SignatoryDTO;
import gov.coateam1.model.signatory.SignatoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SignatoryMapper {


    public  <T extends SignatoryModel> SignatoryDTO mapToDTO(T signatory){
        return new SignatoryDTO(signatory.getId(),signatory.getName(),signatory.getPosition(),signatory.isActive());
    }

    public <T extends SignatoryModel>T  mapToModel(SignatoryDTO signatoryDTO,Class<T> clazz) throws Exception{
        T signatory = clazz.getDeclaredConstructor().newInstance();
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
