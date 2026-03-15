package pharama.example.adminpanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pharama.example.adminpanel.model.CategoryMaster;

public interface CategoryRepository extends JpaRepository<CategoryMaster, Long> {
}