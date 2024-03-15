package gov.coateam1.mapper;

import gov.coateam1.model.Vehicle;
import gov.coateam1.payload.VehicleDTO;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle mapToModel(VehicleDTO vehicleDTO){
        return new Vehicle(vehicleDTO.getId(),vehicleDTO.getBrand(),vehicleDTO.getModel(),
                vehicleDTO.getType(),vehicleDTO.getPlateNo());
    }

    public VehicleDTO mapToDTO(Vehicle vehicle){
        return new VehicleDTO(vehicle.getId(),vehicle.getBrand(),vehicle.getModel(),vehicle.getType(), vehicle.getPlateNo());
    }

    public  void mapToModel(VehicleDTO vehicleDTO, Vehicle vehicle){
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setType(vehicleDTO.getType());
        vehicle.setPlateNo(vehicleDTO.getPlateNo());
    }
}
