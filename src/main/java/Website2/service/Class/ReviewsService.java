package Website2.service.Class;

import Website2.model.entity.*;

import Website2.model.request.CreateReviews;
import Website2.model.request.UpdateReviews;
import Website2.repository.ProductRepository;
import Website2.repository.ReviewRepository;
import Website2.repository.UserRepository;
import Website2.service.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewsService implements IReviewService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Reviews> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Reviews> findById(int id) {
        return reviewRepository.findById(id);

    }

    @Override
    public void createReviews(CreateReviews createReviews) {
        Product product = productRepository.findById(createReviews.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + createReviews.getProductId()));
        Users user = userRepository.findById(createReviews.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + createReviews.getUserId()));

        Reviews review = new Reviews();
        review.setUserId(user.getUserId());
        review.setProductId(product.getProductId());
        review.setContent(createReviews.getContent());
        review.setRate(createReviews.getRate());
        review.setCreateTimeReview(LocalDateTime.now());
        reviewRepository.save(review);
    }




    @Override
    public Reviews updateReviews(int id, UpdateReviews updateReviews) {
        Reviews existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));
            existingReview.setContent(updateReviews.getContent());
            existingReview.setRate(updateReviews.getRate());

        return reviewRepository.save(existingReview);
    }

    @Override
    public void deleteReviews(int id) {
        Reviews existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));

        reviewRepository.delete(existingReview);
    }

    @Override
    public List<Reviews> findByProductId(int productId) {
        return null;
    }

}
