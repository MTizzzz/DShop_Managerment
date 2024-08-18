package Website2.model.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateReviews {
    private String content;
    private int rate;
    private LocalDateTime createTimeReview;
    private int userId;
    private int productId;
}