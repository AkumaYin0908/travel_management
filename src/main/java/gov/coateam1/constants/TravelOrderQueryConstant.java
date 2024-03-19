package gov.coateam1.constants;

public interface TravelOrderQueryConstant {

    String FIND_BY_DATE_ISSUED = "select * from travel_order where date_issued = ?";
}
