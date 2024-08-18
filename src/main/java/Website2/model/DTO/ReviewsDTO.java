package Website2.model.DTO;
import Website2.model.entity.Reviews;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class ReviewsDTO {
    private String content;
    private int rate;
    private LocalDateTime createTimeReview;
    private UserDTO userDTO;  // Thêm UserDTO để chứa thông tin của người dùng
    public ReviewsDTO(Reviews review) {
        this.content = review.getContent();
        this.rate = review.getRate();
        this.createTimeReview = review.getCreateTimeReview();
        if (review.getUser() != null) {
            this.userDTO = new UserDTO(review.getUser());  // Ánh xạ Users sang UserDTO
        }
    }
}

