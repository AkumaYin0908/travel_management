package gov.coateam1.service;

import gov.coateam1.model.Distance;

public interface DistanceService {



    Distance add(Distance distance);

    Distance update(Distance distance);

    Distance findDistance(Long startDistance);
}
