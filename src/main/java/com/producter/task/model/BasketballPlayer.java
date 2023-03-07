package com.producter.task.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "surname" }) })
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class BasketballPlayer extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private String surname;
    private Position position;

    public BasketballPlayer(String name, String surname, Position position) {
        this.name = name;
        this.surname = surname;
        this.position = position;
    }


}
