package gov.coateam1.payload.place;

import gov.coateam1.payload.BasicDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProvinceDTO extends BasicDTO{

   private String psgcCode;

   public ProvinceDTO(String code, String name, String psgcCode) {
      super(code, name);
      this.psgcCode = psgcCode;
   }

   public ProvinceDTO(String code) {
      super(code);
   }
}
