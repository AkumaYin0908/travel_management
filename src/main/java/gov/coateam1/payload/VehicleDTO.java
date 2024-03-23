package gov.coateam1.payload;

import lombok.*;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleDTO that = (VehicleDTO) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


        @Override
    public String toString() {
        return String.format("%s %s/%s/%s",
                brand,model,type,plateNo);
    }

    //for testing purposes
    public VehicleDTO id(Long id){
        this.id=id;
        return this;
    }
}
