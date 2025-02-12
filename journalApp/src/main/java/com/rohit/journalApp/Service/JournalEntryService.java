package com.rohit.journalApp.Service;

import com.rohit.journalApp.Entity.JournalEntity;
import com.rohit.journalApp.Entity.Users;
import com.rohit.journalApp.Repos.JournalEntryRepository;
import com.rohit.journalApp.Repos.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserRepository userRepository;

    // Fetch all journal entries
    public ResponseEntity<?> getJournalEntriesOfAllUsers() {
        List<JournalEntity> allJournals = journalEntryRepository.findAll();
        if (allJournals.isEmpty()) {
            return new ResponseEntity<>("No Journal Entries found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allJournals, HttpStatus.OK);
    }

    // Fetch journals for a specific user by username
    public ResponseEntity<?> getJournalEntriesOfUserName(String username) {
        Users oldUser = userRepository.findUsersByName(username);
        return getResponseEntity(oldUser);
    }

    // Fetch journals for a specific user by user ID
    public ResponseEntity<?> getJournalEntriesOfId(ObjectId myId) {
        Users oldUser = userRepository.findUsersByUserId(myId);
        return getResponseEntity(oldUser);
    }

    // Add a journal entry for a user by username
    public ResponseEntity<?> setJournalOfUserName(String userName, JournalEntity myEntry) {
        Users oldUser = userRepository.findUsersByName(userName);
        return setResponseEntity(oldUser, myEntry);
    }

    // Add a journal entry for a user by user ID
    public ResponseEntity<?> setJournalOfUserID(ObjectId myId, JournalEntity myEntry) {
        Users oldUser = userRepository.findUsersByUserId(myId);
        return setResponseEntity(oldUser, myEntry);
    }

    // Update a journal entry for a user by username
    public ResponseEntity<?> updateJournalOfUserName(String userName, JournalEntity myEntry) {
        Users oldUser = userRepository.findUsersByName(userName);
        return updateResponseEntity(oldUser, myEntry);
    }

    // Update a journal entry for a user by user ID
    public ResponseEntity<?> updateJournalOfUserID(ObjectId myId, JournalEntity myEntry) {
        Users oldUser = userRepository.findUsersByUserId(myId);
        return updateResponseEntity(oldUser, myEntry);
    }

    // Delete a journal entry of a user
    public ResponseEntity<?> deleteJournalOfUserID(ObjectId journalId, String username) {
        Users oldUser = userRepository.findUsersByName(username);

        if (oldUser == null || oldUser.getMyJournal() == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        List<JournalEntity> journals = oldUser.getMyJournal();
        boolean removed = journals.removeIf(journal -> journal.getId().equals(journalId));

        if (!removed) {
            return new ResponseEntity<>("Journal entry not found", HttpStatus.NOT_FOUND);
        }

        userRepository.save(oldUser);
        return new ResponseEntity<>("Journal entry deleted successfully", HttpStatus.OK);
    }

    // Helper method to get a response entity
    private ResponseEntity<?> getResponseEntity(Users oldUser) {
        if (oldUser == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if (oldUser.getMyJournal().isEmpty()) {
            return new ResponseEntity<>("No journal entries found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(oldUser.getMyJournal(), HttpStatus.OK);
    }

    // Helper method to add a journal entry
    private ResponseEntity<?> setResponseEntity(Users oldUser, JournalEntity myEntry) {
        if (oldUser == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        oldUser.getMyJournal().add(myEntry);
        userRepository.save(oldUser);
        return new ResponseEntity<>(oldUser, HttpStatus.OK);
    }

    // Helper method to update a journal entry
    private ResponseEntity<?> updateResponseEntity(Users oldUser, JournalEntity myEntry) {
        if (oldUser == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        List<JournalEntity> journals = oldUser.getMyJournal();
        if (journals.isEmpty()) {
            return new ResponseEntity<>("No journal entries found", HttpStatus.NOT_FOUND);
        }

        // Find and update the journal entry by matching the ID
        for (int i = 0; i < journals.size(); i++) {
            if (journals.get(i).getId().equals(myEntry.getId())) {
                journals.set(i, myEntry);
                userRepository.save(oldUser);
                return new ResponseEntity<>("Journal entry updated successfully", HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("Journal entry not found", HttpStatus.NOT_FOUND);
    }
}
