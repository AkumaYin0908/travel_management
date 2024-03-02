package gov.coateam1.service.impl.place;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.place.Municipality;
import gov.coateam1.payload.place.MunicipalityDTO;
import gov.coateam1.repository.place.MunicipalityRepository;
import gov.coateam1.service.place.MunicipalityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MunicipalityServiceImpl implements MunicipalityService {

    private final MunicipalityRepository municipalityRepository;
    private final ModelMapper modelMapper;

    @Override
    public MunicipalityDTO findByName(String name) {
        return modelMapper.map(municipalityRepository.findByName(name).orElse(new Municipality(name)),MunicipalityDTO.class);
    }

    @Override
    public List<MunicipalityDTO> findAll() {
        return municipalityRepository.findAll().stream().map(m->modelMapper.map(m, MunicipalityDTO.class)).toList();
    }

    @Override
    @Transactional
    public MunicipalityDTO add(MunicipalityDTO municipalityDTO) {
        Municipality municipality = modelMapper.map(municipalityDTO, Municipality.class);
        Municipality dbMunicipality = municipalityRepository.save(municipality);
        municipalityDTO.setId(dbMunicipality.getId());
        return municipalityDTO;
    }

    @Override
    @Transactional
    public MunicipalityDTO update(MunicipalityDTO municipalityDTO, Long id) {
        Municipality municipality = municipalityRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Municipality","id",id));
        municipality.setName(municipalityDTO.getName());
        municipalityRepository.save(municipality);
        return modelMapper.map(municipality, MunicipalityDTO.class);
    }

    @Override
    public void delete(Long id) {
        municipalityRepository.deleteById(id);
    }
}
