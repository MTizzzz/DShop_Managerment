package Website2.service.Class;

import Website2.model.DTO.*;
import Website2.model.entity.*;
import Website2.model.request.CreateProduct;
import Website2.model.request.FilterProduct;
import Website2.model.request.UpdateProduct;
import Website2.repository.CategoryRepository;
import Website2.repository.NsxRepository;
import Website2.repository.ProductRepository;
import Website2.repository.ReviewRepository;
import Website2.service.IProductService;
import Website2.speacification.ProductSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NsxRepository nsxRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void createProduct(CreateProduct createProduct) throws Exception {
        // Tìm kiếm các thực thể liên quan (Category và Nsx)
        Category category = categoryRepository.findById(createProduct.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + createProduct.getCategoryId()));

        Nsx nsx = nsxRepository.findById(createProduct.getNsxId())
                .orElseThrow(() -> new EntityNotFoundException("Nsx not found with id: " + createProduct.getNsxId()));

        // Tạo mới đối tượng Product và thiết lập các thuộc tính
        Product product = new Product();
        product.setProductId(createProduct.getProductId());
        product.setProductCode(String.valueOf(createProduct.getProductCode()));
        product.setProductName(createProduct.getProductName());
        product.setProductDescription(createProduct.getDescriptionProduct());
        product.setPrice(createProduct.getPrice());
        product.setDiscount(createProduct.getDiscount());
        product.setImage(createProduct.getImage());
        product.setStatus(createProduct.getStatus());
        product.setCreateTime(LocalDateTime.now());  // Thiết lập thời gian tạo
        product.setSoLuongTonKho(createProduct.getSoLuongTonKho());
        product.setCategoryId(category.getCategoryId());
        product.setNsxId(nsx.getNsxId());
        // Lưu đối tượng Product vào cơ sở dữ liệu
        productRepository.save(product);
    }

   @Override
    public Product updateProduct(int productId, UpdateProduct updateProduct) throws Exception {
           Product existingProduct = productRepository.findById(productId)
                   .orElseThrow(() -> new Exception("Product with ID " + productId + " not found"));

    // Thủ công ánh xạ các trường cần thiết từ updateProduct sang existingProduct
    if (updateProduct.getProductName() != null) {
        existingProduct.setProductName(updateProduct.getProductName());
    }
    if (updateProduct.getDescriptionProduct() != null) {
        existingProduct.setProductDescription(updateProduct.getDescriptionProduct());
    }
    if (updateProduct.getPrice() != 0) {
        existingProduct.setPrice(updateProduct.getPrice());
    }
    if (updateProduct.getDiscount() != 0) {
        existingProduct.setDiscount(updateProduct.getDiscount());
    }
    if (updateProduct.getImage() != null) {
        existingProduct.setImage(updateProduct.getImage());
    }
    if (updateProduct.getStatus() != null) {
        existingProduct.setStatus(updateProduct.getStatus());
    }
    if (updateProduct.getSoLuongTonKho() != 0) {
        existingProduct.setSoLuongTonKho(updateProduct.getSoLuongTonKho());
    }
    // Tìm và thiết lập category nếu có
    if (updateProduct.getCategoryId() != null) {
        Category category = categoryRepository.findById(updateProduct.getCategoryId())
                .orElseThrow(() -> new Exception("Category with ID " + updateProduct.getCategoryId() + " not found"));
        existingProduct.setCategory(category);
    }
    // Tìm và thiết lập nsx nếu có
    if (updateProduct.getNsxId() != null) {
        Nsx nsx = nsxRepository.findById(updateProduct.getNsxId())
                .orElseThrow(() -> new Exception("Nsx with ID " + updateProduct.getNsxId() + " not found"));
        existingProduct.setNsx(nsx);
    }
    return productRepository.save(existingProduct);
}
    @Override
    public Page<Product> getAllProductsPage(Pageable pageable, FilterProduct filterProduct) {
        Specification<Product> spec = ProductSpecification.buildSpec(filterProduct);
        return productRepository.findAll(spec, pageable);
    }

    @Override
    public ProductDTOv2 getProductById(int id) {
        // Lấy Product theo ID
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        // Lấy danh sách Reviews theo productId
        List<Reviews> reviews = reviewRepository.findByProductId(id);

        // Tạo và trả về ProductDTOv2
        return new ProductDTOv2(product, reviews);
    }

    @Override
    public ProductDTO getProductByIdOld(int id) {
        // Tìm sản phẩm theo ID trong cơ sở dữ liệu
        Optional<Product> optionalProduct = productRepository.findById(id);

        // Kiểm tra nếu sản phẩm có tồn tại
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Chuyển đổi từ entity Product sang DTO ProductDTO
            ProductDTO productDTO = new ProductDTO(product);
            productDTO.setProductId(product.getProductId());
            productDTO.setProductCode(Integer.parseInt(product.getProductCode())); // Assuming productCode is String
            productDTO.setProductName(product.getProductName());
            productDTO.setDescriptionProduct(product.getProductDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setDiscount(product.getDiscount());
            productDTO.setImage(product.getImage());
            productDTO.setStatus(product.getStatus());
            productDTO.setCreatedTime(product.getCreateTime());
            productDTO.setSoLuongTonKho(product.getSoLuongTonKho());

            // Nếu Product có quan hệ với Nsx và Category, ánh xạ chúng sang DTO tương ứng
            if (product.getNsx() != null) {
                NsxDTO nsxDTO = new NsxDTO(product.getNsx());
                productDTO.setNsxDTO(nsxDTO);
            }

            if (product.getCategory() != null) {
                CategoryDTO categoryDTO = new CategoryDTO(product.getCategory());
                productDTO.setCategoryDTO(categoryDTO);
            }

            return productDTO; // Trả về ProductDTO
        } else {
            return null; // Trả về null nếu không tìm thấy sản phẩm
        }
    }


    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean isProductNameExists(String productName) {
        return false; // Implement as needed
    }

    @Override
    public List<Product> getAllProductByTypeId(Integer typeId) {
        return null; // Implement as needed
    }
}
