package com.dbc.api.domain;

import jakarta.persistence.*;

@Table(name = "ranking")
@Entity
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detective_id")
    private Long detectiveId;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "points")
    private int points;

    // Default constructor (required by JPA)
    public Ranking() {
    }

    // Constructor with parameters
    public Ranking(String name, int points) {
        this.name = name;
        this.points = points;
    }

    // Getters and setters
    public Long getDetectiveId() {
        return detectiveId;
    }

    public void setDetectiveId(Long detectiveId) {
        this.detectiveId = detectiveId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
