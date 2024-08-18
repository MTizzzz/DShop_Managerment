package Website2.repository;
import Website2.model.entity.CartDetail;
import Website2.model.entity.CartDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, CartDetailPK> {

}
