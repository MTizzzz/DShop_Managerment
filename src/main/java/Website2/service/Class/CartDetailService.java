package Website2.service.Class;
import Website2.model.entity.Cart;
import Website2.model.entity.CartDetail;
import Website2.model.entity.CartDetailPK;
import Website2.model.entity.Product;
import Website2.model.request.CreateCartDetail;
import Website2.model.request.PkCartDetail;
import Website2.model.request.UpdateCartDetail;
import Website2.repository.CartDetailRepository;
import Website2.repository.CartRepository;
import Website2.repository.ProductRepository;
import Website2.service.ICartDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<CartDetail> findAllCarDetail() {
        return cartDetailRepository.findAll();
    }

    @Override
    public CartDetail findById(PkCartDetail pkCartDetail) {
        CartDetailPK cartDetailPK = pkCartDetail.getCartDetailPK();
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailPK)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy id mong muốn"));
        return cartDetail;
    }

    @Override
    public void createCartDetail(CreateCartDetail createCartDetail) {
        CartDetail cartDetail = new CartDetail();
        CartDetailPK cartDetailPK = new CartDetailPK();
        //
        BeanUtils.copyProperties(createCartDetail,cartDetailPK);
        cartDetail.setCartDetailPK(cartDetailPK);
        //
        Optional<Cart> cart = cartRepository.findById(createCartDetail.getCartId());
        Optional<Product> product = productRepository.findById(createCartDetail.getCartId());
        //
        cartDetailPK.setCartId(cart.get());
        cartDetailPK.setProductId(product.get());
        cartDetail.setCartDetailPK(cartDetailPK);
        //
        cartDetail.setCart(cart.get());
        cartDetail.setProduct(product.get());
        //
        cartDetail.setCount(createCartDetail.getCount());
        //
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public CartDetail updateCartDetail(UpdateCartDetail updateCartDetail) {
        CartDetail cartDetail = cartDetailRepository.findById(updateCartDetail.getCartDetailPK())
                .orElseThrow(() -> new EntityNotFoundException("Không thấy id"));
        cartDetail.setCount(updateCartDetail.getCount());
        return cartDetailRepository.save(cartDetail);
    }
    @Override
    public void deleteCartDetail(PkCartDetail pkCartDetail) {
        CartDetailPK cartDetailPK = pkCartDetail.getCartDetailPK();
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailPK)
                .orElseThrow(() -> new EntityNotFoundException("Không thấy id"));
        cartDetailRepository.delete(cartDetail);
    }



}
