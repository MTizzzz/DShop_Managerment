package Website2.speacification;
import Website2.model.entity.Product;
import Website2.model.request.FilterProduct;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> buildSpec(FilterProduct form) {
        if (form == null) {
            return null;
        }
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();

                // Filter by product name
                if (StringUtils.hasText(form.getSearch())) {
                    predicates.add(builder.like(root.get("productName"), "%" + form.getSearch() + "%"));
                }

                // Filter by price range
                if (form.getPriceMin() != null) {
                    predicates.add(builder.greaterThanOrEqualTo(root.get("price"), form.getPriceMin()));
                }

                if (form.getPriceMax() != null) {
                    predicates.add(builder.lessThanOrEqualTo(root.get("price"), form.getPriceMax()));
                }

                // Add sorting
                if (form.getPriceAsc() != null && form.getPriceAsc()) {
                    query.orderBy(builder.asc(root.get("price")));
                }
                if (form.getPriceDesc() != null && form.getPriceDesc()) {
                    query.orderBy(builder.desc(root.get("price")));
                }

                if (form.getCategoryId() != null) {
                    predicates.add(builder.equal(root.get("category").get("categoryId"), form.getCategoryId()));
                }

                if (form.getTypeNsx() != null) {
                    predicates.add(builder.equal(root.get("nsx").get("nsxRole"), form.getTypeNsx()));
                }

                return builder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
