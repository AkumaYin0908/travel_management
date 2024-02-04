package gov.coateam1.service;


import gov.coateam1.model.Approver;
import gov.coateam1.repository.ApproverRepository;



public interface ApproverService {


    Approver findApproverByActiveStatus(boolean active);

    Approver addApprover(Approver approver);

    Approver updateApprover(Approver approver);

    Approver updateApproverActiveStatus(boolean active,long id);

    void deleteApprover(Long id);

}
