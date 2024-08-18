package Website2.model.DTO;

import Website2.model.entity.ProductStatus;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ProductDTOv3 {
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
}
