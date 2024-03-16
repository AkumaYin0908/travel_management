package gov.coateam1.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="purpose")
public class Purpose {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="purpose")
    private String purpose;

    @ToString.Exclude
    @OneToMany(mappedBy = "purpose",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<TravelOrder> travelOrders;

    @ToString.Exclude
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
        travelOrder.setPurpose(this);
    }
}
