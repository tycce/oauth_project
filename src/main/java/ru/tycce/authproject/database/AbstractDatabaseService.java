package ru.tycce.authproject.database;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractDatabaseService {

    protected <T> T saveOrUpdateEntity(T entity, CrudRepository<T, ?> crudRepository) {
        return crudRepository.save(entity);
    }

    protected  <T, ID> T removeEntityBy(ID id, CrudRepository<T, ID> crudRepository) {
        T entity = getEntityById(id, crudRepository);
        crudRepository.delete(entity);
        return entity;
    }

    protected <T, ID> T getEntityById(ID id, CrudRepository<T, ID> crudRepository) {
        return crudRepository.findById(id).orElse(null);
    }

    protected <T> List<T> getListEntity(CrudRepository<T, ?> crudRepository){
        return getListFromIterable(crudRepository.findAll());
    }

    private <T> List<T> getListFromIterable(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
}
