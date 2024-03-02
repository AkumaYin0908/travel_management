package gov.coateam1.service.impl;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.VehicleMapper;
import gov.coateam1.model.Vehicle;
import gov.coateam1.payload.VehicleDTO;
import gov.coateam1.repository.VehicleRepository;
import gov.coateam1.service.VehicleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;






    @Override
    public List<VehicleDTO> findAll() {
        return vehicleRepository.findAll().stream().map(v->modelMapper.map(v, VehicleDTO.class)).toList();
    }

    @Override
    public VehicleDTO findByBrand(String brand) {
        Vehicle vehicle = vehicleRepository.findByBrand(brand).orElseThrow(() -> new ResourceNotFoundException("Vehicle","brand",brand));
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    @Override
    public VehicleDTO findByPlateNo(String plateNo) {
        Vehicle vehicle = vehicleRepository.findByPlateNo(plateNo).orElseThrow(() -> new ResourceNotFoundException("Vehicle","plateNo",plateNo));
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    @Override
    public VehicleDTO findByModel(String model) {
        Vehicle vehicle =  vehicleRepository.findByModel(model).orElseThrow(() -> new ResourceNotFoundException("Vehicle","model",model));
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    @Override
    public VehicleDTO findByType(String type) {
        Vehicle vehicle = vehicleRepository.findByType(type).orElseThrow(() -> new ResourceNotFoundException("Vehicle","type",type));

        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    @Override
    @Transactional
    public VehicleDTO add(VehicleDTO vehicleDTO) {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        Vehicle dbVehicle = vehicleRepository.save(vehicle);
        vehicleDTO.setId(dbVehicle.getId());

        return  vehicleDTO;
    }

    @Override
    @Transactional
    public VehicleDTO update(VehicleDTO vehicleDTO, Long id) {

        Vehicle vehicle =  vehicleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Vehicle", "id", id));
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setType(vehicleDTO.getType());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setPlateNo(vehicleDTO.getPlateNo());
        vehicleRepository.save(vehicle);
        return vehicleDTO;
    }

    @Override
    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }
}
