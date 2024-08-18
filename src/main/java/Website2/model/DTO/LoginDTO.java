package Website2.model.DTO;
import Website2.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private int userId;
    private String username;
    private Role role;
    private String userAgent;
    private String token;
}
