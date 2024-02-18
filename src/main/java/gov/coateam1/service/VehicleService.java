package gov.coateam1.service;

import gov.coateam1.model.Vehicle;
import gov.coateam1.payload.VehicleDTO;

import java.util.List;

public interface VehicleService {

    List<VehicleDTO> findAll();
    VehicleDTO findByBrand(String brand);

    VehicleDTO findByPlateNo(String plateNo);

    VehicleDTO findByModel(String model);

    VehicleDTO findByType(String type);

    VehicleDTO add(VehicleDTO vehicleDTO);

    VehicleDTO update(VehicleDTO vehicleDTO, Long id);
    void delete(Long id);
}
