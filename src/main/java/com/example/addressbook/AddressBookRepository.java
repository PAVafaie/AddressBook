package com.example.addressbook;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {

    Optional<AddressBook> findById(long id);

    AddressBook save(AddressBook addressBook);

    void deleteById(long id);
}
