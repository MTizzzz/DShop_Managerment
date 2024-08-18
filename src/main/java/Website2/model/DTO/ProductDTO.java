package Website2.model.DTO;

import Website2.model.entity.Product;
import Website2.model.entity.ProductStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private int productId;
    private int productCode;
    private String productName;
    private String descriptionProduct;
    private int price;
    private int discount;
    private String image;
    private ProductStatus status;
    private LocalDateTime createdTime;
    private int soLuongTonKho;
    private NsxDTO nsxDTO;
    private CategoryDTO categoryDTO;

    // Constructor từ Product entity
    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.productCode = Integer.parseInt(product.getProductCode());
        this.productName = product.getProductName();
        this.descriptionProduct = product.getProductDescription();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.image = product.getImage();
        this.status = product.getStatus();
        this.createdTime = product.getCreateTime();
        this.soLuongTonKho = product.getSoLuongTonKho();

        // Nếu Nsx và Category không null, ánh xạ chúng sang DTO tương ứng
        if (product.getNsx() != null) {
            this.nsxDTO = new NsxDTO(product.getNsx());
        }
        if (product.getCategory() != null) {
            this.categoryDTO = new CategoryDTO(product.getCategory());
        }
    }
}
