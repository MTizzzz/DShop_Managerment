package Website2.model.DTO;
import Website2.model.entity.Product;
import Website2.model.entity.ProductStatus;
import Website2.model.entity.Reviews;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTOv2 extends RepresentationModel<ProductDTOv2> {
    private int productId;
    private String productCode;
    private String productName;
    private String descriptionProduct;
    private int price;
    private int discount;
    private String image;
    private ProductStatus status;
    private LocalDateTime createdTime;
    private int soLuongTonKho;
    private List<ReviewsDTO> reviews = new ArrayList<>(); // Khởi tạo danh sách ở đây

    public ProductDTOv2(Product product, List<Reviews> reviews) {
        this.productId = product.getProductId();
        this.productCode = product.getProductCode();
        this.productName = product.getProductName();
        this.descriptionProduct = product.getProductDescription();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.image = product.getImage();
        this.status = product.getStatus();
        this.createdTime = product.getCreateTime();
        this.soLuongTonKho = product.getSoLuongTonKho();

        // Thay vì khởi tạo danh sách ở đây, đã khởi tạo trước trong khai báo
        // Bạn có thể tùy chọn khởi tạo danh sách rỗng nếu không có reviews
        if (reviews != null) {
            for (Reviews review : reviews) {
                this.reviews.add(new ReviewsDTO(review));
            }
        }
    }
}
