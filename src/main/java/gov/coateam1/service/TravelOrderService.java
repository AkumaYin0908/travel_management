package gov.coateam1.service;

import gov.coateam1.model.TravelOrder;
import gov.coateam1.payload.TravelOrderDTO;

import java.time.LocalDate;
import java.util.List;

public interface TravelOrderService {


    List<TravelOrderDTO> findAll();

    TravelOrderDTO findById(Long id);

    TravelOrderDTO add(Long employeeId, TravelOrderDTO travelOrderDTO) throws Exception;

    TravelOrderDTO update(TravelOrderDTO travelOrderDTO, Long id) throws Exception;

    void delete(Long id);


    List<TravelOrderDTO> findTravelOrderByEmployeeId(Long employeeId);

}
