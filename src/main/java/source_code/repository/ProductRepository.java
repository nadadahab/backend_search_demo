package source_code.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import source_code.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    void deleteById(String id);
}

