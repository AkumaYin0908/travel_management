package gov.coateam1;


import com.fasterxml.jackson.databind.ObjectMapper;
import gov.coateam1.controller.TripTicketController;
import gov.coateam1.exception.ResourceNotFoundException;
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
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TripTicketController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.yml")
public class TripTicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TripTicketService tripTicketService;

    @Value("${server.servlet.context-path}")
    private String path;

    private TripTicketDTO tripTicketDTO;

    private List<TripTicketDTO> tripTickets;

    private PlaceDTO placeDTO;

    private EmployeeDTO passenger;

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
                new PlaceDTO(1L,"N/A",null,municipalityDTOs.get((int)(Math.random() * municipalityDTOs.size())),provinceDTO,regionDTO,"N/A"),
                new PlaceDTO(2L,"N/A",null,municipalityDTOs.get((int)(Math.random() * municipalityDTOs.size())),provinceDTO,regionDTO ,"N/A"),
                new PlaceDTO(3L,"N/A",null,municipalityDTOs.get((int)(Math.random() * municipalityDTOs.size())),provinceDTO,regionDTO,"N/A"),
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

        tripTicketDTO = tripTickets.get(0);

    }

    @Test
    public void testGetAllTripTicketShouldReturn200OK() throws Exception{
        Mockito.when(tripTicketService.findAll()).thenReturn(tripTickets);

        ResultActions response = mockMvc.perform(createGetRequest("/triptickets/all").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", CoreMatchers.is(1)))
                .andExpect(jsonPath("$[1].id", CoreMatchers.is(2)))
                .andExpect(jsonPath("$[2].id", CoreMatchers.is(3)))
                .andDo(print());
    }

    @Test
    public void testGetAllTripTicketShouldReturn204NOCONTENT() throws Exception{
        Mockito.when((tripTicketService.findAll())).thenReturn(new ArrayList<>());

        ResultActions response = mockMvc.perform(createGetRequest("/triptickets/all").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void testAddTripTicketShouldReturn201CREATED() throws Exception{


        given(tripTicketService.add(ArgumentMatchers.anyLong(),ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(1));


        ResultActions response = mockMvc.perform(post("/coa/employees/{id}/triptickets", tripTicketDTO.getEmployee().getId()).contextPath(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripTicketDTO)));

        response.andExpect(status().isCreated())
                .andDo(print());

        Mockito.verify(tripTicketService,times(1)).add(ArgumentMatchers.anyLong(),ArgumentMatchers.any());
    }

    @Test
    public void testGetTripTicketByIdShouldReturn302FOUND() throws Exception{
        Long id = 1L;

        Mockito.when(tripTicketService.findById(id)).thenReturn(tripTicketDTO);

        placeDTO = tripTicketDTO.getPlaces().stream().findFirst().orElse(null);

        passenger = tripTicketDTO.getPassengers().stream().findFirst().orElse(null);

        ResultActions response = mockMvc.perform(createGetRequest("/triptickets/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripTicketDTO)));

        response.andExpect(status().isFound())
                .andExpect(jsonPath("$.employee.id").value(tripTicketDTO.getEmployee().getId().intValue()))
                .andExpect(jsonPath("$.dateDeparture", CoreMatchers.is(tripTicketDTO.getDateDeparture())))
                .andExpect(jsonPath("$.dateReturn", CoreMatchers.is((tripTicketDTO.getDateReturn()))))
                .andExpect(jsonPath("$.tripTime.tripTimeId").value(tripTicketDTO.getTripTime().getTripTimeId().intValue()))
                .andExpect(jsonPath("$.places[0].id").value(placeDTO.getId().intValue()))
                .andExpect(jsonPath("$.passengers[0].id").value(passenger.getId().intValue()))
                .andExpect(jsonPath("$.tripFuel.tripFuelId").value(tripTicketDTO.getTripFuel().getTripFuelId().intValue()))
                .andExpect(jsonPath("$.gearOil", CoreMatchers.is(tripTicketDTO.getGearOil().intValue())))
                .andExpect(jsonPath("$.lubricantOil", CoreMatchers.is(tripTicketDTO.getLubricantOil().intValue())))
                .andExpect(jsonPath("$.tripDistance.tripDistanceId").value(tripTicketDTO.getTripDistance().getTripDistanceId().intValue()))
                .andExpect(jsonPath("$.remarks", CoreMatchers.is(tripTicketDTO.getRemarks())))
                .andExpect(jsonPath("$.purpose.id").value(tripTicketDTO.getPurpose().getId().intValue()))
                .andExpect(jsonPath("$.vehicle.id").value(tripTicketDTO.getVehicle().getId().intValue()))
                .andDo(print());

    }

    @Test
    public void testGetTripTicketByIdShouldReturn404NOTFOUND() throws Exception{
        Long id = 1L;

        Mockito.when(tripTicketService.findById(id)).thenThrow(new ResourceNotFoundException("TripTicket", "id",id));



        ResultActions response = mockMvc.perform(createGetRequest("/triptickets/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripTicketDTO)));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testUpdateTripTicketShouldReturn200OK() throws Exception{
        Long id =1L;

        Mockito.when(tripTicketService.update(tripTicketDTO,id)).thenReturn(tripTicketDTO);

        placeDTO = tripTicketDTO.getPlaces().stream().findFirst().orElse(null);

        passenger = tripTicketDTO.getPassengers().stream().findFirst().orElse(null);

        ResultActions response = mockMvc.perform(put("/coa/triptickets/{id}",id)
                .contextPath(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripTicketDTO)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(tripTicketDTO.getId().intValue()))
                .andExpect(jsonPath("$.employee.id").value(tripTicketDTO.getEmployee().getId().intValue()))
                .andExpect(jsonPath("$.dateDeparture", CoreMatchers.is(tripTicketDTO.getDateDeparture())))
                .andExpect(jsonPath("$.dateReturn", CoreMatchers.is((tripTicketDTO.getDateReturn()))))
                .andExpect(jsonPath("$.tripTime.tripTimeId").value(tripTicketDTO.getTripTime().getTripTimeId().intValue()))
                .andExpect(jsonPath("$.places[0].id").value(placeDTO.getId().intValue()))
                .andExpect(jsonPath("$.passengers[0].id").value(passenger.getId().intValue()))
                .andExpect(jsonPath("$.tripFuel.tripFuelId").value(tripTicketDTO.getTripFuel().getTripFuelId().intValue()))
                .andExpect(jsonPath("$.gearOil", CoreMatchers.is(tripTicketDTO.getGearOil().intValue())))
                .andExpect(jsonPath("$.lubricantOil", CoreMatchers.is(tripTicketDTO.getLubricantOil().intValue())))
                .andExpect(jsonPath("$.tripDistance.tripDistanceId").value(tripTicketDTO.getTripDistance().getTripDistanceId().intValue()))
                .andExpect(jsonPath("$.remarks", CoreMatchers.is(tripTicketDTO.getRemarks())))
                .andExpect(jsonPath("$.purpose.id").value(tripTicketDTO.getPurpose().getId().intValue()))
                .andExpect(jsonPath("$.vehicle.id").value(tripTicketDTO.getVehicle().getId().intValue()))
                .andDo(print());
    }

    @Test
    public void testUpdateTripTicketShouldReturn404NOTFOUND() throws Exception{
        Long id = 12L;

        Mockito.when(tripTicketService.update(tripTicketDTO,id)).thenThrow(new ResourceNotFoundException("TripTicket", "id", id));

        ResultActions response = mockMvc.perform(put("/coa/triptickets/{id}",id)
                .contextPath(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripTicketDTO)));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testDeleteTripTicketShouldReturn200OK() throws Exception {
        Long id = 1L;
        doNothing().when(tripTicketService).delete(id);

        ResultActions response = mockMvc.perform(delete("/coa/tripticket/{id}",id)
                .contextPath(path)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print());

    }

    protected MockHttpServletRequestBuilder createGetRequest(String request) {
        return get(path + request).contextPath(path);
    }







}
