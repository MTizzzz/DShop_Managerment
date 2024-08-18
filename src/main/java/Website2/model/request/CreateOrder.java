package Website2.model.request;

import Website2.model.entity.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateOrder {
    private Long total;
    private String fullName;
    private String address;
    private int phone;
    private Date orderDate;
    private Date saleDate;
    private List<CreateProductRequest> productRequests;
    private OrderStatus status;
    private String note;

    @Data
    public  static class CreateProductRequest{
        private Integer idPro;
        private Integer amount;
        private Integer price;
        private Integer discount;

    }
}
