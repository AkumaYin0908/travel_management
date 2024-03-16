package gov.coateam1.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="report_to")
public class ReportTo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;


    @ToString.Exclude
    @ManyToMany(mappedBy = "reportTos")
    private List<TravelOrder> travelOrders;


    public ReportTo(String name) {
        this.name = name;
    }

    public ReportTo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

//    public void addTravelOrder(TravelOrder travelOrder){
//        if(travelOrders == null){
//            travelOrders = new ArrayList<>();
//        }
//
//        travelOrders.add(travelOrder);
//    }
//
//    public  void removeTravelOrder(TravelOrder travelOrder){
//        travelOrders.remove(travelOrder);
//    }
}
