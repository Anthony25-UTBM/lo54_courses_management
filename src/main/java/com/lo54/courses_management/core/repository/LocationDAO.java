package com.lo54.courses_management.core.repository;


import com.lo54.courses_management.core.entity.Location;

import java.util.List;

public class LocationDAO extends DefaultDAO<Location>{
    public LocationDAO() {
        super();
        entityType = Location.class;
    }
}
