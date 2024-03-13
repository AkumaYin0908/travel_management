package gov.coateam1.model.place;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="barangay")
public class Barangay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "barangay",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Place> places;



    public Barangay(String name) {
        this.name = name;
    }

    public Barangay(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addPlace(Place place){
        if(places==null){
            places = new ArrayList<>();
        }
        places.add(place);
        place.setBarangay(this);
    }

    public void removePlace(Place place){
        places.remove(place);
        place.setBarangay(null);
    }
}
