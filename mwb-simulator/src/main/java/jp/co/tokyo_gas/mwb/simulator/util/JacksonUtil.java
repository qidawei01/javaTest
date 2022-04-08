package jp.co.tokyo_gas.mwb.simulator.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacksonUtil {

    public static String parseString(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null)
                return leaf.asText();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    public static List<String> parseStringList(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);

            if (leaf != null)
                return mapper.convertValue(leaf, new TypeReference<List<String>>() {
                });
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static Integer parseInteger(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null)
                return leaf.asInt();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static List<Integer> parseIntegerList(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);

            if (leaf != null)
                return mapper.convertValue(leaf, new TypeReference<List<Integer>>() {
                });
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    public static Boolean parseBoolean(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null)
                return leaf.asBoolean();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static Short parseShort(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                Integer value = leaf.asInt();
                return value.shortValue();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static Byte parseByte(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                Integer value = leaf.asInt();
                return value.byteValue();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T parseObject(String body, String field, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(body);
            node = node.get(field);
            return mapper.treeToValue(node, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static Object toNode(String json) {
        if (json == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {

            return mapper.readTree(json);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public static Map<String, String> toMap(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(data, new TypeReference<Map<String, String>>() {
            });
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String toJson(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
    * JSON->List(Object）
    * @param <T> class
    * @param jsonAryStr jsonAry
    * @param clazz model
    * @return 
    */
    public static <T> List<T> getListFromJson(String jsonAryStr,Class<T> clazz) {
        try {
            return ObjectMapperHelper.build().jsonToList(jsonAryStr, clazz);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            log.error(e.toString());
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            log.error(e.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.toString());
        }
        return null;
    }
}
