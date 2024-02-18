package gov.coateam1.service.impl;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.FuelBalance;
import gov.coateam1.repository.FuelBalanceRepository;
import gov.coateam1.service.FuelBalanceService;
import jakarta.transaction.Transactional;
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
    @Transactional
    public FuelBalance update(BigDecimal remainingFuel) {
        FuelBalance fuelBalance = this.findFuelBalance();
        fuelBalance.setFuel(remainingFuel);
        return fuelBalanceRepository.save(fuelBalance);
    }

    @Override
    public FuelBalance findFuelBalance() {
        return fuelBalanceRepository.findFuelBalance().orElseThrow(()->
                new ResourceNotFoundException("I assume that this is your first time using this app. Set beginning fuel balance at the settings first!"));

    }
}
