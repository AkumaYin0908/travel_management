package gov.coateam1.repository.place;

import gov.coateam1.model.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {


    @Query("SELECT p FROM Place p WHERE p.buildingName = :buildingName")
    Place findByBuildingName(@Param("buildingName") String buildingName);
    @Query("SELECT p FROM Place p WHERE p.barangay.name = :name")
    Place findByBarangayName(@Param("name") String barangay);

    @Query("SELECT p FROM Place p WHERE  p.municipality.name = :name")
    Place findByMunicipalityName(@Param("name") String name);


    @Query("SELECT p FROM Place p WHERE  p.province.name = :name")
    Place findByProvinceName(@Param("name") String name);

    @Query("SELECT p FROM Place p WHERE  p.defaultPlace = :defaultPlace")
    Optional<Place> findByDefaultPlace(@Param("defaultPlace") String defaultPlace);


    @Query("SELECT p FROM Place p WHERE  p.buildingName = :buildingName AND p.barangay.name = :barangayName " +
            "AND p.municipality.name = :municipalityName AND p.province.name = :provinceName")
    Place findByCompletePlaceDetails(@Param("buildingName") String buildingName, @Param("barangayName") String barangay,
                                     @Param("municipalityName") String municipality, @Param("provinceName") String province);





}
