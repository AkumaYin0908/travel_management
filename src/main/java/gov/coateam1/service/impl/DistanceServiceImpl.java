package gov.coateam1.service.impl;

import gov.coateam1.model.Distance;
import gov.coateam1.repository.DistanceRepository;
import gov.coateam1.service.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DistanceServiceImpl implements DistanceService {

    private final DistanceRepository distanceRepository;

    @Override
    public Distance add(Distance distance) {
        return distanceRepository.save(distance);
    }

    @Override
    public Distance update(Distance distance) {
        return distanceRepository.save(distance);
    }

    @Override
    public Distance findDistance(Long startDistance) {
        return distanceRepository.findDistance().orElse(new Distance(startDistance));
    }
}
