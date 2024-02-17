package gov.coateam1.service.impl;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.Vehicle;
import gov.coateam1.repository.VehicleRepository;
import gov.coateam1.service.VehicleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle findByBrand(String brand) {
        return vehicleRepository.findByBrand(brand).orElseThrow(() -> new ResourceNotFoundException("Vehicle","brand",brand));
    }

    @Override
    public Vehicle findByPlateNo(String plateNo) {
        return vehicleRepository.findByPlateNo(plateNo).orElseThrow(() -> new ResourceNotFoundException("Vehicle","plateNo",plateNo));
    }

    @Override
    public Vehicle findByModel(String model) {
        return vehicleRepository.findByModel(model).orElseThrow(() -> new ResourceNotFoundException("Vehicle","model",model));
    }

    @Override
    public Vehicle findByType(String type) {
        return vehicleRepository.findByType(type).orElseThrow(() -> new ResourceNotFoundException("Vehicle","type",type));
    }

    @Override
    @Transactional
    public Vehicle add(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Vehicle update(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }
}
