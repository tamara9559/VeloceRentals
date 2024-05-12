package co.edu.cue.velocerentals.model;

import java.util.ArrayList;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;

    private ArrayList record;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList getRecord() {
        return record;
    }

    public void setRecord(ArrayList record) {
        this.record = record;
    }
}
