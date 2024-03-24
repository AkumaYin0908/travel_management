package gov.coateam1;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.coateam1.controller.VehicleController;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.payload.VehicleDTO;
import gov.coateam1.service.VehicleService;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(VehicleController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.yml")
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VehicleService vehicleService;

    @Value("${server.servlet.context-path}")
    private String path;

    private VehicleDTO vehicleDTO;

    private List<VehicleDTO> vehicles;


    @BeforeEach
    void setup() {
        assertThat(path).isNotBlank();
        ((MockServletContext) mockMvc.getDispatcherServlet().getServletContext()).setContextPath(path);
    }

    @BeforeEach
    public void init() {
        vehicles = new ArrayList<>();
        vehicles.add(new VehicleDTO(1L, "Toyota", "Innova", "SUV", "SJH410"));
        vehicles.add(new VehicleDTO(2L, "Ford", "Raptor", "PICKUP", "SNX123"));
        vehicles.add(new VehicleDTO(3L, "Mitsubishi", "Montero Sport", "SUV", "DCA342"));

        vehicleDTO = vehicles.get(0);
    }

    @Test
    public void testAddVehicleShouldReturn201CREATED() throws Exception {
        given(vehicleService.add(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));


        ResultActions response = mockMvc.perform(post("/coa/vehicles").contextPath(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand", CoreMatchers.is(vehicleDTO.getBrand())))
                .andExpect(jsonPath("$.model", CoreMatchers.is(vehicleDTO.getModel())))
                .andExpect(jsonPath("$.type", CoreMatchers.is(vehicleDTO.getType())))
                .andExpect(jsonPath("$.plateNo", CoreMatchers.is(vehicleDTO.getPlateNo())))
                .andDo(print());

        Mockito.verify(vehicleService, times(1)).add(vehicleDTO);

    }


    @Test
    public void testGetVehicleByPlateNoShouldReturn302Found() throws Exception {
        String plateNo = "SJH410";


        Mockito.when(vehicleService.findByPlateNo(plateNo)).thenReturn(vehicleDTO);

        ResultActions response = mockMvc.perform(createGetRequest("/vehicles/plate_no/" + plateNo)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)));

        response.andExpect(status().isFound())
                .andExpect(jsonPath("$.brand", CoreMatchers.is(vehicleDTO.getBrand())))
                .andExpect(jsonPath("$.model", CoreMatchers.is(vehicleDTO.getModel())))
                .andExpect(jsonPath("$.type", CoreMatchers.is(vehicleDTO.getType())))
                .andExpect(jsonPath("$.plateNo", CoreMatchers.is(vehicleDTO.getPlateNo())))
                .andDo(print());
    }

    @Test
    public void testGetVehicleByPlateNoShouldReturn404NOTFOUND() throws Exception {

        String plateNo = "SJH410";

        Mockito.when(vehicleService.findByPlateNo(plateNo)).thenThrow(new ResourceNotFoundException("Vehicle", "plateNo", plateNo));

        ResultActions response = mockMvc.perform(createGetRequest("/vehicles/plate_no/" + plateNo)

                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testGetVehicleByBrandShouldReturn200OK() throws Exception {
        String brand = "Toyota";

        List<VehicleDTO> vehiclesByBrand = vehicles.stream().filter(v->v.getBrand().equals(brand)).toList();
        Mockito.when(vehicleService.findByBrand(brand)).thenReturn(vehiclesByBrand);

        ResultActions response = mockMvc.perform(createGetRequest("/vehicles/brand/" + brand)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isFound())
                .andExpect(jsonPath("$[0].brand",CoreMatchers.is(brand)))
                .andDo(print());
    }

    @Test
    public void testGetVehicleByBrandShouldReturn404NOTFOUND() throws Exception {

        String brand = "Ferrari";
        Mockito.when(vehicleService.findByBrand(brand)).thenThrow(new ResourceNotFoundException("Vehicle", "brand",brand));

        ResultActions response = mockMvc.perform(createGetRequest("/vehicles/brand/" + brand)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }


    @Test
    public void testGetVehicleByModelShouldReturn200OK() throws Exception{
        String model = "Innova";

        List<VehicleDTO> vehiclesByModel = vehicles.stream().filter(v->v.getModel().equals(model)).toList();
        Mockito.when(vehicleService.findByModel(model)).thenReturn(vehiclesByModel);

        ResultActions response = mockMvc.perform(createGetRequest("/vehicles/model/" + model)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isFound())
                .andExpect(jsonPath("$[0].model",CoreMatchers.is(model)))
                .andDo(print());
    }

    @Test
    public void testGetVehicleByModelShouldReturn404NOTFOUND() throws Exception {

        String model = "Innova v2";
        Mockito.when(vehicleService.findByModel(model)).thenThrow(new ResourceNotFoundException("Vehicle", "model",model));

        ResultActions response = mockMvc.perform(createGetRequest("/vehicles/model/" + model)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testGetVehicleByTypeShouldReturn200OK() throws Exception{
        String type = "SUV";

        List<VehicleDTO> vehiclesByType = vehicles.stream().filter(v->v.getType().equals(type)).toList();
        Mockito.when(vehicleService.findByType(type)).thenReturn(vehiclesByType);

        ResultActions response = mockMvc.perform(createGetRequest("/vehicles/type/" + type)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isFound())
                .andExpect(jsonPath("$[0].brand",CoreMatchers.is("Toyota")))
                .andExpect(jsonPath("$[1].brand",CoreMatchers.is("Mitsubishi")))
                .andDo(print());
    }

    @Test
    public void testGetVehicleByTypeShouldReturn404NOTFOUND() throws Exception {

        String type = "Truck";
        Mockito.when(vehicleService.findByType(type)).thenThrow(new ResourceNotFoundException("Vehicle", "type",type));

        ResultActions response = mockMvc.perform(createGetRequest("/vehicles/type/" + type)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }



    @Test
    public void testGetAllVehicleShouldReturn200OK() throws Exception {

        Mockito.when(vehicleService.findAll()).thenReturn(vehicles);

        ResultActions response = mockMvc.perform(createGetRequest("/vehicles/all").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].plateNo", CoreMatchers.is("SJH410")))
                .andExpect(jsonPath("$[1].plateNo", CoreMatchers.is("SNX123")))
                .andExpect(jsonPath("$[2].plateNo", CoreMatchers.is("DCA342")))
                .andExpect(jsonPath("$.size()", CoreMatchers.is(vehicles.size())))
                .andDo(print());
    }



    @Test
    public void testGetAllVehicleShouldReturn204NOCONTENT() throws Exception {
        Mockito.when(vehicleService.findAll()).thenReturn(new ArrayList<>());

        ResultActions response = mockMvc.perform(createGetRequest("/vehicles/all")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }


    @Test
    public void testUpdateVehicleShouldReturn200OK() throws Exception {
        Long id = 1L;
        Mockito.when(vehicleService.update(vehicleDTO, id)).thenReturn(vehicleDTO);

        ResultActions response = mockMvc.perform(put("/coa/vehicles/{id}", id)
                .contextPath(path).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", CoreMatchers.is(vehicleDTO.getBrand())))
                .andExpect(jsonPath("$.model", CoreMatchers.is(vehicleDTO.getModel())))
                .andExpect(jsonPath("$.type", CoreMatchers.is(vehicleDTO.getType())))
                .andExpect(jsonPath("$.plateNo", CoreMatchers.is(vehicleDTO.getPlateNo())))
                .andDo(print());
    }

    @Test
    public void testUpdateVehicleShouldReturn404NOTFOUND() throws Exception{
        Long id = 12L;

        VehicleDTO vehicleDTO1 = new VehicleDTO();
        vehicleDTO1.setId(1L);
        vehicleDTO1.setBrand("Toyota");
        vehicleDTO1.setModel("Fortuner");
        vehicleDTO1.setType("SUV");

        Mockito.when(vehicleService.update(vehicleDTO, id)).thenThrow(new ResourceNotFoundException("Vehicle","id",id));

        ResultActions response = mockMvc.perform(put("/coa/vehicles/{id}", id)
                .contextPath(path).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO1)));


        response.andExpect(status().isNotFound())
                .andDo(print());
    }


    @Test
    public void testDeleteVehicleShouldReturn200OK() throws Exception {
        Long id = 1L;
        doNothing().when(vehicleService).delete(id);

        ResultActions response = mockMvc.perform(delete("/coa/vehicles/{id}", id)
                .contextPath(path)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print());
    }

    protected MockHttpServletRequestBuilder createGetRequest(String request) {
        return get(path + request).contextPath(path);
    }
}
