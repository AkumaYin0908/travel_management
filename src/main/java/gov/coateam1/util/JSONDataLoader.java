package gov.coateam1.util;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.coateam1.exception.JSONDataException;
import gov.coateam1.model.place.Barangay;
import gov.coateam1.payload.BasicDTO;
import gov.coateam1.payload.place.BarangayDTO;
import gov.coateam1.payload.place.MunicipalityDTO;
import gov.coateam1.payload.place.ProvinceDTO;
import gov.coateam1.payload.place.RegionDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JSONDataLoader<T extends BasicDTO> {

    private final ObjectMapper objectMapper;

    public Set<T> fetchAsSet(String fileName,Class<T> clazz) throws Exception {
        Set<T>  set = new HashSet<>();
        JsonNode jsonNode;
        try(InputStream inputStream = TypeReference.class.getResourceAsStream(fileName)){
            jsonNode = objectMapper.readValue(inputStream,JsonNode.class);
        }catch (IOException ex){
            throw  new JSONDataException(fileName,clazz.getName());
        }
        Iterator<JsonNode> iterator = jsonNode.elements();
        while(iterator.hasNext()){
           set.add(createObjectFROMJSON(jsonNode,clazz));
        }
        return set;
    }

    public List<T> fetchAsList(String fileName,Class<T> clazz) throws Exception {
        List<T> list=new ArrayList<>();
        JsonNode jsonNode;
        try(InputStream inputStream = TypeReference.class.getResourceAsStream(fileName)){
            jsonNode = objectMapper.readValue(inputStream,JsonNode.class);
        }catch (IOException ex){
            throw  new JSONDataException(fileName,clazz.getName());
        }
        Iterator<JsonNode> iterator = jsonNode.elements();
        while(iterator.hasNext()){
            list.add(createObjectFROMJSON(jsonNode,clazz));
        }
        return list;
    }



    private T createObjectFROMJSON(JsonNode jsonNode,Class<T> clazz) throws Exception{
        T type = clazz.getDeclaredConstructor().newInstance();

        if (type instanceof BarangayDTO){
            String brgyCode = jsonNode.get("brgy_code").asText();
            String brgyName = jsonNode.get("brgy_name").asText();
            String cityCode = jsonNode.get("city_code").asText();

            type.setCode(brgyCode);
            type.setName(brgyName);
            ((BarangayDTO) type).setCityCode(cityCode);
        }else if(type instanceof MunicipalityDTO){
            String cityCode = jsonNode.get("city_code").asText();
            String cityName = jsonNode.get("city_name").asText();
            String provinceCode = jsonNode.get("province_code").asText();

            type.setCode(cityCode);
            type.setName(cityName);
            ((MunicipalityDTO) type).setProvinceCode(provinceCode);
        }else if(type instanceof ProvinceDTO){
            String provinceCode = jsonNode.get("province_code").asText();
            String provinceName = jsonNode.get("province_name").asText();
            String regionCode = jsonNode.get("region_code").asText();

            type.setCode(provinceCode);
            type.setName(provinceName);
            ((ProvinceDTO) type).setRegionCode(regionCode);
        }else if(type instanceof RegionDTO){
            String regionCode = jsonNode.get("region_code").asText();
            String regionName=jsonNode.get("region_name").asText();

            type.setCode(regionCode);
            type.setName(regionName);
        }

        return type;

    }







}
