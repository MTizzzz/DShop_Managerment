package Website2.controller;

import Website2.model.DTO.CategoryDTO;
import Website2.model.entity.Category;
import Website2.model.request.CreateCategory;
import Website2.model.request.FilterCategory;
import Website2.model.request.UpdateCategory;
import Website2.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/categories")
@CrossOrigin("*")
@Validated
public class    CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/find-all-category")
    public ResponseEntity<Page<CategoryDTO>> findAllCategoryPage(Pageable pageable, FilterCategory filterCategory) {
        Page<Category> categoryPage = categoryService.getAllCategoryPage(pageable, filterCategory);
        Page<CategoryDTO> categoryDTOPage = categoryPage.map(category -> mapper.map(category, CategoryDTO.class));
        return ResponseEntity.ok(categoryDTOPage);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        Optional<Category> categoryOptional = categoryService.findByCategoryId(id);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            CategoryDTO categoryDTO = mapper.map(category, CategoryDTO.class);
            return ResponseEntity.ok(categoryDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create-category")
    public ResponseEntity<String> createCategory(@RequestBody @Validated CreateCategory createCategory) {
        categoryService.createCategory(createCategory);
        return ResponseEntity.ok("Thêm loại thành công");
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @RequestBody @Validated UpdateCategory updateCategory) {
        try {
            categoryService.updateCategory(id, updateCategory);
            return ResponseEntity.ok("Sửa loại thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sửa loại thất bại: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteByCategoryId(id);
        return ResponseEntity.ok("Xóa loại thành công");
    }
}
