package Website2.model.request;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class FilterCategory {
    private String search;
    private Integer minId;
    private Integer maxId;
    private Boolean  idAsc;
    private Boolean  idDesc;
}
