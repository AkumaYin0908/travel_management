package gov.coateam1.service.impl.place;

import gov.coateam1.model.place.Municipality;
import gov.coateam1.repository.place.MunicipalityRepository;
import gov.coateam1.service.place.MunicipalityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MunicipalityServiceImpl implements MunicipalityService {

    private final MunicipalityRepository municipalityRepository;

    @Override
    public Municipality findByName(String name) {
        return municipalityRepository.findByName(name).orElse(new Municipality(name));
    }

    @Override
    public List<Municipality> findAll() {
        return municipalityRepository.findAll();
    }

    @Override
    @Transactional
    public Municipality add(Municipality municipality) {
        return municipalityRepository.save(municipality);
    }

    @Override
    @Transactional
    public Municipality update(Municipality municipality) {
        return municipalityRepository.save(municipality);
    }

    @Override
    public void delete(Long id) {
        municipalityRepository.deleteById(id);
    }
}
