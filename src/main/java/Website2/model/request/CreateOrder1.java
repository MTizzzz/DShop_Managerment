package Website2.model.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrder1 {
    // thong tin order
    private String name;
    private String address;

    // thong tin order_detail
    List<OrderDetailForm1> orderDetailForm1s;

    @Data
    public class OrderDetailForm1 {
        private Integer pId;
        private Integer amount;
    }

}
