package ra.model.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.model.entity.Books;

import java.util.List;

public interface BookService {
    List<Books> sortBookByBookName(String direction);

    List<Books> sortByNameAndPrice(String directionName, String driectionPrice);
    List<Books> sortByName_Price_Id(String directionName, String directionPrice, String directionId );

    Page<Books> getPagging(Pageable pageable);


}
