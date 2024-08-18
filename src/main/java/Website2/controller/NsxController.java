package Website2.controller;

import Website2.model.DTO.NsxDTO;
import Website2.model.entity.Nsx;
import Website2.model.request.CreateNsx;
import Website2.model.request.UpdateNsx;
import Website2.service.INsxService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/type")
@CrossOrigin("*")
@Validated
public class NsxController {
    @Autowired
    private INsxService nsxService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/get-all-nsx")
    List<NsxDTO> findAllNsx() {
        List<Nsx> nsxList = nsxService.findAll();
        List<NsxDTO> nsxDTOS = nsxList.stream()
                .map(nsx -> mapper.map(nsx, NsxDTO.class))
                .collect(Collectors.toList());

        return nsxDTOS;
    }
    @PostMapping("/create-nsx")
    public ResponseEntity<?> createNsx(@RequestBody CreateNsx createNsx) throws Exception {
        nsxService.createNsx(createNsx);
        return ResponseEntity.ok("Thêm nsx thành công");
    }
    @PutMapping("/update-nsx/{id}")
    public ResponseEntity<?> updateNsx(@PathVariable int id,@RequestBody UpdateNsx updateNsx) throws Exception {
        nsxService.updateNsx(id,updateNsx);
        return ResponseEntity.ok("Sửa nsx công");
    }
    @DeleteMapping("/delete-nsx/{id}")
    public ResponseEntity<?> deleteNsx(@PathVariable("id") int id){
        nsxService.deleteByNsxId(id);
        return ResponseEntity.ok("Xóa đơn nsx công");
    }
}