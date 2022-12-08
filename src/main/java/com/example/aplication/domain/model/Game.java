package com.example.aplication.domain.model;

import com.example.aplication.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "games")
public class Game extends BaseEntity {

    private String name;

    private String cover;

    private long votes;

    @Column(length = 500)
    private String description;
}
