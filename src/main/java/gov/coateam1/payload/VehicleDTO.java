package gov.coateam1.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private Long id;

    @NotNull(message = "brand is required!")
    @Size(min = 3, message = "brand should consist of at least 3 characters!")
    private String brand;

    @NotNull(message = "model is required!")
    @Size(min = 3, message = "model should consist of at least 3 characters!")
    private String model;

    @NotNull(message = "type is required!")
    @Size(min = 3, message = "type should consist of at least 3 characters!")
    private String type;

    @NotNull(message = "brand is required!")
    @Size(min = 5, message = "brand should consist of at least 5 characters!")
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
