package be.thomasmore.library.controllers;
import be.thomasmore.library.model.Book;
import be.thomasmore.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    BookRepository bookRepository;


    @GetMapping({"", "/", "/adminhome"})
    public String adminhome() {
        return "admin/adminhome";
    }


    @GetMapping("/managebooks")
    public String managebooks(Model model){
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "admin/managebooks";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam int id) {
        bookRepository.findById(id).ifPresent(book -> bookRepository.delete(book));
        return "redirect:/admin/managebooks";
    }

    @GetMapping("/addbook")
    public String addbook(){

        return "admin/addbook";
    }

    @PostMapping("/addbook")
    public String addbookPost(@RequestParam String title, @RequestParam String author,@RequestParam String description , @RequestParam int year, @RequestParam int pages){
        Book book = new Book(author, title, description, pages, year);
        bookRepository.save(book);
        return "redirect:/admin/managebooks";
    }
}