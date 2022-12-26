package ra.model.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Integer> {
}
