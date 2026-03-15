package pharama.example.adminpanel.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pharama.example.adminpanel.model.ProductMaster;

@Repository
public interface ProductRepository extends JpaRepository<ProductMaster, Long> {}