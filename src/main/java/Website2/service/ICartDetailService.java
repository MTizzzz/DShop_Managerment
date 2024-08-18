package Website2.service;

import Website2.model.entity.CartDetail;
import Website2.model.request.CreateCartDetail;
import Website2.model.request.PkCartDetail;
import Website2.model.request.UpdateCartDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICartDetailService {
    List<CartDetail> findAllCarDetail();
    CartDetail findById(PkCartDetail pkCartDetail);
    void createCartDetail(CreateCartDetail createCartDetail);
    CartDetail updateCartDetail(UpdateCartDetail updateCartDetail);
    void deleteCartDetail(PkCartDetail pkCartDetail);



}
