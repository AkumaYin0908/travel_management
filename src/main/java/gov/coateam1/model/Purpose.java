package gov.coateam1.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="purpose")
@ToString(onlyExplicitlyIncluded = true)
public class Purpose {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @ToString.Include
    private Long id;


    @Column(name="purpose")
    @ToString.Include
    private String purpose;

    @OneToMany(mappedBy = "purpose",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<TravelOrder> travelOrders;

    @OneToMany(mappedBy = "purpose",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<TripTicket> tripTickets;


    public Purpose(Long id, String purpose) {
        this.id = id;
        this.purpose = purpose;
    }

    public Purpose(String purpose) {
        this.purpose = purpose;
    }

    public void addTravelOrder(TravelOrder travelOrder){
        if(travelOrders == null){
            travelOrders = new ArrayList<>();
        }

        travelOrders.add(travelOrder);
    }
}
