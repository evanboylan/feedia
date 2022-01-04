package com.wcci.Feedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Lob
    private String description;

    private int intervals;

    @ManyToOne
    @JsonIgnore
    private Reptile reptile;

    public Schedule(String name, String description, int intervals, Reptile reptile) {
        this.name = name;
        this.description = description;
        this.intervals = intervals;
        this.reptile = reptile;
    }

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getInterval() {
        return intervals;
    }

    public Reptile getReptile() {
        return reptile;
    }
}
