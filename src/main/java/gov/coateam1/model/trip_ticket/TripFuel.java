package gov.coateam1.model.trip_ticket;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="trip_fuel")
public class TripFuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="fuel_balance",scale = 2)
    private BigDecimal fuelBalance;

    @Column(name="issued_fuel",scale = 3)
    private BigDecimal issuedFuel;

    @Column(name="purchased_Fuel",scale = 3)
    private BigDecimal purchasedFuel;

    @Column(name="consumed_fuel",scale = 2)
    private BigDecimal consumedFuel;

    @Column(name="remaining_fuel",scale = 2)
    private BigDecimal remainingFuel;
}
