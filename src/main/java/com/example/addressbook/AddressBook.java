package com.example.addressbook;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL)
    private List<BuddyInfo> buddyList = new ArrayList<>();

    /**
     * Initializes an empty AddressBook object.
     */
    public AddressBook() {
        this.buddyList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    /**
     * Adds a BuddyInfo object to the address book.
     * @param buddy The BuddyInfo object to be added.
     */
    public void addBuddy(BuddyInfo buddy) {
        buddyList.add(buddy);
        buddy.setAddressBook(this);
    }

    /**
     * Removes a BuddyInfo object from the address book.
     * @param buddy The BuddyInfo object to be removed.
     */
    public void removeBuddy(BuddyInfo buddy) {
        buddyList.remove(buddy);
        buddy.setAddressBook(null);
    }

    /**
     * Gets the number of buddies in the address book.
     * @return The number of buddies in the address book.
     */
    public int getNumBuddies() {
        return buddyList.size();
    }

    /**
     * Gets a specific buddy from the address book by index.
     * @param index The index of the buddy to retrieve.
     * @return The BuddyInfo object at the specified index, or null if the index is out of bounds.
     */
    public BuddyInfo getBuddy(int index) {
        if (index >= 0 && index < buddyList.size()) {
            return buddyList.get(index);
        } else {
            return null;
        }
    }
}
