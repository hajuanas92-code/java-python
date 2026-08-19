package com.example.demo.project;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

@RestController
@RequestMapping("/book")
public class BookController {
    List<Book> bookList = new ArrayList<>();
    int nextId = 1;

    @PostMapping
    public Book addBook(@RequestParam String title,@RequestParam String author ) {
        Book newBook = new Book(nextId, title, author);
        nextId++;
        bookList.add(newBook);
        return newBook; 
    }
    // get author
    @GetMapping("/author/{id}")
    public Book author(@PathVariable int id){
        for (Book n: bookList) {
            if (n.getId() == id) {
                return n;
            }
        }return null;
    }
    // all books
    @GetMapping("/all")
    public List<Book> getAllBooks(){
        return bookList;
    }
    // one book
    @GetMapping("/oneBook/{id}")
    public Book getBook(@PathVariable int id){
        for (Book n: bookList) {
            if (n.getId() == id) {
                return n;
            }
        } return null;
    }
    // delete
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        for (Book n: bookList) {
            if (n.getId() == id) {
                bookList.remove(n);
                return "Book with id "+id+"deleted successfully";
            }
        }return "Book id not found";
    }
    // update
    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable int id, @RequestParam String newTitle, @RequestParam String newAuthor) {
        for (Book n: bookList) {
            if (n.getId() == id) {
              n.setTitle(newTitle);
              n.setAuthor(newAuthor);
              return n;
            }
        }
        return null;
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
