package org.wso2.carbon.utility.qaportal.dss.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.wso2.carbon.utility.qaportal.dss.mapping.model.MappingModel;
import org.wso2.carbon.utility.qaportal.dss.mapping.model.WSO2_QAP_PRODUCT;
import org.wso2.carbon.utility.qaportal.dss.mapping.model.WSO2_QAP_TEST_SUIT;
import org.wso2.carbon.utility.qaportal.model.TestSuit;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'+'HH:mm");
        mapper.setDateFormat(df);
    }

    public static <T,E>  E getPOJOFromJson(JsonNode node, Class<T> mappingType, Class<E> entity) throws IOException {

        T pojo = null;

        try {

            pojo = mapper.readValue(node.traverse(), mappingType);

        } catch (IOException e) {


            throw e;
        }

        return (E)((MappingModel)pojo).getEntity();
    }

    /**
     *
     * See below link to Get an understanding jackson type factory
     * http://stackoverflow.com/questions/6846244/jackson-and-generic-type-reference?lq=1
     *
     * @param node
     * @param mappingType
     * @param entityType
     *
     * @return
     */
    public static <T,E> List<E> getPOJOListFromJson(JsonNode node, Class<T> mappingType, Class<E> entityType){

        List<E> list = new ArrayList<E>();

        try{
            if(node.isArray())
            {
                list = JsonUtil.getPOJOList(node, mapper.getTypeFactory().constructCollectionType(List.class, mappingType), mappingType, entityType);
            }else{

                list.add(JsonUtil.getPOJOFromJson(node, mappingType, entityType));
            }
        }
        catch (JsonMappingException jme){
            jme.printStackTrace();
        }
        catch (JsonParseException jpe){
            jpe.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static <T,E> List<E> getPOJOList(JsonNode node, CollectionType listType, Class<T> mappingType, Class<E> entity) throws IOException {

        List<T> list = new ArrayList<T>();
        List<E> entityList = new ArrayList<E>();
        Class clz = listType.getContentType().getRawClass();

        try {
            list = mapper.readValue(node.traverse(), listType) ;

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
