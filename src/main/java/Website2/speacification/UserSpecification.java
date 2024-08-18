package Website2.speacification;
import Website2.model.entity.Users;
import Website2.model.request.FilterUser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
public class UserSpecification {
    public static Specification<Users> buildSpec(FilterUser form) {
        if (form == null) {
            return null;
        }
        return new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();
                // SELECT * FORM account WHERE username LIKE '%search%'
                // Lây username theo biến java
                if (StringUtils.hasText(form.getSearch())) {
                    predicates.add(builder.or(builder.like(root.get("username"), "%" + form.getSearch() + "%"),
                                    builder.like(root.get("fullName"), "%" + form.getSearch() + "%")
                            ));
                }
                if (form.getMinId() != null) {
                    predicates.add(builder.greaterThanOrEqualTo(
                            root.get("id"), form.getMinId()
                    ));
                }

                if (form.getMaxId() != null) {
                    predicates.add(builder.lessThanOrEqualTo(
                            root.get("id"), form.getMaxId()
                    ));
                }
                // Add sorting
                if (form.getIdAsc() != null && form.getIdAsc()) {
                    query.orderBy(builder.asc(root.get("id")));
                }
                if (form.getIdDesc() != null && form.getIdDesc()) {
                    query.orderBy(builder.desc(root.get("id")));
                }

                return builder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
