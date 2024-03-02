package gov.coateam1.service.impl.place;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.place.Province;
import gov.coateam1.payload.place.ProvinceDTO;
import gov.coateam1.repository.place.ProvinceRepository;
import gov.coateam1.service.place.ProvinceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper;
    @Override
    public ProvinceDTO findByName(String name) {
        return modelMapper.map(provinceRepository.findByname(name).orElse(new Province(name)),ProvinceDTO.class);
    }

    @Override
    public List<ProvinceDTO> getAll() {

        return provinceRepository.findAll().stream().map(p->modelMapper.map(p,ProvinceDTO.class)).toList();
    }

    @Override
    @Transactional
    public ProvinceDTO add(ProvinceDTO provinceDTO) {
        Province province = modelMapper.map(provinceDTO,Province.class);
        Province dbProvince = provinceRepository.save(province);
        provinceDTO.setId(dbProvince.getId());
        return provinceDTO;
    }

    @Override
    @Transactional
    public ProvinceDTO update(ProvinceDTO provinceDTO, Long id) {
        Province province = provinceRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Province","id",id));
        province.setName(provinceDTO.getName());
        provinceRepository.save(province);
        provinceDTO.setId(id);
        return provinceDTO;
    }

    @Override
    public void delete(Long id) {
        provinceRepository.deleteById(id);
    }
}
