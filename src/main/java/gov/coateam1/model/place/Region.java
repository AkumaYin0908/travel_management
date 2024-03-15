package gov.coateam1.model.place;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="region")
public class Region {

    @Id
    @Column(name="region_code")
    private String  regionCode;

    @Column(name="region_name")
    private String regionName;

    @OneToMany(mappedBy = "region",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Place> places;

    public Region(String regionCode, String regionName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
    }

    public void addPlace(Place place){
        if(places==null){
            places = new ArrayList<>();
        }
        places.add(place);
        place.setRegion(this);
    }

    public void removePlace(Place place){
        places.remove(place);
        place.setRegion(null);
    }

}
