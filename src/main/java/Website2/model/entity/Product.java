package Website2.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Integer productId;

    @Column(name = "productCode", nullable = false, unique = true)
    private String productCode;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productDescription", length = 500)
    private String productDescription;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "discount")
    private int discount;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "featured", nullable = false)
    private boolean featured;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "createTime", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name = "soLuongTonKho", nullable = false)
    private int soLuongTonKho;

    // Cột lưu nsxId
    // Cách mới đẩy thông tin vào cột được tạo vào
    @Column(name = "nsxId")
    private Integer nsxId;

    // Cột lưu categoryId
    // Cách mới đẩy thông tin vào cột được tạo vào
    @Column(name = "categoryId")
    private Integer categoryId;

    @ManyToOne()
    @JoinColumn(name = "nsxId", referencedColumnName = "nsxId", insertable = false, updatable = false)
    private Nsx nsx;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId", insertable = false, updatable = false)
    private Category category;

//    @JsonBackReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;



}
