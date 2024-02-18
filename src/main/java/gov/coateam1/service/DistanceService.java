package gov.coateam1.service;

import gov.coateam1.model.Distance;

public interface DistanceService {

    Distance add(Distance distance);

    Distance update(Long startDistance);

    Distance findDistance();
}
