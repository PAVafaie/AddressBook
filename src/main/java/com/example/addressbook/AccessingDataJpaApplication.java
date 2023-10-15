//package com.example.addressbook;
//
//import com.example.addressbook.BuddyInfoRepository; // it's unused because spring injects it for us
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//@SpringBootApplication
//public class AccessingDataJpaApplication {
//
//    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);
//
//    public static void main(String[] args) {
//        SpringApplication.run(AccessingDataJpaApplication.class);
//    }
//
//    @Bean
//    public CommandLineRunner demo(BuddyInfoRepository buddyInfoRepository, AddressBookRepository addressBookRepository) {
//        return (args) -> {
//            // save a few customers
//            buddyInfoRepository.save(new BuddyInfo("Jack", "123"));
//            buddyInfoRepository.save(new BuddyInfo("Chloe", "123"));
//            buddyInfoRepository.save(new BuddyInfo("Kim", "456"));
//            buddyInfoRepository.save(new BuddyInfo("David", "789"));
//
//            // fetch all BuddyInfos
//            log.info("BuddyInfos found with findAll():");
//            log.info("-------------------------------");
//            for (BuddyInfo buddyInfo : buddyInfoRepository.findAll()) {
//                log.info(buddyInfo.toString());
//            }
//            log.info("");
//
//            // fetch an individual BuddyInfo by ID
//            BuddyInfo buddyInfo = buddyInfoRepository.findById(1L);
//            log.info("BuddyInfo found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(buddyInfo.toString());
//            log.info("");
//
//            // fetch BuddyInfos by last name
//            log.info("BuddyInfo found with findByName('Kim'):");
//            log.info("--------------------------------------------");
//            buddyInfoRepository.findByName("Kim").forEach(kim -> {
//                log.info(kim.toString());
//            });
//            log.info("");
//
//
//            AddressBook ab1 = new AddressBook();
//            AddressBook ab2 = new AddressBook();
//
//            ab1.addBuddy(new BuddyInfo("Patrick", "000"));
//            ab2.addBuddy(new BuddyInfo("Police", "911"));
//
//            addressBookRepository.save(ab1);
//            addressBookRepository.save(ab2);
//
//            AddressBook addressBook = addressBookRepository.findById(1L);
//            log.info("AddressBook found with findById(1L) has the following BuddyInfo at index 0");
//            log.info("--------------------------------");
//            log.info(addressBook.getBuddy(0).toString());
//            log.info("");
//
//            addressBook = addressBookRepository.findById(2L);
//            log.info("AddressBook found with findById(2L) has the following BuddyInfo at index 0");
//            log.info("--------------------------------");
//            log.info(addressBook.getBuddy(0).toString());
//            log.info("");
//        };
//    }
//}
