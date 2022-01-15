package ru.tycce.authproject.database;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.tycce.authproject.Util.JsonUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AbstractDatabase {

    protected  <T> JsonResponse saveOrUpdateEntity(T entity, CrudRepository<T, ?> crudRepository) {
        JsonResponse jsonResponse;
        try {
            T entityWithId = crudRepository.save(entity);
            jsonResponse = new JsonResponse(true, 200, entityWithId);
        } catch (Exception e) {
            jsonResponse = new JsonResponse(false, 400, String.format("Ошибка сохранения записи: %s", entity));
        }
        return jsonResponse;
    }

    protected  <T, ID> JsonResponse removeEntityById(ID id, CrudRepository<T, ID> crudRepository) {
        JsonResponse jsonResponse;
        T entity = crudRepository.findById(id).orElse(null);
        if(entity == null) {
            jsonResponse = new JsonResponse(false, 400, String.format("Не удалось удалить запись с id: %s", id));
        } else {
            crudRepository.delete(entity);
            jsonResponse = new JsonResponse(true, 200, entity);
        }
        return jsonResponse;
    }

    protected <T, ID> JsonResponse getEntityById(ID id, CrudRepository<T, ID> crudRepository) {
        JsonResponse jsonResponse;
        try {
            T entity = crudRepository.findById(id).orElse(null);
            jsonResponse = new JsonResponse(true, 200, entity);
        } catch (Exception e) {
            jsonResponse = new JsonResponse(false, 400, String.format("Не удалось получить запись с id: %s", id));
        }
        return jsonResponse;
    }

    protected <T> JsonResponse getListEntity(CrudRepository<T, ?> crudRepository){
        JsonResponse jsonResponse;

        try{
            List<T> entities = getListFromIterable(crudRepository.findAll());
            jsonResponse = new JsonResponse(true, 200, entities);
        } catch (Exception e) {
            jsonResponse = new JsonResponse(false, 400, "Не удалось получить записи");
        }

        return jsonResponse;
    }

    private <T> List<T> getListFromIterable(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    @RequiredArgsConstructor
    @Getter
    public static class JsonResponse {

        private final boolean success;
        private final int code;
        private final LocalDateTime date;
        private JsonNode message;

        private JsonResponse(boolean success, int code) {
            this.date = LocalDateTime.now();
            this.success = success;
            this.code = code;
        }

        public <T> JsonResponse(boolean success, int code, T entity) {
            this(success, code);
            this.message = entity == null ? JsonUtil.getEmptyNode() : JsonUtil.getObjectNode(entity);
        }

        public <T> JsonResponse(boolean success, int code, List<T> entity) {
            this(success, code);
            this.message = JsonUtil.getArrayNode(entity);
        }
    }
}
