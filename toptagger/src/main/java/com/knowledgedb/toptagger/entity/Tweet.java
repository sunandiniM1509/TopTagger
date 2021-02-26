package com.knowledgedb.toptagger.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tweet")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String Name;

    @Column(name = "description")
    private String Description;

    public Tweet() {
    }

    public Tweet(String name, String description) {
        super();
        this.Name = name;
        this.Description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", name=" + Name + ", description='" + Description+ '\'' +'}';
    }
}