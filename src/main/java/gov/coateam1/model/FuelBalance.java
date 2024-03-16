package gov.coateam1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="fuel_balance")
public class FuelBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="fuel")
    private BigDecimal fuel;

    public FuelBalance(BigDecimal fuel) {
        this.fuel = fuel;
    }
}
