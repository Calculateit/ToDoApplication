package ru.dmitriidaragan.todo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "task")
public class Task {
    @SuppressWarnings("unused")
    public Long getId() {
        return id;
    }
    @SuppressWarnings("unused")
    public void setId(Long id) {
        this.id = id;
    }
    @SuppressWarnings("unused")
    public String getTitle() {
        return title;
    }
    @SuppressWarnings("unused")
    public void setTitle(String title) {
        this.title = title;
    }
    @SuppressWarnings("unused")
    public String getDescription() {
        return description;
    }
    @SuppressWarnings("unused")
    public void setDescription(String description) {
        this.description = description;
    }
    @SuppressWarnings("unused")
    public Boolean getCrossed() {
        return isCrossed;
    }

    public void setCrossed(Boolean crossed) {
        isCrossed = crossed;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Boolean isCrossed;

    public Task() {}
    public Task(Long id, String title, String description, Boolean isCrossed) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.isCrossed = isCrossed;
    }
}
