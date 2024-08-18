package Website2.controller;
import Website2.model.DTO.CartDetailDTO;
import Website2.model.entity.CartDetail;
import Website2.model.request.CreateCartDetail;
import Website2.model.request.PkCartDetail;
import Website2.model.request.UpdateCartDetail;
import Website2.service.ICartDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("api/v1/cartdetail")
@CrossOrigin("*")
@Validated
public class CartDetailController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ICartDetailService cartDetailService;

    @GetMapping("/find-all-cartdetail")
    public List<CartDetailDTO> findAllCartDetail(){
        List<CartDetail> cartDetails = cartDetailService.findAllCarDetail();
        List<CartDetailDTO> cartDetailDTOS = cartDetails.stream()
                .map(cartDetail -> mapper.map(cartDetail, CartDetailDTO.class))
                .collect(Collectors.toList());
        return cartDetailDTOS;
    }
    @GetMapping("/find-cartdetail-by-id")
    public CartDetailDTO findCartDetailById(@RequestBody PkCartDetail request) {
        CartDetail cartDetail = cartDetailService.findById(request);
        return mapper.map(cartDetail , CartDetailDTO.class);
    }
    @PostMapping("/create-cartdetail")
    public ResponseEntity<?> createCartDetail(@Valid @RequestBody CreateCartDetail request){
        cartDetailService.createCartDetail(request);
        return ResponseEntity.ok("Thêm Chi tiết giỏ hàng thành công");
    }
    @PutMapping ("/update-cartdetail")
    public ResponseEntity<?> updateCartDetail(@Valid @RequestBody UpdateCartDetail request){
        cartDetailService.updateCartDetail(request);
        return ResponseEntity.ok("Sửa Chi tiết giỏ hàng thành công");
    }
    @DeleteMapping("/delete-cartdetail")
    public ResponseEntity<?> deleteCartDetail(@RequestBody PkCartDetail request){
        cartDetailService.deleteCartDetail(request);
        return ResponseEntity.ok("Xóa Chi tiết giỏ hàng thành công");
    }
}
