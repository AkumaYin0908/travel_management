package gov.coateam1.service.impl;

import gov.coateam1.exception.ApproverNotFoundException;
import gov.coateam1.model.Approver;
import gov.coateam1.repository.ApproverRepository;
import gov.coateam1.service.ApproverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return approverRepository.findByActiveStatus(active).orElseThrow(()-> new ApproverNotFoundException("No approver is currently active!"));
    }

    @Override
    public Approver add(Approver approver) {
        return approverRepository.save(approver);
    }

    @Override
    public Approver update(Approver approver) {
        return approverRepository.save(approver);
    }

    @Override
    public Approver updateActiveStatus(boolean active, long id) {
        return approverRepository.updateActiveStatus(active,id);
    }

    @Override
    public void delete(Long id) {
        approverRepository.deleteById(id);
    }
}
