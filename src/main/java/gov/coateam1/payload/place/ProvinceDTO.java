package gov.coateam1.payload.place;

import gov.coateam1.payload.BasicDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProvinceDTO extends BasicDTO{

   private String regionCode;

   public ProvinceDTO(String code, String name) {
      super(code, name);
   }

   public ProvinceDTO(String code, String name, String regionCode) {
      super(code, name);
      this.regionCode = regionCode;
   }

   public ProvinceDTO(String code) {
      super(code);
   }
}
