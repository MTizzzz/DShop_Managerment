package Website2.model.DTO;

import Website2.model.entity.OrderDetail;
import lombok.Data;

@Data
public class OrderDetailDTO {
    private int count;
    private int price;
    private String productName;
    private String image;

    public OrderDetailDTO() {

    }
    public OrderDetailDTO(OrderDetail orderDetail) {
        this.count = orderDetail.getCount();
        this.productName = orderDetail.getProduct().getProductName();
        this.price = orderDetail.getProduct().getPrice();
        this.image = orderDetail.getProduct().getImage();
    }
}
