package gov.coateam1.service.impl;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.Approver;
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

    @Override
    public List<Approver> findAll() {
        return approverRepository.findAll();
    }

    @Override
    public Approver findByActiveStatus(boolean active) {
        return approverRepository.findByActiveStatus(active).orElseThrow(()-> new ResourceNotFoundException("Approver","active",String.valueOf(active)));
    }

    @Override
    @Transactional
    public Approver add(Approver approver) {

        Optional<Approver> approverOptional = approverRepository.findByActiveStatus(true);
        approver.setActive(approverOptional.isEmpty());
        return approverRepository.save(approver);
    }

    @Override
    @Transactional
    public Approver update(Approver approver) {
        return approverRepository.save(approver);
    }


    @Override
    @Transactional
    public void updateByActiveStatus(boolean active, long id) {
        approverRepository.updateByActiveStatus(active,id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        approverRepository.deleteById(id);
    }



    @Override
    public Approver findByName(String name) {
        return approverRepository.findByName(name).orElseThrow(()->new ResourceNotFoundException("Approver","name",name));
    }
}
