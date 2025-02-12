package com.rohit.journalApp.Entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
@Getter
@Setter
@Document(collection = "users")
public class Users {

    @Id
    private ObjectId userId;
    @Indexed(unique = true)
    private String name;
    private String status;
    private Integer age;
    @DBRef
    private List<JournalEntity> myJournal = new ArrayList<>();

    Users(){
        ObjectId Id = new ObjectId();
        this.userId = Id;
    }

    public String getuserId() {
        return userId.toString();
    }
}
