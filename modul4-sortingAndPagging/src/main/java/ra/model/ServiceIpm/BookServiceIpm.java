package ra.model.ServiceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.Service.BookService;
import ra.model.entity.Books;
import ra.model.repostory.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceIpm implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Books> sortBookByBookName(String direction) {
       if (direction.equals("asc")){
           return bookRepository.findAll(Sort.by("bookName").ascending());
       }else {
           return bookRepository.findAll(Sort.by("bookName").descending());
       }
    }

    @Override
    public List<Books> sortByNameAndPrice(String directionName, String driectionPrice) {
        List<Books> listBooks;
        if (directionName.equals("asc")){
            if (driectionPrice.equals("asc")){
                return  bookRepository.findAll(Sort.by("bookName").and(Sort.by("price")));
            }else {
                return bookRepository.findAll(Sort.by("bookName").and(Sort.by("price").descending()));
            }
        }else {
            if (driectionPrice.equals("asc")){
                return  bookRepository.findAll(Sort.by("bookName").descending().and(Sort.by("price")));
            }else {
                return bookRepository.findAll(Sort.by("bookName").descending().and(Sort.by("price").descending().descending()));
            }
        }
    }

    @Override
    public List<Books> sortByName_Price_Id(String directionName, String directionPrice, String directionId) {
        List<Sort.Order> listOrder = new ArrayList<>();
        if (directionName.equals("asc")){
            listOrder.add(new Sort.Order(Sort.Direction.ASC,"bookName"));
        }else {
            listOrder.add(new Sort.Order(Sort.Direction.DESC,"bookName"));
        }
        if (directionPrice.equals("asc")){
            listOrder.add(new Sort.Order(Sort.Direction.ASC, "price"));
        }else {
            listOrder.add(new Sort.Order(Sort.Direction.DESC, "price"));
        }
        if (directionId.equals("id")){
            listOrder.add(new Sort.Order(Sort.Direction.ASC, "bookId"));
        }else {
            listOrder.add(new Sort.Order(Sort.Direction.DESC, "bookId"));
        }
       return bookRepository.findAll(Sort.by(listOrder));

    }

    @Override
    public Page<Books> getPagging(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}

