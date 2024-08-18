package Website2.model.entity;
import javax.persistence.*;import lombok.Data;
@Entity
@Data
@Table(name = "cart_detail")
public class CartDetail {
    @EmbeddedId
    private CartDetailPK cartDetailPK;

    @Column(name = "count")
    private int count;

    @ManyToOne
    @MapsId("cartId") // Đặt tên của trường ở đây
    @JoinColumn(name = "cartId", referencedColumnName = "cartId", insertable = false, updatable = false)
    private Cart cart;

    @ManyToOne
    @MapsId("productId") // Đặt tên của trường ở đây
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;
}
