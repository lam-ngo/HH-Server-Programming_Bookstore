package fi.hh.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.Bookstore.domain.Category;
import fi.hh.Bookstore.domain.CategoryRepository;
import fi.hh.Bookstore.domain.Book;
import fi.hh.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepository; 
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	//Show all books in Thymeleaf template
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }
    
    //RESTfull service to get all books
    @RequestMapping(value="/books", method= RequestMethod.GET)
    public @ResponseBody List <Book> bookListRest() {	
    	return (List <Book>) bookRepository.findAll();
    }
    
    //RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findOne(bookId);
    }   
    
    //RESTfull service to get all books
    @RequestMapping(value="/categories", method= RequestMethod.GET)
    public @ResponseBody List <Category> categoryListRest() {	
    	return (List <Category>) categoryRepository.findAll();
    }
    
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
    	bookRepository.save(book);
        return "redirect:booklist";
    }    

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bookRepository.delete(bookId);
        return "redirect:../booklist";
    }     
    
    @RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model){
    	model.addAttribute("book", bookRepository.findOne(bookId));
    	model.addAttribute("categories", categoryRepository.findAll());
        return "edit";
    }      
		
}
