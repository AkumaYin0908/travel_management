package gov.coateam1.service.impl;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.ReportTo;
import gov.coateam1.payload.ReportToDTO;
import gov.coateam1.payload.SignatoryDTO;
import gov.coateam1.repository.ReportToRepository;
import gov.coateam1.service.ReportToService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportToServiceImpl implements ReportToService {

    private final ReportToRepository reportToRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<ReportToDTO> findAll() {
        return reportToRepository.findAll().stream().map(r->modelMapper.map(r,ReportToDTO.class)).toList();
    }

    @Override
    public ReportToDTO findByName(String name) {
        return modelMapper.map(reportToRepository.findByName(name).orElse(new ReportTo(name)), ReportToDTO.class);
    }

    @Override
    @Transactional
    public ReportToDTO add(ReportToDTO reportToDTO) {
        ReportTo reportTo = modelMapper.map(reportToDTO,ReportTo.class);
        ReportTo dbReportTo = reportToRepository.save(reportTo);
        reportToDTO.setId(dbReportTo.getId());
        return reportToDTO;
    }

    @Override
    @Transactional
    public ReportToDTO update(ReportToDTO reportToDTO, Long id) {
       ReportTo reportTo = reportToRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("ReportTo","id",id));
       reportTo.setName(reportTo.getName());
       reportToRepository.save(reportTo);
       reportToDTO.setId(id);
       return reportToDTO;

    }

    @Override
    public void delete(Long id) {

    }
}
