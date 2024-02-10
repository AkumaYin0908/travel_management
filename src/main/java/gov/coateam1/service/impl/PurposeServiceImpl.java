package gov.coateam1.service.impl;

import gov.coateam1.model.Purpose;
import gov.coateam1.repository.PurposeRepository;
import gov.coateam1.service.PurposeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PurposeServiceImpl implements PurposeService {

    private final PurposeRepository purposeRepository;

    @Override
    public Purpose findByPurpose(String purpose) {
        return purposeRepository.findByPurpose(purpose).orElse(new Purpose(purpose));
    }

    @Override
    public List<Purpose> findAll() {
        return purposeRepository.findAll();
    }

    @Override
    public Purpose save(Purpose purpose) {
        return purposeRepository.save(purpose);
    }

    @Override
    public Purpose update(Purpose purpose) {
        return purposeRepository.save(purpose);
    }

    @Override
    public void delete(Long id) {
        purposeRepository.deleteById(id);
    }
}
