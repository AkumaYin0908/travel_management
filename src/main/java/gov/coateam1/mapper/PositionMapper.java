package gov.coateam1.mapper;



import gov.coateam1.model.Position;
import gov.coateam1.payload.PositionDTO;
import org.springframework.stereotype.Component;

@Component
public class  PositionMapper {



   public PositionDTO mapToDTO(Position position){
       return new PositionDTO(position.getId(),position.getName());
   }



   public Position mapToModel(PositionDTO positionDTO){
       return new Position(positionDTO.getId(), positionDTO.getName());
   }

   public void mapToModel(PositionDTO positionDTO, Position position){
       position.setName(positionDTO.getName());
   }




}
