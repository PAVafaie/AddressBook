package com.example.addressbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuiController {
    private final AddressBookRepository addressBookRepository;

    public GuiController(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    @GetMapping("/")
    public String getGUI(Model model) {
        // Fetch address books from the repository and add them to the model
        Iterable<AddressBook> addressBooks = addressBookRepository.findAll();
        model.addAttribute("addressBooks", addressBooks);

        // Return the name of the Thymeleaf template to render
        return "index";
    }
}
