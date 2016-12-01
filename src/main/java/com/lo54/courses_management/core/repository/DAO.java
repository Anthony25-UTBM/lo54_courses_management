package com.lo54.courses_management.core.repository;

import com.lo54.courses_management.core.entity.Item;

import java.util.List;


public interface DAO {

    /**
     * To insert an entity into the database
     * @param entity
     */
    void insertEntity(final Item entity);

    /**
     * To update an entity into the database
     * @param id
     * @param entity
     */
    void updateEntity(final int id, final Item entity);

    /**
     * To remove an entity into the database
     * @param id
     */
    void removeEntity(final int id);

    /**
     * To get an entity from an id
     * @param id
     * @return
     */
    Item getEntity(final int id);


    /**
     * To get all the entities
     * @return
     */
    List<Item> getEntities();
}
