package Website2.service;

import Website2.model.DTO.OrderDTO;
import Website2.model.entity.Order;
import Website2.model.entity.Product;
import Website2.model.request.CreateOrder;

import Website2.model.request.FilterOrder;
import Website2.model.request.FilterProduct;
import Website2.model.request.UpdateOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IOrderService {
    List<Order> getAllOrders();

    Page<Order> getAllOrdersPage(Pageable pageable, FilterOrder filterOrder);

    OrderDTO getOrderById(int id);

    void createOrder(CreateOrder createOrder) throws Exception;

    Order updateOrder(int orderID, UpdateOrder updateOrder) throws Exception;

    void deleteOrder(int id);

}
