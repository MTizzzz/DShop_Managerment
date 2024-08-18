package Website2.model.request;

import lombok.Data;

@Data
public class FilterUser {
    private String search;
    private Integer minId;
    private Integer maxId;
    private Boolean  idAsc;
    private Boolean  idDesc;
}
