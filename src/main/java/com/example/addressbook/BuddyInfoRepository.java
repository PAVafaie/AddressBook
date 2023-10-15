package com.example.addressbook;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// Pretend this is annotated as a bean, because CrudRepository is.
// That's why spring is able to recognize this and inject it into the demo method that
// takes a BuddyInfoRepository as a parameter
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    BuddyInfo findById(long id);

    List<BuddyInfo> findByName(String name);

    List<BuddyInfo> findByPhoneNumber(String phoneNumber);
}
