package Website2.model.request;

import Website2.model.entity.ProductStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateProduct {
    private int productCode;
    private String productName;
    private String descriptionProduct;
    private int price;
    private int discount;
    private String image;
    private ProductStatus status;
    private int soLuongTonKho;
    private Integer nsxId;
    private Integer categoryId;
}
