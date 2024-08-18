package Website2.model.request;

import Website2.model.entity.CartDetailPK;
import Website2.model.entity.OrderDetailPK;
import lombok.Data;

@Data
public class UpdateOrderDetail {
    private OrderDetailPK orderDetailPK;
    private int count;
}
