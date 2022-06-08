package ua.lviv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lviv.dao.BookDAO;
import ua.lviv.dao.PersonDAO;
import ua.lviv.models.Book;
import ua.lviv.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showBooks(Model model){
        model.addAttribute("books", bookDAO.showBooks());
        return "books/showBooks";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book){
        return "books/newBook";
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/newBook";
        }
        bookDAO.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person")Person person){
        model.addAttribute("book", bookDAO.showBook(id));
        model.addAttribute("people", personDAO.showPeople());
        model.addAttribute("nameOfPerson", bookDAO.getNameOfPerson(id));
        return "books/showBook";
    }
    @PatchMapping("/{id}/addUser")
    public String addBookToPerson(@PathVariable int id, @ModelAttribute("person") Person person){
        bookDAO.addBookToPerson(id, person);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release-book")
    public String deleteBookFromPerson(@PathVariable("id") int id){
        bookDAO.deleteBookFromPerson(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/editBook";
        }
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id){
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
}
