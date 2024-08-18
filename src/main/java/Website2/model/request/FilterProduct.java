package Website2.model.request;
import Website2.model.entity.NsxRole;
import lombok.Data;
@Data
public class FilterProduct {
    private String search;
    private Integer priceMin;
    private Integer priceMax;
    private Boolean  priceAsc;
    private Boolean  priceDesc;
    private Integer categoryId;
    private NsxRole typeNsx;
}
