package ra.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table( name = "Books")
@Data
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookId")
    private int bookId;
    @Column(name = "BookName")
    private String bookName;
    @Column(name = "Price")
    private float price;
    @Column(name = "BookStatus")
    private boolean bookStatus;
}
