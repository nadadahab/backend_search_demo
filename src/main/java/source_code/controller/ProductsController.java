package source_code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_code.model.Product;
import source_code.service.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class ProductsController {

    @Autowired
    private ProductService productService;

    @PostMapping("/createDocument")
    public ResponseEntity<Object> createDocument(@RequestBody Product product) throws IOException {
        String response = productService.addProduct(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateDocument")
    public ResponseEntity<Object> updateDocument(@RequestBody Product product) throws IOException {
        String response = productService.updateProduct(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getDocument")
    public ResponseEntity<Object> getDocumentById() throws IOException {
        return new ResponseEntity<>("product", HttpStatus.OK);
    }

    @DeleteMapping("/deleteDocument")
    public ResponseEntity<Object> deleteDocumentById(@RequestParam String productId) throws IOException {
        String response =  productService.deleteProduct(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchDocuments")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/searchDocument")
    public ResponseEntity<Object> searchDocument(@RequestParam String name) throws IOException {
        List<Product> products = productService.getProductByName(name);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        return ResponseEntity.ok().headers(responseHeaders).body(products);
    }
}
