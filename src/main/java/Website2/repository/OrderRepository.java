package Website2.repository;

import Website2.model.entity.Order;
import Website2.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>, JpaSpecificationExecutor<Order> {
//    List<Order> findAllByUserId (Integer userId);
//
//    @Query(
//         value = "SELECT COUNT(*) FROM Order order " +
//         "WHERE YEAR(order.orderDate) = YEAR(CURRENT_DATE()) AND " +
//         "\tMONTH(order.orderDate) = MONTH(CURRENT_DATE())", nativeQuery = true
//    )
//    Integer OrdersInMonth();
//
//    /*
//     * DATE_ADD
//     *   + Tham số đầu tiên: Là 1 giá trị thời gian
//     *   + Tham số thu 2: Là khoản thời gian ta muốn + thêm
//     * -> Giải thích trong phương thức: Lấy ngày hiện tại bằng CURRENT_DATE() + (-1)
//     *   -> Xử lý được vấn đề Tháng 1 lùi về 1 tháng
//     *    ( 02/01/2024 -> 02/12/2023)
//     *
//     */
//    @Query(
//            value = "SELECT COUNT(*) FROM Order o " +
//                    "WHERE  YEAR(o.orderDate) = YEAR(DATE_ADD(CURRENT_DATE(), INTERVAL -1 MONTH)) AND " +
//                    "\tMONTH(o.orderDate) = MONTH(DATE_ADD(CURRENT_DATE(), INTERVAL -1 MONTH))", nativeQuery = true
//    )
//    Integer ordersInLastMonth();


}
