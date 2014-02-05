package org.wso2.carbon.utility.qaportal.dss.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.wso2.carbon.utility.qaportal.dss.mapping.model.MappingModel;
import org.wso2.carbon.utility.qaportal.dss.mapping.model.WSO2_QAP_PRODUCT;

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

    public static <T,E> List<E> getPOJOListFromJson(JsonNode node, TypeReference<List<T>> type,Class<E> entity) throws IOException {

        List<T> list = new ArrayList<T>();
        List<E> entityList = new ArrayList<E>();

        try {
            list = mapper.readValue(node.traverse(), type) ;

        } catch (IOException e) {

            throw e;
        }

        for(T obj:list){

            MappingModel jsonObj = (MappingModel)obj;
            entityList.add((E)jsonObj.getEntity());
        }

        return entityList;
    }

    public static JsonNode getNamedNode(String jsonString, String name) throws IOException {

        JsonNode element = null;

        try {
            JsonNode root = mapper.readTree(jsonString);

            element =  root.path(name);

        } catch (IOException e) {

            throw e;
        }

        return element;
    }

    public static String getJsonFromPojo(Object obj){

        String value = "{}";

        try {
            value = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return value;
    }

}
