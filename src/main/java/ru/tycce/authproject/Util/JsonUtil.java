package ru.tycce.authproject.Util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import ru.tycce.authproject.database.AbstractDatabase;

import java.util.List;

@UtilityClass
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public ObjectNode getObjectNode(Object object) {
        return OBJECT_MAPPER.valueToTree(object);
    }

    public ObjectNode getEmptyNode() {return OBJECT_MAPPER.createObjectNode();}

    public <T> ArrayNode getArrayNode(List<T> objects){
        ArrayNode message = OBJECT_MAPPER.createArrayNode();
        objects.forEach(o->message.add(OBJECT_MAPPER.valueToTree(o)));
        return message;
    }

    public ObjectNode getJsonResponse(AbstractDatabase.JsonResponse jsonResponse) {
        return OBJECT_MAPPER.createObjectNode()
                .put("success", jsonResponse.isSuccess())
                .put("code", jsonResponse.getCode())
                .set("message", jsonResponse.getMessage());
    }

}
