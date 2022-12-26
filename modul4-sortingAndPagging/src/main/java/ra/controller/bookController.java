package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import ra.model.Service.BookService;
import ra.model.entity.Books;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/books")
public class bookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/sortByName")
    public ResponseEntity<List<Books>> sortBookByName(@RequestParam("direction") String direction){
        List<Books> listBooks = bookService.sortBookByBookName(direction);
        return new ResponseEntity<>(listBooks, HttpStatus.OK);
    }
    @GetMapping("/sortByNameAndPrice")
    public ResponseEntity<List<Books>> sortBookByNameAndPrice(@RequestParam("directionName") String directionName,
                                                              @RequestParam("directionPrice") String directionPrice){
        List<Books> listBooks = bookService.sortByNameAndPrice(directionName,directionPrice);
        return new ResponseEntity<>(listBooks, HttpStatus.OK);
    }

    @GetMapping("/sortByNameAndPriceAndId")
    public ResponseEntity<List<Books>> sortBookByNameAndPriceAndId(@RequestParam("directionName") String directionName,
                                                              @RequestParam("directionPrice") String directionPrice,
                                                              @RequestParam("directionId") String directionId){
        List<Books> listBooks = bookService.sortByName_Price_Id(directionName,directionPrice, directionId);
        return new ResponseEntity<>(listBooks, HttpStatus.OK);

    }
    @GetMapping("/getPagging")
    public ResponseEntity<Map<String,Object>> getPagging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3")int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Books> pageBook = bookService.getPagging(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("books", pageBook.getContent());
        data.put("total", pageBook.getSize());
        data.put("totalItems", pageBook.getTotalElements());
        data.put("totalPages", pageBook.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/getPaggingAndSortByName")
    public ResponseEntity<Map<String,Object>> getPaggingAndSortByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3")int size,
            @RequestParam String direction){
        Sort.Order order;
        if (direction.equals("asc")){
            order=new Sort.Order(Sort.Direction.ASC, "bookName");
        }else {
            order=new Sort.Order(Sort.Direction.DESC, "bookName");
        }
        Pageable pageable = PageRequest.of(page,size,Sort.by(order));
        Page<Books> pageBook = bookService.getPagging(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("books", pageBook.getContent());
        data.put("total", pageBook.getSize());
        data.put("totalItems", pageBook.getTotalElements());
        data.put("totalPages", pageBook.getTotalPages());
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
}
