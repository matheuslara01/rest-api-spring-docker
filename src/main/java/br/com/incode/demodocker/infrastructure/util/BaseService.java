package br.com.incode.demodocker.infrastructure.util;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import br.com.incode.demodocker.application.dtos.ResponseDTO;
import br.com.incode.demodocker.application.exceptions.EntityDeleteException;
import br.com.incode.demodocker.application.exceptions.EntityPersistenceException;
import jakarta.persistence.NoResultException;

public abstract class BaseService<T, ID extends Serializable> {

    @Autowired
    private BaseRepository<T, ID> baseRepository;

    @Autowired
    private Logger logger;

    public boolean save(T entity) {
        try {
            baseRepository.saveAndFlush(entity);
            return true;
        } catch (Exception ex) {
            logger.error("BaseRepository.save: " + entity.getClass() + " Error: " + ex.getMessage());
            return false;
        }
    }

    public void save(T entity, String errorMessage) {
        try {
            baseRepository.saveAndFlush(entity);
        } catch (Exception ex) {
            logger.error("BaseRepository.save: " + entity.getClass() + " Error: " + ex.getMessage());
            throw new EntityPersistenceException(errorMessage);
        }
    }

    public boolean saveAllAndFlush(List<T> listEntity) {
        try {
            baseRepository.saveAllAndFlush(listEntity);
            return true;
        } catch (Exception ex) {
            logger.error("BaseRepository.saveAllAndFlush: " + listEntity.getClass() + " Error: " + ex.getMessage());
            return false;
        }
    }

    public void saveAllAndFlush(List<T> listEntity, String errorMessage) {
        try {
            baseRepository.saveAllAndFlush(listEntity);
        } catch (Exception ex) {
            logger.error("BaseRepository.saveAllAndFlush: " + listEntity.getClass() + " Error: " + ex.getMessage());
            throw new EntityPersistenceException(errorMessage);
        }
    }

    public List<T> saveAllAndFlushReturnList(List<T> listEntity) {
        return baseRepository.saveAllAndFlush(listEntity);
    }

    public List<T> saveAllAndFlushReturnList(List<T> listEntity, String errorMessage) {
        try {
            return baseRepository.saveAllAndFlush(listEntity);
        } catch (Exception ex) {
            logger.error(
                    "BaseRepository.saveAllAndFlushReturnList: " + listEntity.getClass() + " Error: "
                            + ex.getMessage());
            throw new EntityPersistenceException(errorMessage);
        }

    }

    public T saveReturnEntity(T entity) {
        return baseRepository.saveAndFlush(entity);
    }

    public T saveReturnEntity(T entity, String errorMessage) {
        try {
            return baseRepository.saveAndFlush(entity);
        } catch (Exception ex) {
            logger.error("BaseRepository.saveReturnEntity: " + entity.getClass() + " Error: " + ex.getMessage());
            throw new EntityPersistenceException(errorMessage);
        }
    }

    public List<T> findAll() {
        return baseRepository.findAll();
    }

    public List<T> findAll(Specification<T> spec) {
        return baseRepository.findAll(spec);
    };

    public Page<T> findAll(Specification<T> spec, Integer page, Integer size) {
        return baseRepository.findAll(spec, PageRequest.of(page, size));
    };

    public Page<T> findAll(Integer page, Integer size) {
        return baseRepository.findAll(PageRequest.of(page, size));
    };

    public Optional<T> findByIdOptional(ID entityId) {
        return baseRepository.findById(entityId);
    }

    public T findById(ID entityId) {
        return baseRepository.findById(entityId).orElseThrow(() -> new NoResultException("Resource not found!"));
    }

    public T update(T entity) {
        return (T) baseRepository.saveAndFlush(entity);
    }

    public T updateById(T entity, ID entityId) {
        Optional<T> optional = baseRepository.findById(entityId);
        if (optional.isPresent()) {
            return (T) baseRepository.saveAndFlush(entity);
        } else {
            return null;
        }
    }

    public boolean delete(T entity) {
        try {
            baseRepository.delete(entity);
            return true;
        } catch (Exception e) {
            logger.error("BaseRepository.delete: " + entity.getClass() + " Error: " + e.getMessage());
            return false;
        }
    }

    public void delete(T entity, String errorMessage) {
        try {
            baseRepository.delete(entity);
        } catch (Exception ex) {
            logger.error("BaseRepository.delete: " + entity.getClass() + " Error: " + ex.getMessage());
            throw new EntityDeleteException(errorMessage);
        }
    }

    public ResponseDTO deleteById(ID entityId, String errorMessage) {
        try {
            baseRepository.deleteById(entityId);
            return new ResponseDTO(true, "Deleted successfully!");
        } catch (Exception e) {
            logger.error("BaseRepository.deleteById: " + entityId.getClass() + " Error: " + e.getMessage());
            throw new EntityDeleteException(errorMessage);
        }
    }

    public ResponseDTO deleteAll(List<T> listEntit, String errorMessage) {
        try {
            baseRepository.deleteAll(listEntit);
            return new ResponseDTO(true, "Deleted successfully!");
        } catch (Exception e) {
            logger.error("BaseRepository.deleteAll: " + listEntit.getClass() + " Error: " + e.getMessage());
            throw new EntityDeleteException(errorMessage);
        }
    }
}
