package Website2.model.DTO;

import Website2.model.entity.Order;
import Website2.model.entity.OrderDetail;
import Website2.model.entity.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Integer orderId;
    private long total;
    private String fullName;
    private String address;
    private int phone;
    private Date orderDate;
    private Date saleDate;
    private OrderStatus status;
    private String note;
    private List<OrderDetailDTO> orderDetailDTOS;

    public OrderDTO(Order order, List<OrderDetail> orderDetails) {
        this.orderId = order.getOrderId();
        this.total = order.getTotal();
        this.fullName = order.getFullName();
        this.phone = order.getPhone();
        this.address = order.getAddress();
        this.orderDate = order.getOrderDate();
        this.note = order.getNote();
        this.status = order.getStatus();

        this.orderDetailDTOS = new ArrayList<>();
        for (OrderDetail orderDetail: orderDetails) {
            this.orderDetailDTOS.add(new OrderDetailDTO(orderDetail));
        }
    }
}