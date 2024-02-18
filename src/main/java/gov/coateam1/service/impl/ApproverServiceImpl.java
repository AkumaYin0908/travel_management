package gov.coateam1.service.impl;

import gov.coateam1.payload.SignatoryDTO;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.SignatoryMapper;
import gov.coateam1.model.signatory.Approver;
import gov.coateam1.repository.ApproverRepository;
import gov.coateam1.service.ApproverService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApproverServiceImpl implements ApproverService {

    private final ApproverRepository approverRepository;
    private final SignatoryMapper signatoryMapper;



    @Override
    public List<SignatoryDTO> findAll() {
        return approverRepository.findAll().stream().map(signatoryMapper::mapToDTO).toList();
    }

    @Override
    public SignatoryDTO findByActiveStatus(boolean active) {
        Approver approver = approverRepository.findByActiveStatus(active).orElseThrow(()-> new ResourceNotFoundException("Approver","active",String.valueOf(active)));
        return signatoryMapper.mapToDTO(approver);
    }

    @Override
    @Transactional
    public SignatoryDTO add(SignatoryDTO signatoryDTO) throws Exception {

        Optional<Approver> approverOptional = approverRepository.findByActiveStatus(true);
        Approver approver = signatoryMapper.mapToModel(signatoryDTO,Approver.class);
        approver.setActive(approverOptional.isEmpty());
        Approver dbApprover = approverRepository.save(approver);
        signatoryDTO.setId(dbApprover.getId());
        return signatoryDTO;
    }

    @Override
    @Transactional
    public SignatoryDTO update(SignatoryDTO signatoryDTO, Long id) {
        Approver approver = approverRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Approver","id",id));
        signatoryMapper.mapToModel(signatoryDTO,approver);
        approverRepository.save(approver);
        signatoryDTO.setId(id);
        return signatoryDTO;
    }


    @Override
    @Transactional
    public SignatoryDTO updateByActiveStatus(boolean active, Long id) {
        approverRepository.updateByActiveStatus(active,id);
        Approver approver=approverRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Approver","id",id));
        return signatoryMapper.mapToDTO(approver);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        approverRepository.deleteById(id);
    }


    @Override
    public SignatoryDTO findByName(String name) {
        Approver approver = approverRepository.findByName(name).orElseThrow(()->new ResourceNotFoundException("Approver","name",name));
        return  signatoryMapper.mapToDTO(approver);
    }
}
