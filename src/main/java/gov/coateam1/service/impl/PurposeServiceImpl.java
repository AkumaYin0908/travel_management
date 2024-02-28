package gov.coateam1.service.impl;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.PurposeMapper;
import gov.coateam1.model.Purpose;
import gov.coateam1.payload.PurposeDTO;
import gov.coateam1.repository.PurposeRepository;
import gov.coateam1.service.PurposeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PurposeServiceImpl implements PurposeService {

    private final PurposeRepository purposeRepository;
    private final PurposeMapper purposeMapper;



    @Override
    public PurposeDTO findByPurpose(String purposeDescription) {
        Purpose purpose = purposeRepository.findByPurpose(purposeDescription).orElse(new Purpose(purposeDescription));

        return purposeMapper.mapToDTO(purpose);

    }

    @Override
    public List<PurposeDTO> findAll() {
        return purposeRepository.findAll().stream().map(purposeMapper::mapToDTO).toList();
    }

    @Override
    public PurposeDTO add(PurposeDTO purposeDTO) {
        Purpose purpose = purposeMapper.mapToModel(purposeDTO);
        Purpose dbPurpose = purposeRepository.save(purpose);
        purposeDTO.setId(dbPurpose.getId());
        return purposeDTO;

    }

    @Override
    public PurposeDTO update(PurposeDTO purposeDTO, Long id) {
        Purpose purpose=purposeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Purpose","id",id));
        purposeMapper.mapToModel(purposeDTO,purpose);
        purposeRepository.save(purpose);
        purposeDTO.setId(id);
        return  purposeDTO;
    }

    @Override
    public void delete(Long id) {
        purposeRepository.deleteById(id);
    }
}
