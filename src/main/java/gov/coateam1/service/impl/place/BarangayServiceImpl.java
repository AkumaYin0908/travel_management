package gov.coateam1.service.impl.place;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.place.Barangay;
import gov.coateam1.payload.place.BarangayDTO;
import gov.coateam1.repository.place.BarangayRepository;
import gov.coateam1.service.place.BarangayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BarangayServiceImpl implements BarangayService {

    private final BarangayRepository barangayRepository;
    private final ModelMapper modelMapper;

    @Override
    public BarangayDTO findByName(String name) {

        return modelMapper.map(barangayRepository.findByName(name).orElse(new Barangay(name)),BarangayDTO.class);
    }

    @Override
    public List<BarangayDTO> findAll() {
        return barangayRepository.findAll().stream().map(b->modelMapper.map(b,BarangayDTO.class)).toList();
    }

    @Override
    @Transactional
    public BarangayDTO add(BarangayDTO barangayDTO) {
        Barangay barangay=modelMapper.map(barangayDTO,Barangay.class);
        Barangay dbBarangay = barangayRepository.save(barangay);
        barangayDTO.setId(dbBarangay.getId());
        return barangayDTO;
    }

    @Override
    @Transactional
    public BarangayDTO update(BarangayDTO barangayDTO,Long id) {
        Barangay barangay=barangayRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Barangay","id",id));
        barangay.setName(barangayDTO.getName());
        barangayRepository.save(barangay);
        return modelMapper.map(barangay,BarangayDTO.class);
    }

    @Override
    public void delete(Long id) {
        barangayRepository.deleteById(id);
    }
}
