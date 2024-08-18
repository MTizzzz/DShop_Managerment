package Website2.service;

import Website2.model.DTO.ProductDTO;
import Website2.model.DTO.ProductDTOv2;
import Website2.model.entity.Product;
import Website2.model.request.CreateProduct;
import Website2.model.request.FilterProduct;
import Website2.model.request.UpdateProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface IProductService {

    Page<Product> getAllProductsPage(Pageable pageable, FilterProduct filterProduct);

    ProductDTOv2 getProductById(int id);
    ProductDTO getProductByIdOld(int id);

    void createProduct(CreateProduct createProduct) throws Exception;

    Product updateProduct(int productId, UpdateProduct updateProduct) throws Exception;

    void deleteProduct(int id);

    boolean isProductNameExists(String productName);

    List<Product> getAllProductByTypeId(Integer typeId);
}
