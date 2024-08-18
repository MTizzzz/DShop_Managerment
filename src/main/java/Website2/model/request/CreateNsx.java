package Website2.model.request;
import Website2.model.entity.NsxRole;
import lombok.Data;

@Data
public class CreateNsx {
    private Integer id;
    private NsxRole role;
}
