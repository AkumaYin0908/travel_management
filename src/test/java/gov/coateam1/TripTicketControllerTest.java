package gov.coateam1;


import com.fasterxml.jackson.databind.ObjectMapper;
import gov.coateam1.model.trip_ticket.TripTicket;
import gov.coateam1.payload.PositionDTO;
import gov.coateam1.payload.PurposeDTO;
import gov.coateam1.payload.VehicleDTO;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.*;
import gov.coateam1.payload.trip_ticket.TripDistanceDTO;
import gov.coateam1.payload.trip_ticket.TripFuelDTO;
import gov.coateam1.payload.trip_ticket.TripTicketDTO;
import gov.coateam1.payload.trip_ticket.TripTimeDTO;
import gov.coateam1.service.TripTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(TripTicket.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.yml")
public class TripTicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TripTicketService tripTicketService;

    @Value("${server.servlet.context-path}")
    private String path;

    private TripTicketDTO tripTicketDTO;

    private List<TripTicketDTO> tripTickets;

    @BeforeEach
    void setup() {
        assertThat(path).isNotBlank();
        ((MockServletContext) mockMvc.getDispatcherServlet().getServletContext()).setContextPath(path);
    }

    @BeforeEach
    public void init(){
        List<TripTimeDTO> tripTimeDTOs = List.of(
                new TripTimeDTO(1L,"07:30 AM","09:00 AM", "03:00 PM", "05:00 PM"),
                new TripTimeDTO(2L,"06:00 AM","10:00 AM", "04:00 PM", "07:00 PM"),
                new TripTimeDTO(3L,"08:00 AM","9:00 AM", "04:00 PM", "05:00 PM")
        );

        List<MunicipalityDTO> municipalityDTOs = List.of(
                new MunicipalityDTO("175201", "Baco", "1752"),
                new MunicipalityDTO("175202", "Bansud", "1752"),
                new MunicipalityDTO("175203", "Bongabong", "1752"),
                new MunicipalityDTO("175204", "Bulalacao (San Pedro)", "1752"),
                new MunicipalityDTO("175206", "Gloria", "1752"),
                new MunicipalityDTO("175207", "Mansalay", "1752"),
                new MunicipalityDTO("175208", "Naujan", "1752"),
                new MunicipalityDTO("175209", "Pinamalayan", "1752"),
                new MunicipalityDTO("175210", "Pola", "1752"),
                new MunicipalityDTO("175211", "Puerto Galera", "1752"),
                new MunicipalityDTO("175212", "Roxas", "1752"),
                new MunicipalityDTO("175213", "San Teodoro", "1752"),
                new MunicipalityDTO("175214", "Socorro", "1752"),
                new MunicipalityDTO("175215", "Victoria", "1752")
        );

        ProvinceDTO provinceDTO = new ProvinceDTO("1752","Oriental Mindoro", "17");

        RegionDTO regionDTO = new RegionDTO("17","MIMAROPA");


        List<PlaceDTO> placeDTOS = List.of(
                new PlaceDTO(1L,"N/A",null,municipalityDTOs.get((int)(Math.random() * municipalityDTOs.size())),provinceDTO,regionDTO),
                new PlaceDTO(2L,"N/A",null,municipalityDTOs.get((int)(Math.random() * municipalityDTOs.size())),provinceDTO,regionDTO),
                new PlaceDTO(3L,"N/A",null,municipalityDTOs.get((int)(Math.random() * municipalityDTOs.size())),provinceDTO,regionDTO),
                new PlaceDTO(4L,"to any point of Calapan City")
        );

        PositionDTO driverPosition = new PositionDTO(1L,"Administrative Aide I(Driver III)");

        EmployeeDTO driver = new EmployeeDTO(3L, "Rowan Atkinsons", driverPosition);

        PositionDTO passengerPosition = new PositionDTO(2L,"Administrative Aide III");
        List<EmployeeDTO> passengers = List.of(
                new EmployeeDTO(1L,"John Augustus",passengerPosition),
                new EmployeeDTO(2L,"Akuma Yin",passengerPosition)
        );

        List<TripFuelDTO> tripFuelDTOs = List.of(
                new TripFuelDTO(1L,new BigDecimal(23),new BigDecimal(0),new BigDecimal(0),new BigDecimal(12),new BigDecimal(11)),
                new TripFuelDTO(2L,new BigDecimal(11),new BigDecimal(30),new BigDecimal(0),new BigDecimal(20),new BigDecimal(21)),
                new TripFuelDTO(2L,new BigDecimal(20),new BigDecimal(0),new BigDecimal(0),new BigDecimal(8.53),new BigDecimal(11.47))
        );

        BigDecimal gearOil = new BigDecimal(0);
        BigDecimal lubricantOil = new BigDecimal(0);

        List<TripDistanceDTO> tripDistanceDTOs = List.of(
                new TripDistanceDTO(1L, 123456L,123500L,44),
                new TripDistanceDTO(2L, 123500L,123520L,20),
                new TripDistanceDTO(3L, 123520L,123600L,80)
        );

        String remark = "N/A";

        PurposeDTO purposeDTO = new PurposeDTO(1L, "to convey staff");

        VehicleDTO vehicleDTO = new VehicleDTO(1L,"Toyota","Innova","SUV", "SJH410");






        tripTickets = List.of(
                new TripTicketDTO(1L,driver,"January 03, 2024", "January 03, 2024", tripTimeDTOs.get(0),
                        placeDTOS.stream().filter(place->place.getId().equals(1L)).collect(Collectors.toSet()),
                        passengers.stream().filter(passenger -> passenger.getName().equals("John Augustus")).collect(Collectors.toSet()),
                        tripFuelDTOs.get(0),
                        gearOil,lubricantOil,
                        tripDistanceDTOs.get(0),
                        remark, purposeDTO,vehicleDTO),
                new TripTicketDTO(2L,driver,"January 04, 2024", "January 04, 2024", tripTimeDTOs.get(1),
                        placeDTOS.stream().filter(place->place.getId().equals(2L)).collect(Collectors.toSet()),
                        passengers.stream().filter(passenger -> passenger.getName().equals("Akuma Yin")).collect(Collectors.toSet()),
                        tripFuelDTOs.get(1),
                        gearOil,lubricantOil,
                        tripDistanceDTOs.get(1),
                        remark, purposeDTO,vehicleDTO),
                new TripTicketDTO(3L,driver,"January 06, 2024", "January 06, 2024", tripTimeDTOs.get(2),
                        placeDTOS.stream().filter(place->place.getId().equals(3L)).collect(Collectors.toSet()),
                        passengers.stream().filter(passenger -> passenger.getName().equals("John Augustus")).collect(Collectors.toSet()),
                        tripFuelDTOs.get(2),
                        gearOil,lubricantOil,
                        tripDistanceDTOs.get(2),
                        remark, purposeDTO,vehicleDTO)

        );

    }




}
