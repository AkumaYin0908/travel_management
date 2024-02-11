package gov.coateam1.service.impl.place;

import gov.coateam1.model.place.Barangay;
import gov.coateam1.repository.place.BarangayRepository;
import gov.coateam1.service.place.BarangayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BarangayServiceImpl implements BarangayService {

    private final BarangayRepository barangayRepository;

    @Override
    public Barangay findByName(String name) {

        return barangayRepository.findByName(name).orElse(new Barangay(name));
    }

    @Override
    public List<Barangay> findAll() {
        return barangayRepository.findAll();
    }

    @Override
    @Transactional
    public Barangay add(Barangay barangay) {
        return barangayRepository.save(barangay);
    }

    @Override
    @Transactional
    public Barangay update(Barangay barangay) {
        return barangayRepository.save(barangay);
    }

    @Override
    public void delete(Long id) {
        barangayRepository.deleteById(id);
    }
}
