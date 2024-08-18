package Website2.model.entity;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "Orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Integer orderId;

    @Column(name = "total")
    private Long total;

    @Column(name = "fullName",nullable = false)
    private String fullName;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "phone",nullable = false)
    private int phone;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "sale_date")
    private Date saleDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "note")
    private String note;
}
