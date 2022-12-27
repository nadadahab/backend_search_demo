package source_code.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source_code.model.Product;
import source_code.open_search.repository.ProductSearchRepository;
import source_code.repository.ProductRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSearchRepository productSearchRepository;

    public Product getProductById(Long id) throws IOException {
        Product product = productSearchRepository.getDocumentById(id);
        return product;
    }

    public List<Product> getProductByName(String name) throws IOException {
        List<Product> products  = productSearchRepository.getDocumentByName(name);
        return products;
    }

    public List<Product> getAllProducts() throws IOException {
        List<Product> products  = productSearchRepository.searchAllDocuments();
        return products;
    }

    public String addProduct(Product product){
        //product = new Product(product);
        productRepository.save(product);
        return "created";
    }

    public String updateProduct(Product product) {
        productRepository.save(product);
        return "updated";
    }

    public String deleteProduct(String id) {
        productRepository.deleteById(id);
        return "deleted";
    }
}
