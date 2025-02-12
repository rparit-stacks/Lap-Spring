package com.rohit.journalApp.Controllers;

import com.rohit.journalApp.Entity.JournalEntity;
import com.rohit.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journal-entries")
public class JournalController {

    @Autowired
    private JournalEntryService journalEntryService;

    // Fetch all journal entries
    @GetMapping
    public ResponseEntity<?> getJournalEntriesOfAllUsers() {
        return journalEntryService.getJournalEntriesOfAllUsers();
    }

    // Fetch journals of a specific user by username
    @GetMapping("{username}")
    public ResponseEntity<?> getJournalEntriesOfUserName(@PathVariable String username) {
        return journalEntryService.getJournalEntriesOfUserName(username);
    }

    // Fetch journals of a specific user by ID
    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntriesOfId(@PathVariable ObjectId myId) {
        return journalEntryService.getJournalEntriesOfId(myId);
    }

    // Add a new journal entry for a user by username
    @PostMapping("{userName}")
    public ResponseEntity<?> setJournalOfUserName(@PathVariable String userName, @RequestBody JournalEntity myEntry) {
        return journalEntryService.setJournalOfUserName(userName, myEntry);
    }

    // Add a new journal entry for a user by user ID
    @PostMapping("id/{myId}")
    public ResponseEntity<?> setJournalOfUserID(@PathVariable ObjectId myId, @RequestBody JournalEntity myEntry) {
        return journalEntryService.setJournalOfUserID(myId, myEntry);
    }

    // Update a journal entry for a user by username
    @PutMapping("{username}")
    public ResponseEntity<?> updateJournalOfUserName(@PathVariable String username, @RequestBody JournalEntity myEntry) {
        return journalEntryService.updateJournalOfUserName(username, myEntry);
    }

    // Update a journal entry for a user by user ID
    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalOfUserID(@PathVariable ObjectId myId, @RequestBody JournalEntity myEntry) {
        return journalEntryService.updateJournalOfUserID(myId, myEntry);
    }

    // Delete a journal entry of a user by journal ID and username
    @DeleteMapping("{username}/{journalId}")
    public ResponseEntity<?> deleteJournalOfUserID(@PathVariable String username, @PathVariable ObjectId journalId) {
        return journalEntryService.deleteJournalOfUserID(journalId, username);
    }
}
