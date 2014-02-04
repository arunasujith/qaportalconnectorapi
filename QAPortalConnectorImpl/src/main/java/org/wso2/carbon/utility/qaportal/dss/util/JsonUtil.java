package org.wso2.carbon.utility.qaportal.dss.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    public static <T> List<T> getPOJOListFromJson(JsonNode node, TypeReference<List<T>> type) throws IOException {

       List<T> list = new ArrayList<T>();

        try {
            list = mapper.readValue(node.traverse(), type) ;

        } catch (IOException e) {

            throw e;
        }

        return list;
    }

    public static boolean isValidJSON(final String json) {

        boolean valid = false;

        try {
            final JsonParser parser = new ObjectMapper().getJsonFactory()
                    .createJsonParser(json);
            while (parser.nextToken() != null) {
            }
            valid = true;
        } catch (JsonParseException jpe) {
            jpe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return valid;
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

}
