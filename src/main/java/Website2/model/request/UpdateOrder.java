package Website2.model.request;

import Website2.model.entity.OrderStatus;
import lombok.Data;

import java.util.Date;
@Data
public class UpdateOrder {
    private long total;
    private String fullName;
    private int phone;
    private String address;
    private Date orderDate;
    private Date saleDate;
    private OrderStatus status;
    private String note;
}

