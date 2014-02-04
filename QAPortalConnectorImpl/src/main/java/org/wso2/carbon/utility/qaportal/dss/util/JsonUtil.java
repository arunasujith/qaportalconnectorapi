package org.wso2.carbon.utility.qaportal.dss.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kavith on 2/3/14.
 */
public class JsonUtil {

    private static ObjectMapper mapper;

    private JsonUtil(){}

    static {
        mapper = new ObjectMapper();
    }

    public static <T>  T getPOJOFromJson(String jsonString, Class<T> clz) throws IOException {

        T pojo = null;

        try {

            pojo = mapper.readValue(jsonString, clz);

        } catch (IOException e) {


            throw e;
        }

        return pojo;
    }

    public static <T> List<T> getPOJOListFromJson(String jsonString, Class<T> clz) throws IOException {

       List<T> list = new ArrayList<T>();

        try {
            list = mapper.readValue(jsonString, List.class) ;

        } catch (IOException e) {

            throw e;
        }

        return list;
    }

    public static String getNamedElementAsText(String jsonString, String name) throws IOException {

        String json = "{}";

        try {
            JsonNode root = mapper.readTree(jsonString);

            json = root.get(name).asText();

        } catch (IOException e) {

            throw e;
        }

        return json;
    }
}
