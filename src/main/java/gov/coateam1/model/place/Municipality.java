package gov.coateam1.model.place;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="municipality")
public class Municipality {

    @Id
    @Column(name="municipality_code")
    private String municipalityCode;


    @Column(name="municipality_name")
    private String municipalityName;

    @ToString.Exclude
    @OneToMany(mappedBy = "municipality",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Place> places;

    public Municipality(String municipalityCode, String municipalityName) {
        this.municipalityCode = municipalityCode;
        this.municipalityName = municipalityName;
    }

    public void addPlace(Place place){
        if(places==null){
            places = new ArrayList<>();
        }
        places.add(place);
        place.setMunicipality(this);
    }

    public void removePlace(Place place){
        places.remove(place);
        place.setMunicipality(null);
    }
}
