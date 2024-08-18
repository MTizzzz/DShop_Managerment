package Website2.controller;
import Website2.model.DTO.ReviewsDTO;
import Website2.model.entity.Reviews;
import Website2.model.request.*;
import Website2.service.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequestMapping("api/v1/reviews")
@CrossOrigin("*")
@Validated
public class ReviewsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IReviewService reviewService;

    // Get all reviews
    @GetMapping("/find-all-reviews")
    public ResponseEntity<List<ReviewsDTO>> findAllReview() {
        List<Reviews> reviews = reviewService.findAllReviews();
        List<ReviewsDTO> reviewsDTOS = reviews.stream()
                .map(review -> mapper.map(review, ReviewsDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewsDTOS);
    }

    // Get reviews by product ID
    @GetMapping("/find-reviews-by-product-id")
    public ResponseEntity<List<ReviewsDTO>> getReviewsByProductId(@RequestParam int productId) {
        List<Reviews> reviews = reviewService.findByProductId(productId);
        List<ReviewsDTO> reviewsDTOS = reviews.stream()
                .map(review -> mapper.map(review, ReviewsDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewsDTOS);
    }

    // Get review by ID
    @GetMapping("/find-reviews-by-id/{id}")
    public ResponseEntity<ReviewsDTO> findReviewById(@PathVariable int id) {
        Optional<Reviews> review = reviewService.findById(id);
        ReviewsDTO reviewsDTO = mapper.map(review, ReviewsDTO.class);
        return ResponseEntity.ok(reviewsDTO);
    }

    // Create a new review
    @PostMapping("/create-reviews")
    public ResponseEntity<String> createReview(@Valid @RequestBody CreateReviews request) throws Exception {
        reviewService.createReviews(request);
        return ResponseEntity.ok("Thêm đánh giá thành công");
    }

    // Update a review
    @PutMapping("/update-reviews/{id}")
    public ResponseEntity<String> updateReview(@PathVariable int id, @Valid @RequestBody UpdateReviews request) {
        reviewService.updateReviews(id, request);
        return ResponseEntity.ok("Sửa đánh giá thành công");
    }

    // Delete a review
    @DeleteMapping("/delete-reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id) {
        reviewService.deleteReviews(id);
        return ResponseEntity.ok("Xóa đánh giá thành công");
    }
}
