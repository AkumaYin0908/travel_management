package gov.coateam1.service;

import gov.coateam1.model.Vehicle;
import gov.coateam1.payload.VehicleDTO;

import java.util.List;

public interface VehicleService {

    List<VehicleDTO> findAll();
    List<VehicleDTO> findByBrand(String brand);

    VehicleDTO findByPlateNo(String plateNo);

    List<VehicleDTO> findByModel(String model);

    List<VehicleDTO> findByType(String type);

    VehicleDTO add(VehicleDTO vehicleDTO);

    VehicleDTO update(VehicleDTO vehicleDTO, Long id);
    void delete(Long id);
}
