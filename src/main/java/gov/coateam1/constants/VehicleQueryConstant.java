package gov.coateam1.constants;

public interface VehicleQueryConstant {

    String FIND_BY_BRAND = "select * from vehicle where brand = ?";

    String FIND_BY_TYPE = "select * from vehicle where type = ?";

    String FIND_BY_MODEL = "select * from vehicle where model = ?";
}
