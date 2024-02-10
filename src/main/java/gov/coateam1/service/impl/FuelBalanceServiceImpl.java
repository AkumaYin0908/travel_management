package gov.coateam1.service.impl;

import gov.coateam1.model.FuelBalance;
import gov.coateam1.repository.FuelBalanceRepository;
import gov.coateam1.service.FuelBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class FuelBalanceServiceImpl implements FuelBalanceService {

    private final FuelBalanceRepository fuelBalanceRepository;


    @Override
    public FuelBalance add(FuelBalance fuelBalance) {
        return fuelBalanceRepository.save(fuelBalance);
    }

    @Override
    public FuelBalance update(FuelBalance fuelBalance) {
        return fuelBalanceRepository.save(fuelBalance);
    }

    @Override
    public FuelBalance findFuelBalance(BigDecimal fuelBalance) {
        return fuelBalanceRepository.findFuelBalance().orElse(new FuelBalance(fuelBalance));
    }
}
