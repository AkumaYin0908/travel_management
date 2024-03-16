package gov.coateam1.repository.place;

import gov.coateam1.constants.PlaceQueryConstant;
import gov.coateam1.model.place.Place;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {


    @Query("SELECT p FROM Place p WHERE p.buildingName = :buildingName")
    Optional<Place> findByBuildingName(@Param("buildingName") String buildingName);
//    @Query("SELECT p FROM Place p WHERE p.barangay.name = :name")
//    Optional<Place> findByBarangayName(@Param("name") String barangay);
//
//    @Query("SELECT p FROM Place p WHERE  p.municipality.name = :name")
//    Optional<Place> findByMunicipalityName(@Param("name") String name);
//
//
//    @Query("SELECT p FROM Place p WHERE  p.province.name = :name")
//    Optional<Place> findByProvinceName(@Param("name") String name);

    @Query("SELECT p FROM Place p WHERE  p.defaultPlace = :defaultPlace")
    Optional<Place> findByDefaultPlace(@Param("defaultPlace") String defaultPlace);

    @Query(value = PlaceQueryConstant.FINDBY_CODES,nativeQuery = true)
    Optional<Place> findPlaceByCodes(String barangayCode, String municipalityCode,String provinceCode,String regionCode);


//    @Query("SELECT p FROM Place p WHERE  p.buildingName = :buildingName AND p.barangay.name = :barangayName " +
//            "AND p.municipality.name = :municipalityName AND p.province.name = :provinceName")
//    Optional<Place> findByCompletePlaceDetails(@Param("buildingName") String buildingName, @Param("barangayName") String barangay,
//                                     @Param("municipalityName") String municipality, @Param("provinceName") String province);





}
