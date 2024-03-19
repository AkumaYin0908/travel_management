package gov.coateam1.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private Long id;
    private String brand;
    private String model;
    private String type;
    private String plateNo;


    @Override
    public String toString() {
        return String.format("%s %s/%s/%s",
                brand,model,type,plateNo);
    }
}
