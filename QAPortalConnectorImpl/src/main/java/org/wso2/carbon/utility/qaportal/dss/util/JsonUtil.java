package org.wso2.carbon.utility.qaportal.dss.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.wso2.carbon.utility.qaportal.dss.mapping.model.MappingModel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kavith on 2/3/14.
 */
public class JsonUtil {

    public static DateFormat DATE_FORMAT_I;

    public static DateFormat DATE_FORMAT_II;

    private static ObjectMapper defaultMapper;

    private JsonUtil(){}

    static
    {
        defaultMapper = new ObjectMapper();
        DATE_FORMAT_I = new SimpleDateFormat("yyyy-MM-dd'+'HH:mm");
        DATE_FORMAT_II = new SimpleDateFormat("dd/MM/yyyy");
        defaultMapper.setDateFormat(DATE_FORMAT_I);
    }

    public static <T, E>  E getPOJOFromJson(JsonNode node, Class<T> mappingType, Class<E> entity) throws IOException {

        T pojo = null;

        try {
            pojo = mappingType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            if (!(node instanceof MissingNode))
            {
                pojo = defaultMapper.readValue(node.traverse(), mappingType);
            }
            else
            {
                return null;
            }

        } catch (IOException e) {
            throw e;
        }

        return (E)((MappingModel)pojo).getEntity();
    }

    public static <T, E>  E getPOJOFromJson(ObjectMapper customMapper, JsonNode node, Class<T> mappingType, Class<E> entity) throws IOException {

        T pojo = null;

        try {
            pojo = mappingType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            if (!(node instanceof MissingNode))
            {
                pojo = customMapper.readValue(node.traverse(), mappingType);
            }
            else
            {
                return null;
            }

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
    public static <T, E> List<E> getPOJOListFromJson(JsonNode node, Class<T> mappingType, Class<E> entityType){

        List<E> list = new ArrayList<E>();

        try{
            if (node.isArray())
            {
                list = JsonUtil.getPOJOList(node, defaultMapper.getTypeFactory()
                            .constructCollectionType(List.class, mappingType), mappingType, entityType);
            }else {

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

    public static <T, E> List<E> getPOJOListFromJson(ObjectMapper customMapper, JsonNode node, Class<T> mappingType, Class<E> entityType){

        List<E> list = new ArrayList<E>();

        try{
            if(node.isArray())
            {
                list = JsonUtil.getPOJOList(node, customMapper.getTypeFactory()
                        .constructCollectionType(List.class, mappingType), mappingType, entityType);
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

    public static <T,E> List<E> getPOJOList(JsonNode node, CollectionType listType,
                                                        Class<T> mappingType, Class<E> entity) throws IOException {

        List<T> list = new ArrayList<T>();
        List<E> entityList = new ArrayList<E>();

        try {
            list = defaultMapper.readValue(node.traverse(), listType) ;

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
            JsonNode root = defaultMapper.readTree(jsonString);

            element =  root.path(name);

        } catch (IOException e) {

            throw e;
        }

        return element;
    }

    public static String getJsonFromPojo(Object obj){

        String value = "{}";

        try {
            value = defaultMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return value;
    }

    public static ObjectMapper getDefaultMapper() {
        return defaultMapper;
    }

}
