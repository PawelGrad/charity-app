package pl.coderslab.charity.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.Model.Category.CategoryEntity;

public interface CategoryRepository  extends JpaRepository<CategoryEntity, Long> {
}
