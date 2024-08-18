package Website2.service;


import Website2.model.entity.Category;
import Website2.model.request.CreateCategory;
import Website2.model.request.FilterCategory;
import Website2.model.request.UpdateCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public interface ICategoryService {
//    List<Category> findAll();

    Page<Category> getAllCategoryPage(Pageable pageable, FilterCategory filterProduct);

    Optional<Category> findByCategoryId(int categoryId);

    Category updateCategory(int categoryId, UpdateCategory updateCategory) throws Exception;

    void createCategory(CreateCategory createCategory);

    void deleteByCategoryId(int categoryId);
}
