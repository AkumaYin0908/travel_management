package gov.coateam1.constants;

public interface PlaceQueryConstant {

    String FIND_BY_BUILDINGNAME = "select" +
            " building_name," +
            " b.barangay_code," +
            " b.barangay_name," +
            " m.municipality_code," +
            " m.municipality_name," +
            " p.province_code," +
            " p.province_name," +
            " r.region_code," +
            " r.region_name" +
            " from place inner join barangay as b on place.barangay = b.barangay_code" +
            " inner join municipality as m on place.municipality = m.municipality_code" +
            " inner join province as p on place.province = p.province_code" +
            " inner join region as r on place.region = r.region_code" +
            " where building_name = ?";


    String FIND_BY_CODES = "select * from place where barangay_code = ? and municipality_code =? and province_code = ? and region_code = ?";

}
