package gov.coateam1.service;


import gov.coateam1.model.Approver;
import gov.coateam1.repository.ApproverRepository;

import java.util.List;


public interface ApproverService {


    List<Approver> findAll();

    Approver findByActiveStatus(boolean active);

    Approver add(Approver approver);

    Approver update(Approver approver);

    Approver updateActiveStatus(boolean active,long id);

    void delete(Long id);

}
