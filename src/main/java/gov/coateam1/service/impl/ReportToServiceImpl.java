package gov.coateam1.service.impl;

import gov.coateam1.model.ReportTo;
import gov.coateam1.repository.ReportToRepository;
import gov.coateam1.service.ReportToService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportToServiceImpl implements ReportToService {

    private final ReportToRepository reportToRepository;
    @Override
    public List<ReportTo> findAll() {
        return reportToRepository.findAll();
    }

    @Override
    public ReportTo findByName(String name) {
        return reportToRepository.findByName(name).orElse(new ReportTo(name));
    }

    @Override
    public ReportTo add(ReportTo reportTo) {
        return null;
    }

    @Override
    public ReportTo update(ReportTo reportTo) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
