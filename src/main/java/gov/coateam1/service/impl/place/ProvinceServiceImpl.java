package gov.coateam1.service.impl.place;

import gov.coateam1.model.place.Province;
import gov.coateam1.repository.place.ProvinceRepository;
import gov.coateam1.service.place.ProvinceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    @Override
    public Province findByName(String name) {
        return provinceRepository.findByname(name).orElse(new Province(name));
    }

    @Override
    public List<Province> getAll() {
        return provinceRepository.findAll();
    }

    @Override
    @Transactional
    public Province add(Province province) {
        return provinceRepository.save(province);
    }

    @Override
    @Transactional
    public Province update(Province province) {
        return provinceRepository.save(province);
    }

    @Override
    public void delete(Long id) {
        provinceRepository.deleteById(id);
    }
}
