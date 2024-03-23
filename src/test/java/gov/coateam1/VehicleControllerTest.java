package gov.coateam1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.coateam1.controller.VehicleController;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.payload.VehicleDTO;
import gov.coateam1.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleController.class)
@TestPropertySource(locations = "classpath:application.yml")
public class VehicleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    VehicleService vehicleService;

    @Value("${server.servlet.context-path}")
    private String path;

    @BeforeEach
    void setup(){
        assertThat(path).isNotBlank();
        ((MockServletContext)mockMvc.getDispatcherServlet().getServletContext()).setContextPath(path);
    }

    @Test
    public void testAddVehicleShouldReturn201CREATED() throws Exception {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setBrand("Toyota");
        vehicleDTO.setModel("Innova");
        vehicleDTO.setType("SUV");
        vehicleDTO.setPlateNo("SJH410");

        Mockito.when(vehicleService.add(vehicleDTO)).thenReturn(vehicleDTO.id(1L));

        String requestBody = objectMapper.writeValueAsString(vehicleDTO);


        mockMvc.perform(post("/coa/vehicles").contextPath(path).contentType("application/json")
                .content(requestBody))
                .andExpect(status().isCreated())
                .andDo(print());

        Mockito.verify(vehicleService,times(1)).add(vehicleDTO);

    }

    @Test
    public void testGetVehicleShouldReturn404NotFound() throws Exception {
        String plateNo="XXX4";
        Mockito.when(vehicleService.findByPlateNo(plateNo)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(createGetRequest("/vehicles/plate_no/" + plateNo))
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    public void testGetVehicleShouldReturn302Found() throws Exception {
        String plateNo = "SJH410";

        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(1L);
        vehicleDTO.setBrand("Toyota");
        vehicleDTO.setModel("Innova");
        vehicleDTO.setType("SUV");
        vehicleDTO.setPlateNo("SJH410");

        Mockito.when(vehicleService.findByPlateNo(plateNo)).thenReturn(vehicleDTO);

        mockMvc.perform(createGetRequest("/vehicles/plate_no/" + plateNo ))
                .andExpect(status().isFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(objectMapper.writeValueAsString(vehicleDTO)))
                .andDo(print());
    }

    protected MockHttpServletRequestBuilder createGetRequest(String request){
        return get(path + request).contextPath(path);
    }
}
