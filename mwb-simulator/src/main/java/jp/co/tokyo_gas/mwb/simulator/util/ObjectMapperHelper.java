package jp.co.tokyo_gas.mwb.simulator.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperHelper {
	public static ObjectMapperHelper build() {
		return new ObjectMapperHelper();
	}

	public String objToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	public <T> T objFromJson(String json, Class<T> clazz)
			throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
	
	/**
     * JSON形式の文字列をMap<String, Object>型へ変換する。
     *
     * @param jsonStr JSON形式の文字列
     * @return Map
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @SuppressWarnings("unchecked")
    public <T> Map<String, Object> jsonToMap(String jsonStr)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, Map.class);
    }

    /**
     * JSON形式の文字列をMap<String, JavaBeans>型へ変換する。
     *
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public <T> Map<String, T> jsonToMap(String jsonStr, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, T> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>(){});
        Map<String, T> result = new HashMap<>();
        for (Entry<String, T> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     *
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public <T> List<T> jsonToList(String jsonArrayStr, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, T>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<Map<String, T>>>() {
        });
        List<T> result = new ArrayList<>();
        for (Map<String, T> map : list) {
            result.add(map2pojo(map, clazz));
        }
        return result;
    }

    /**
     * map convert to javaBean
     */
    @SuppressWarnings("rawtypes")
    public <T> T map2pojo(Map map, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(map, clazz);
    }
}
