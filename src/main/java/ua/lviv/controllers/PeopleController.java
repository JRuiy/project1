package ua.lviv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lviv.dao.BookDAO;
import ua.lviv.dao.PersonDAO;
import ua.lviv.models.Person;
import ua.lviv.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String showPeople(Model model){
        model.addAttribute("people", personDAO.showPeople());
        return "people/showPeople";
    }

    @GetMapping("/new")
    public String newPeople(@ModelAttribute("person")Person person){
        return "people/newPerson";
    }

    @PostMapping("/new")
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()){
            return "people/newPerson";
        }
        personDAO.createPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.showPerson(id));
        model.addAttribute("books", bookDAO.showBooksAtPerson(id));
        return "people/showPerson";

    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.showPerson(id));
        return "people/editPerson";
    }

    @PatchMapping ("/{id}")
    public String updatePerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()){
            return "people/editPerson";
        }
        personDAO.editPerson(id, person);
        return "redirect:/people";
    }
}
