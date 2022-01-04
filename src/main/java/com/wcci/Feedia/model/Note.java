package com.wcci.Feedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Lob
    private String description;

    private boolean isPinned;

    @ManyToOne
    @JsonIgnore
    private Reptile reptile;

    public Note(String name, String description, boolean isPinned, Reptile reptile) {
        this.name = name;
        this.description = description;
        this.isPinned = isPinned;
        this.reptile = reptile;
    }

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public Reptile getReptile() {
        return reptile;
    }

    public void setReptile(Reptile reptile) {
        this.reptile = reptile;
    }
}
