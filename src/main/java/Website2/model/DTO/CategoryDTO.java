package Website2.model.DTO;

import Website2.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Integer categoryId;

    private String categoryName;

    private String Description;
    public CategoryDTO(Category category) {
        this.categoryId = category.getCategoryId();
    }
}