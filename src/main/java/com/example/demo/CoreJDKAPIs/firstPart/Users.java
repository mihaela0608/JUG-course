package com.example.demo.CoreJDKAPIs.firstPart;

import java.io.Serializable;
import java.util.List;

public class Users implements Serializable {
    private List<Person> users;

    public Users(List<Person> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        String result = "";
        for(Person person: users){
            result+= person.toString();
            result+=" ";
        }
        return result;
    }
}
