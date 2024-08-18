package Website2.service.Class;

import Website2.model.entity.Nsx;

import Website2.model.request.CreateNsx;
import Website2.model.request.UpdateNsx;
import Website2.repository.NsxRepository;
import Website2.service.INsxService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NsxService implements INsxService {
    @Autowired
    private NsxRepository nsxRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Nsx> findAll() {
        return nsxRepository.findAll();
    }

    @Override
    public Optional<Nsx> findByNsxId(int id) {
        return nsxRepository.findById(id);
    }

    @Override
    public void createNsx(CreateNsx createNsx) throws Exception {
        Optional<Nsx> existingNsx = nsxRepository.findById(createNsx.getId());
        if (existingNsx.isPresent()){
            throw new Exception("Không có sản phẩm nào thuộc nhà sản xuất");
        }
        Nsx nsxDb = modelMapper.map(createNsx, Nsx.class);
        nsxRepository.save(nsxDb);
    }

    @Override
    public Nsx updateNsx(int id, UpdateNsx updateNsx) throws Exception {
        Optional<Nsx> nsxDb = findByNsxId(id);
        if (nsxDb.isPresent()){
            Nsx existingNsx = nsxDb.get();
            modelMapper.map(updateNsx, existingNsx);
            return nsxRepository.save(existingNsx);
        }else {
            throw new Exception("NSX không tìm thấy id");
        }

    }


    @Override
    public void deleteByNsxId(int id) {
        nsxRepository.deleteById(id);
    }
}
