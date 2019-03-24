package com.ivan.resident_evil.model.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GenericGenerator(strategy = "uuid", name = "uuid-string")
    @GeneratedValue(generator = "uuid-string")
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
