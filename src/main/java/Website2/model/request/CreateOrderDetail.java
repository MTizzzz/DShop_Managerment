package Website2.model.request;

import lombok.Data;

@Data
public class CreateOrderDetail {
    private int productId;
    private int orderId;
    private int count;
}
