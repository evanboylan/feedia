package com.wcci.Feedia.model;

import javax.persistence.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;

@Entity
public class Reptile {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String species;
    private int age;
    private String sex;
    private String image;
    private float temp;
    private float humidity;
    private String description;

    @OneToOne(mappedBy = "reptile", cascade = CascadeType.ALL, orphanRemoval = true)
    public GoogleCalendar myCalendar = new GoogleCalendar();

    @OneToMany(mappedBy = "reptile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Need> needs;

    @OneToMany(mappedBy = "reptile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Schedule> schedules;

    @OneToMany(mappedBy = "reptile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Note> notes;


    public GoogleCalendar getMyCalendar() {
        return myCalendar;
    }

    public Reptile(String name, String species, int age, String sex, String image, String description, float temp, float humidity) throws GeneralSecurityException, IOException {
        this.name = name;
        this.species = species;
        this.age = age;
        this.sex = sex;
        this.image = image;
        this.description = description;
        this.temp = 0f;
        this.humidity = 0f;
    }

    public Reptile(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public Collection<Need> getNeeds() {
        return needs;
    }

    public Collection<Schedule> getSchedules() {
        return schedules;
    }

    public Collection<Note> getNotes() {
        return notes;
    }

    public float getTemp() {
        return temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void createCalendar() throws GeneralSecurityException, IOException {
        myCalendar.createCalendar(name);
    }
}