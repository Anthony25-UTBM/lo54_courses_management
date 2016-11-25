package com.lo54.courses_management.core.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
public class CourseSession {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Location location;

    private Date startDate;

    private Date endDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
