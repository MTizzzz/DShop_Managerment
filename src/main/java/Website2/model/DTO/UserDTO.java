package Website2.model.DTO;

import Website2.model.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String fullName;
    private String image;

    public UserDTO(Users user) {
        this.fullName = user.getFullName();
        this.image = user.getImage();
    }
}
