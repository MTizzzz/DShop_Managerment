package Website2.service;

import Website2.model.entity.Nsx;
import Website2.model.request.CreateNsx;
import Website2.model.request.UpdateNsx;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface INsxService {
    List<Nsx> findAll();

    Optional<Nsx> findByNsxId(int id);

    void createNsx(CreateNsx createNsx) throws Exception;

    Nsx updateNsx(int id, UpdateNsx updateNsx) throws Exception;

    void deleteByNsxId(int id);
}
