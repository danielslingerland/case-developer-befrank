package com.befrank.casedeveloperjava.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Werkgever {
    @Id
    private Integer id;
    @Column(name="NAAM")
    private String naam;

}
