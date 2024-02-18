package gov.coateam1.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
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


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="reportto_travelorder",
            joinColumns = @JoinColumn(name="reportto_id"),
            inverseJoinColumns = @JoinColumn(name="travelorder_id"))
    private List<TravelOrder> travelOrders;



    public ReportTo(String name) {
        this.name = name;
    }
}
