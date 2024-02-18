package gov.coateam1.service.impl;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.Distance;
import gov.coateam1.repository.DistanceRepository;
import gov.coateam1.service.DistanceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DistanceServiceImpl implements DistanceService {

    private final DistanceRepository distanceRepository;

    @Override
    @Transactional
    public Distance add(Distance distance) {
        return distanceRepository.save(distance);
    }

    @Override
    @Transactional
    public Distance update(Long startDistance) {

        Distance distance = this.findDistance();
        distance.setDistance(startDistance);
        return distanceRepository.save(distance);
    }

    @Override
    public Distance findDistance() {
        return distanceRepository.findDistance().orElseThrow(()->
                new ResourceNotFoundException("I assume that this is your first time using this app. Set start distance at the settings first!"));
    }
}
