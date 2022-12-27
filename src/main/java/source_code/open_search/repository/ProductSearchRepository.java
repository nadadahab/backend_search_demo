package source_code.open_search.repository;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.GetResponse;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import source_code.model.Product;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductSearchRepository implements Repository {

        @Autowired
        private OpenSearchClient opensearchClient;

        private final String indexName = "products";

        public Product getDocumentById(Long productId) throws IOException{
            Product product = null;
            GetResponse<Product> response = opensearchClient.get(g -> g
                            .index(indexName)
                            .id(String.valueOf(productId)),
                    Product.class
            );

            if (response.found()) {
                product = response.source();
                System.out.println("Product name " + product.getName());
            } else {
                System.out.println ("Product not found");
            }

            return product;
        }

    public List<Product> getDocumentByName(String searchText) throws IOException{
        SearchResponse searchResponse =  opensearchClient.search(s -> s
                        .index("products")
                        .query(q -> q
                                .wildcard(t -> t .value("*"+searchText+"*") .field("name"))
                        ),
                Product.class
        );
        List<Hit> hits = searchResponse.hits().hits();
        List<Product> products = new ArrayList<>();
        for(Hit object : hits){

            System.out.print(((Product) object.source()));
            products.add((Product) object.source());

        }
        return products;
    }

        public  List<Product> searchAllDocuments() throws IOException {

            SearchRequest searchRequest =  SearchRequest.of(s -> s.index(indexName));
            SearchResponse searchResponse =  opensearchClient.search(searchRequest, Product.class);
            List<Hit> hits = searchResponse.hits().hits();
            List<Product> products = new ArrayList<>();
            for(Hit object : hits){

                System.out.print(((Product) object.source()));
                products.add((Product) object.source());

            }
            return products;
        }

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}