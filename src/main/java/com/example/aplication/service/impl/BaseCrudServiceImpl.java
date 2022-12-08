package com.example.aplication.service.impl;

import com.example.aplication.domain.BaseEntity;
import com.example.aplication.exception.BusinessException;
import com.example.aplication.exception.NoContentException;
import com.example.aplication.service.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class BaseCrudServiceImpl<E extends BaseEntity, T extends JpaRepository<E, Long>>
        implements CrudService<E> {

    protected T repository;

    public BaseCrudServiceImpl(T repository){
        this.repository = repository;
    }

    @Override
    public List<E> findAll() {
        return this.repository.findAll();
    }

    @Override
    public E findById(Long id) {
        return this.repository.findById(id).orElseThrow(NoContentException::new);
    }

    @Override
    public void insert(E entity) {

        //método alternativo para criação do id
        //entity.setId(ThreadLocalRandom.current().nextLong(11, 204800));

        List<E> list = this.findAll();

        E max = list.get(0);
        for (E item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        entity.setId(max.getId() + 1);
        this.repository.save(entity);
    }

    @Override
    public void update(Long id, E entity) {
        E entityBd = this.findById(id);

        if (entityBd.getId().equals(entity.getId())){
            this.repository.save(entity);
        }else {
            throw new BusinessException("Identifiers for change are divergent!");
        }
    }

    @Override
    public void delete(Long id) {
    E entityBd = this.findById(id);
    this.repository.delete(entityBd);
    }
}
