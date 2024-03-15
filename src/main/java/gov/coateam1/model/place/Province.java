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
@Table(name="province")
public class Province {

    @Id
    @Column(name="province_code")
    private String provinceCode;


    @Column(name="name")
    private String provinceName;

    @OneToMany(mappedBy = "province",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Place> places;

    public Province(String provinceName) {
        this.provinceName = provinceName;
    }

    public Province(String provinceCode, String provinceName) {
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
    }

    public void addPlace(Place place){
        if(places==null){
            places = new ArrayList<>();
        }
        places.add(place);
        place.setProvince(this);
    }

    public void removePlace(Place place){
        places.remove(place);
        place.setProvince(null);
    }
}
