package gov.coateam1.service;

import gov.coateam1.model.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAll();
    Vehicle findByBrand(String brand);

    Vehicle findByPlateNo(String plateNo);

    Vehicle findByModel(String model);

    Vehicle findByType(String type);

    Vehicle add(Vehicle vehicle);

    Vehicle update(Vehicle vehicle);
    void delete(Long id);
}
