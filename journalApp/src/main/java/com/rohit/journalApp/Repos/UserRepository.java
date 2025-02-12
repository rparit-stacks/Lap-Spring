package com.rohit.journalApp.Repos;

import com.rohit.journalApp.Entity.Users;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<Users, ObjectId> {
    Users findUsersByName(String name);

    Users findUsersByUserId(ObjectId userId);

    void deleteUsersByName(String userName);

    void deleteUsersByUserId(ObjectId userId);
}
