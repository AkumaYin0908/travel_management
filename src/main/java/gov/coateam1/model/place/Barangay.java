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
    @Column(name="brgy_code")
    private String brgyCode;


    @Column(name="brgy_name")
    private String brgyName;


    @OneToMany(mappedBy = "barangay",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Place> places;


    public Barangay(String brgyCode, String brgyName) {
        this.brgyCode = brgyCode;
        this.brgyName = brgyName;
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
