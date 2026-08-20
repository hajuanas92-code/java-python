package com.example.demo.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.util.List;
import java.io.IOException;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository; // injecting database service

    @PostMapping
    public Book addBook(@RequestParam String title,@RequestParam String author ) {
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setAvailable(true);
        return bookRepository.save(newBook); 
    }
    // get author
    @GetMapping("/author/{id}")
    public String author(@PathVariable Long id){
        Book book = bookRepository.findById(id).orElse(null);
        return (book != null) ? book.getAuthor() : "Author not found";
    }
    // all books
    @GetMapping("/all")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    // one book
    @GetMapping("/oneBook/{id}")
    public Book getBook(@PathVariable Long id){
        return bookRepository.findById(id).orElse(null);
    }
    // delete
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        return "Book with id "+id+" deleted successfully";
        }
    return "Book id not found";
    }
    
    // update
    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestParam String newTitle, @RequestParam String newAuthor) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book != null) {
          book.setTitle(newTitle);
          book.setAuthor(newAuthor);
          return bookRepository.save(book);
          
        } return null;
    }
    // pdf uploading
    @PostMapping("/upload-pdf")
    public String uploadAndReadpdf(@RequestParam("file") MultipartFile file) {
      try {
        String fileName = file.getOriginalFilename();
        byte[] fileBytes = file.getBytes();

        try (PDDocument document = Loader.loadPDF(fileBytes)){
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String extractedText = pdfStripper.getText(document);

            System.out.println(extractedText);
            return "Successfully recieved file "+ fileName;

        } 
        
      }catch (IOException e) {
            return e.getMessage();
        }       
    }
}
