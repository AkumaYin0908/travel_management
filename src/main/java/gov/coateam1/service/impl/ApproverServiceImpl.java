package gov.coateam1.service.impl;

import gov.coateam1.payload.SignatoryDTO;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.signatory.Approver;
import gov.coateam1.repository.ApproverRepository;
import gov.coateam1.service.ApproverService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApproverServiceImpl implements ApproverService {

    private final ApproverRepository approverRepository;
    private final ModelMapper modelMapper;



    @Override
    public List<SignatoryDTO> findAll() {
        return approverRepository.findAll().stream().map(s->modelMapper.map(s, SignatoryDTO.class)).toList();
    }

    @Override
    public SignatoryDTO findByActiveStatus(boolean active) {
        Approver approver = approverRepository.findByActiveStatus(active).orElseThrow(()-> new ResourceNotFoundException("Approver","active",String.valueOf(active)));
        return modelMapper.map(approver, SignatoryDTO.class);
    }

    @Override
    @Transactional
    public SignatoryDTO add(SignatoryDTO signatoryDTO) throws Exception {

        Optional<Approver> approverOptional = approverRepository.findByActiveStatus(true);
        Approver approver = modelMapper.map(signatoryDTO, Approver.class);
        approver.setActive(approverOptional.isEmpty());
        Approver dbApprover = approverRepository.save(approver);
        signatoryDTO.setId(dbApprover.getId());
        return signatoryDTO;
    }

    @Override
    @Transactional
    public SignatoryDTO update(SignatoryDTO signatoryDTO, Long id) {
        Approver approver = approverRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Approver","id",id));
        approver.setName(signatoryDTO.getName());
        approver.setPosition(signatoryDTO.getPosition());
        approver.setActive(signatoryDTO.isActive());
        approverRepository.save(approver);
        signatoryDTO.setId(id);
        return signatoryDTO;
    }


    @Override
    @Transactional
    public SignatoryDTO updateByActiveStatus(boolean active, Long id) {
        approverRepository.updateByActiveStatus(active,id);
        Approver approver=approverRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Approver","id",id));
        return modelMapper.map(approver, SignatoryDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        approverRepository.deleteById(id);
    }


    @Override
    public SignatoryDTO findByName(String name) {
        Approver approver = approverRepository.findByName(name).orElseThrow(()->new ResourceNotFoundException("Approver","name",name));
        return  modelMapper.map(approver, SignatoryDTO.class);
    }
}
