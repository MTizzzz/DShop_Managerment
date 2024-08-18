package Website2.model.DTO;

import Website2.model.entity.Nsx;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NsxDTO {
    private Integer nsxId;
    private String nsxName;

    // Constructor nhận đối tượng Nsx
    public NsxDTO(Nsx nsx) {
        this.nsxId = nsx.getNsxId();
    }
}
