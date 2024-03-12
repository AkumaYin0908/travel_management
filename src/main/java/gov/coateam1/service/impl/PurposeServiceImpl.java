package gov.coateam1.service.impl;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.PurposeMapper;
import gov.coateam1.model.Purpose;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.payload.PurposeDTO;
import gov.coateam1.repository.PurposeRepository;
import gov.coateam1.service.PurposeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PurposeServiceImpl implements PurposeService {

    private final PurposeRepository purposeRepository;
    private final ModelMapper modelMapper;



    @Override
    public PurposeDTO findByPurpose(String purposeDescription) {
        Purpose purpose = purposeRepository.findByPurpose(purposeDescription).orElse(new Purpose(purposeDescription));

        return modelMapper.map(purpose,PurposeDTO.class);

    }

    @Override
    public List<PurposeDTO> findAll() {
        return purposeRepository.findAll().stream().map(p->modelMapper.map(p,PurposeDTO.class)).toList();
    }

    @Override
    @Transactional
    public PurposeDTO add(PurposeDTO purposeDTO) {
        Purpose purpose = modelMapper.map(purposeDTO,Purpose.class);
        Purpose dbPurpose = purposeRepository.save(purpose);
        purposeDTO.setId(dbPurpose.getId());
        return purposeDTO;

    }

    @Override
    @Transactional
    public PurposeDTO update(PurposeDTO purposeDTO, Long id) {
        Purpose purpose=purposeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Purpose","id",id));
        purpose.setPurpose(purposeDTO.getPurpose());
        purposeRepository.save(purpose);
        purposeDTO.setId(id);
        return  purposeDTO;
    }

    @Override
    public void delete(Long id) {
        purposeRepository.deleteById(id);
    }
}
