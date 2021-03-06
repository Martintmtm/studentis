package com.martintmtm.studentis.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author martin
 */

@Entity
public class Course implements Serializable{
    private static final long serialVersionUID = 9178661439383356177L;
    
    @Id
    @GeneratedValue
    @NotNull(message= "Id must not be null or empty")
    @Min(value=1, message="Id must be equal or greater than 1.")
    private Integer id;
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<User> enrolledStudents;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(Set<User> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    @Override
    /**
     * equality is based only on id equality
     */
    public boolean equals(Object obj) {
        if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Course other = (Course) obj;
        if (other.getId() == null)
		return false;
        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return "Class{" + "id=" + id + ", name=" + name + '}';
    }
    
    
    
}
