package Website2.repository;

import Website2.model.entity.Nsx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NsxRepository extends JpaRepository<Nsx,Integer> {

}
