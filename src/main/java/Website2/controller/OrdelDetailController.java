package Website2.controller;

import Website2.model.DTO.CartDetailDTO;
import Website2.model.DTO.OrderDetailDTO;
import Website2.model.entity.CartDetail;
import Website2.model.entity.OrderDetail;
import Website2.model.request.*;
import Website2.service.ICartDetailService;
import Website2.service.IOrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orderdetail")
@CrossOrigin("*")
@Validated
public class OrdelDetailController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("/find-all-orderdetail")
    public List<OrderDetailDTO> findAllOrderDetail(){
        List<OrderDetail> orderDetails = orderDetailService.findAllOrderDetail();
        List<OrderDetailDTO> orderDetailDTOS = orderDetails.stream()
                .map(orderDetail -> mapper.map(orderDetail, OrderDetailDTO.class))
                .collect(Collectors.toList());
        return orderDetailDTOS;
    }
    @GetMapping("/find-orderdetail-by-id")
    public OrderDetailDTO findOrderDetailById(@RequestBody PkOrderDetail request) {
        OrderDetail orderDetail = orderDetailService.findById(request);
        return mapper.map(orderDetail , OrderDetailDTO.class);
    }
    @PostMapping("/create-orderdetail")
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody CreateOrderDetail request){
        orderDetailService.createOrderDetail(request);
        return ResponseEntity.ok("Thêm Chi tiết đơn hàng thành công");
    }
    @PutMapping ("/update-orderdetail")
    public ResponseEntity<?> updateOrderDetail(@Valid @RequestBody UpdateOrderDetail request){
        orderDetailService.updateOrderDetail(request);
        return ResponseEntity.ok("Sửa Chi tiết đơn hàng thành công");
    }
    @DeleteMapping("/delete-orderdetail")
    public ResponseEntity<?> deleteOrderDetail(@RequestBody PkOrderDetail request){
        orderDetailService.deleteOrderDetail(request);
        return ResponseEntity.ok("Xóa Chi tiết đơn hàng thành công");
    }}
