package com.producter.task.model;

public class BasketballPlayerInput {
    private String name;
    private String surname;
    private Position position;

    public BasketballPlayer toEntity() {
        return new BasketballPlayer(name, surname, position);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
