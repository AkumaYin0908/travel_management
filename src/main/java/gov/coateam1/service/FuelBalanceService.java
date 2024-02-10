package gov.coateam1.service;

import gov.coateam1.model.FuelBalance;

import java.math.BigDecimal;

public interface FuelBalanceService {

    FuelBalance add(FuelBalance fuelBalance);

    FuelBalance update(FuelBalance fuelBalance);

    FuelBalance findFuelBalance(BigDecimal fuelBalance);
}
