package com.lo54.courses_management.core.service;


import com.lo54.courses_management.core.entity.Item;

import java.util.List;

public interface Service {

    /**
     * To store an entity
     * @param entity
     */
    void storeEntity(final Item entity);

    /**
     * To update an entity
     * @param id
     * @param entity
     */
    void updateEntity(final int id, final Item entity);

    /**
     * To remove an entity
     * @param id
     */
    void removeEntity(final int id);

    /**
     * To get an entity by his id
     * @param id
     * @return
     */
    Item getEntity(final int id);

    /**
     * To get the list of entities
     * @return
     */
    List<Item> getEntities();
}
