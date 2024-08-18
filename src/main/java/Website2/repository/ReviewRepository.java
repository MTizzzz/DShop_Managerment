package Website2.repository;
import Website2.model.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews,Integer > {
    List<Reviews> findByProductId(int productId);

}
