package be.thomasmore.library_mgm.controllers;

import be.thomasmore.library_mgm.DAL.BookRepository;
import be.thomasmore.library_mgm.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/catalog")
    public String catalog(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "Catalog";
    }



}
