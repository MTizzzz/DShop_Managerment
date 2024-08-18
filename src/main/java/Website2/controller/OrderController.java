package Website2.controller;

import Website2.model.DTO.OrderDTO;
import Website2.model.DTO.ProductDTO;
import Website2.model.entity.Order;
import Website2.model.entity.Product;
import Website2.model.request.CreateOrder;
import Website2.model.request.FilterOrder;
import Website2.model.request.FilterProduct;
import Website2.model.request.UpdateOrder;
import Website2.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin("*")
@Validated
public class OrderController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IOrderService orderService;

@GetMapping("/find-all-order")//  don hang 1(ngu mua, ng ban , thoi gian mua, tong so tien),    chi tiet don hang oerdetail_ 1a 1b 1c(ten sp, sl, don gia, ...)
public Page<OrderDTO> findAllOrderPage(Pageable pageable, FilterOrder filterOrder) {
    Page<Order> orderPage = orderService.getAllOrdersPage(pageable,filterOrder);
    return orderPage.map(order -> mapper.map(order, OrderDTO.class));
}

    @GetMapping("/find-order-by-id")//  don hang 1(ngu mua, ng ban , thoi gian mua, tong so tien),    chi tiet don hang oerdetail_ 1a 1b 1c(ten sp, sl, don gia, ...)
    public OrderDTO findById(@RequestParam int id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrder createOrder) throws Exception {
        orderService.createOrder(createOrder);
        return ResponseEntity.ok("Thêm đơn hàng thành công");
    }
    @PutMapping("/update-order/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id,@RequestBody UpdateOrder updateOrder) throws Exception {
        orderService.updateOrder(id,updateOrder);
        return ResponseEntity.ok("Sửa đơn hàng thành công");
    }
    @DeleteMapping("/delete-order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") int id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Xóa đơn hàng thàng công");
    }


}