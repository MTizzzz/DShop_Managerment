package Website2.service;

import Website2.model.entity.OrderDetail;
import Website2.model.request.CreateOrderDetail;
import Website2.model.request.PkOrderDetail;
import Website2.model.request.UpdateOrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IOrderDetailService {
    List<OrderDetail> findAllOrderDetail();
    OrderDetail findById(PkOrderDetail pkOrderDetail);
    void createOrderDetail(CreateOrderDetail createOrderDetail);
    OrderDetail updateOrderDetail(UpdateOrderDetail updateOrderDetail);
    void deleteOrderDetail(PkOrderDetail pkOrderDetail);
}
