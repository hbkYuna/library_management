package be.thomasmore.library.controllers;

import be.thomasmore.library.model.Book;
import be.thomasmore.library.model.Purchase;
import be.thomasmore.library.model.User;
import be.thomasmore.library.repositories.BookRepository;
import be.thomasmore.library.repositories.PurchaseRepository;
import be.thomasmore.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping({"", "/", "/home"})
    public String home(Model model, Principal principal) {
        Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
        if (optionalUser.isPresent()) {
            List<Purchase> purchases = purchaseRepository.findByUserAndIsFinalised(optionalUser.get());
            List<Book> books = new ArrayList<>();
            for (Purchase purchase : purchases) {
                books.addAll(purchase.getBooks());
            }
            model.addAttribute("books", books);
        }
        return "user/home";
    }

    @GetMapping({"/catalog"})
    public String catalog(Model model, @RequestParam(required = false) String author, @RequestParam(required = false) String title, @RequestParam(required = false) Integer year) {
        Iterable<Book> books;
        if (author != null && author != "") {
            books = bookRepository.findAllByAuthor(author);
        } else if (title != null && title != "") {
            books = bookRepository.findAllByTitle(title);
        } else if (year != null) {
            books = bookRepository.findAllByYear(year);
        } else {
            books = bookRepository.findAll();
        }
        model.addAttribute("books", books);
        return "user/catalog";
    }

    @GetMapping({"/detailpage/{id}"})
    public String detailpage(Model model, @PathVariable int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            model.addAttribute("book", optionalBook.get());
            boolean lastPage = !bookRepository.existsById(id + 1);
            model.addAttribute("lastPage", lastPage);
        }
        return "user/detailpage";
    }

    @PostMapping("/addToCart/{id}")
    public String addToCart(Principal principal, Model model, @PathVariable int id) {
        Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                Optional<Purchase> optionalPurchase = purchaseRepository.findByUserAndNotFinalised(user);
                Purchase purchase;
                if (optionalPurchase.isEmpty()) {
                    purchase = new Purchase(user, List.of(book));
                } else {
                    purchase = optionalPurchase.get();
                    purchase.addBook(book);
                }
                purchaseRepository.save(purchase);
            }
        }
        return "redirect:/user/catalog";
    }

    @GetMapping("/cart")
    public String cart(Principal principal, Model model) {
        Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
        if (optionalUser.isPresent()) {
            Optional<Purchase> optionalPurchase = purchaseRepository.findByUserAndNotFinalised(optionalUser.get());
            if (optionalPurchase.isPresent()) {
                model.addAttribute("books", optionalPurchase.get().getBooks());
            } else {
                model.addAttribute("books", null);
            }
        }
        return "user/cart";
    }

    @PostMapping("/makePurchase")
    public String makePurchase(Principal principal) {
        Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
        if (optionalUser.isPresent()) {
            Optional<Purchase> optionalPurchase = purchaseRepository.findByUserAndNotFinalised(optionalUser.get());
            if (optionalPurchase.isPresent()) {
                Purchase purchase = optionalPurchase.get();
                purchase.setFinalised(true);
                purchaseRepository.save(purchase);
            }
        }
        return "redirect:/user/home";
    }


}