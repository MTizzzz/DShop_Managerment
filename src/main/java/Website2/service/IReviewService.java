package Website2.service;

import Website2.model.entity.Reviews;
import Website2.model.request.CreateReviews;

import Website2.model.request.UpdateReviews;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IReviewService {
    List<Reviews> findAllReviews();
    Optional<Reviews> findById(int id);
    void createReviews(CreateReviews createReviews) throws Exception;
    Reviews updateReviews(int id,UpdateReviews updateReviews);
    void deleteReviews(int id);
    List<Reviews> findByProductId(int productId);
}
