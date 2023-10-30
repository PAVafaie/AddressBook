package com.example.addressbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class GuiController {
    private final AddressBookRepository addressBookRepository;
    private final BuddyInfoRepository buddyInfoRepository;

    public GuiController(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository) {
        this.addressBookRepository = addressBookRepository;
        this.buddyInfoRepository = buddyInfoRepository;
    }

    @GetMapping("/")
    public String getGUI(Model model) {
        // Fetch address books from the repository and add them to the model
        Iterable<AddressBook> addressBooks = addressBookRepository.findAll();
        model.addAttribute("addressBooks", addressBooks);

        // for getting info about newly added buddies
        model.addAttribute("newBuddyInfo", new BuddyInfo());

        // Return the name of the Thymeleaf template to render
        return "index";
    }

    @PostMapping("/addAddressBook")
    public String createAddressBook(@ModelAttribute AddressBook newAddressBook, Model model) {
        addressBookRepository.save(newAddressBook);

        return getGUI(model);
    }

    @PostMapping("/addBuddyInfo")
    public String createBuddyInfo(@ModelAttribute BuddyInfo newBuddyInfo, @RequestParam("addressBookId") Long addressBookId, Model model) {
        // retrieve the corresponding AddressBook entity and associate it with the buddy info.
        Optional<AddressBook> optionalAddressBook = addressBookRepository.findById(addressBookId);
        if (optionalAddressBook.isPresent()) {
            AddressBook addressBook = optionalAddressBook.get();
            // Add the buddy info to the address book
            addressBook.addBuddy(newBuddyInfo);
            addressBookRepository.save(addressBook); // Save the updated address book
            buddyInfoRepository.save(newBuddyInfo);
        }

        return getGUI(model);
    }




}
