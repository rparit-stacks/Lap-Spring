package com.rohit.journalApp.Entity;

import java.time.LocalDateTime;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@Document(collection = "journals")
public class JournalEntity {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime Date;

    public String getIds() {
        return id.toString();
    }

    JournalEntity(){
        this.title = "[Please Enter Your title]";
        this.content = "[Please Enter Your Content]";

        ObjectId Id = new ObjectId();
        this.id = Id;
        Date = LocalDateTime.now();
    }
}
