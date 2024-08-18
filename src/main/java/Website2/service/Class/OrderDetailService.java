package Website2.service.Class;
import Website2.model.entity.*;
import Website2.model.request.CreateOrderDetail;
import Website2.model.request.PkOrderDetail;
import Website2.model.request.UpdateOrderDetail;
import Website2.repository.OrderDetailRepository;
import Website2.repository.OrderRepository;
import Website2.repository.ProductRepository;
import Website2.service.IOrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDetail> findAllOrderDetail() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail findById(PkOrderDetail pkOrderDetail) {
        OrderDetailPK orderDetailPK = pkOrderDetail.getOrderDetailPK();
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailPK)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy id mong muốn"));
        return orderDetail;
    }
    @Override
    public void createOrderDetail(CreateOrderDetail createOrderDetail) {
        OrderDetail orderDetail = new OrderDetail();
        OrderDetailPK orderDetailPK = new OrderDetailPK();
        //
        BeanUtils.copyProperties(createOrderDetail,orderDetailPK);
        orderDetail.setOrderDetailPK(orderDetailPK);
        //
        Optional<Order> order = orderRepository.findById(createOrderDetail.getOrderId());
        Optional<Product> product = productRepository.findById(createOrderDetail.getProductId());
        //
        orderDetailPK.setOrderId(order.get());
        orderDetailPK.setProductId(product.get());
        orderDetail.setOrderDetailPK(orderDetailPK);
        //
        orderDetail.setOrder(order.get());
        orderDetail.setProduct(product.get());
        //
        orderDetail.setCount(createOrderDetail.getCount());
        //
        orderDetailRepository.save(orderDetail);

    }

    @Override
    public OrderDetail updateOrderDetail(UpdateOrderDetail updateOrderDetail) {
        OrderDetail orderDetail = orderDetailRepository.findById(updateOrderDetail.getOrderDetailPK())
                .orElseThrow(() -> new EntityNotFoundException("Không thấy id"));
        orderDetail.setCount(updateOrderDetail.getCount());
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void deleteOrderDetail(PkOrderDetail pkOrderDetail) {
        OrderDetailPK orderDetailPK = pkOrderDetail.getOrderDetailPK();
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailPK)
                .orElseThrow(() -> new EntityNotFoundException("Không thấy id"));
        orderDetailRepository.delete(orderDetail);

    }
}
