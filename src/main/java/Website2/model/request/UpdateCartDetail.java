package Website2.model.request;

import Website2.model.entity.CartDetailPK;
import lombok.Data;

@Data
public class UpdateCartDetail {
    private CartDetailPK cartDetailPK;
    private int count;
}
