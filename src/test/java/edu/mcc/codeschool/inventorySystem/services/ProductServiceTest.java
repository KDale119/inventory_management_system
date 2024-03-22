package edu.mcc.codeschool.inventorySystem.services;

import edu.mcc.codeschool.inventorySystem.models.Product;
import edu.mcc.codeschool.inventorySystem.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Product Service Test")
public class ProductServiceTest {
    @InjectMocks
    private ProductService subject;

    @Test
    @DisplayName("Create Product")
    void test_createProduct(){
        Product product = new Product();
        subject.createProduct(product);

        assertThat(product.getId()).isNotNull();
        assertThat(subject.getProducts().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Get Product")
    void test_getProduct(){
        Product product = new Product();
        product = subject.createProduct(product);

        assertThat(product.getId()).isNotNull();
        ResponseEntity<Product> response = subject.getProduct(product.getId().toString());
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId().toString()).isEqualTo(product.getId().toString());
    }
    @Test
    @DisplayName("Get Product - Not Found")
    void test_getProduct_NotFound(){

        ResponseEntity<Product> response = subject.getProduct("1");
        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody()).isNull();
    }
    @Test
    @DisplayName("Update Product")
    void test_updateProduct(){
        Product product = new Product();
        product = subject.createProduct(product);
        Product update = new Product();
        product.setId(UUID.randomUUID());
        update.setName("tom");
        update.setPrice(100.00);
        update.setQuantity(5);

        assertThat(product.getId()).isNotNull();
        ResponseEntity<Product> response = subject.updateProduct(product.getId().toString(), update);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
    }
    @Test
    @DisplayName("Update Product - Bad Request")
    void test_updateProduct_BadRequest(){

        ResponseEntity<Product> response = subject.updateProduct("1", null);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }
    @Test
    @DisplayName("Update Product - Not Found")
    void test_updateProduct_NotFound(){
        Product update = new Product();
        ResponseEntity<Product> response = subject.updateProduct("1", update);
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    @DisplayName("Update Stock")
    void test_updateStock(){
        Product product = new Product();
        product = subject.createProduct(product);
        Product stock = new Product();
        stock.setQuantity(5);
        product.setId(UUID.randomUUID());
        product.setName("tom");
        product.setQuantity(stock.getQuantity());

        assertThat(product.getId()).isNotNull();
        ResponseEntity<Product> response = subject.updateStock(product.getId().toString(), stock);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
    }
    @Test
    @DisplayName("Update Stock - Bad Request")
    void test_updateStock_BadRequest(){
        ResponseEntity<Product> response = subject.updateStock("123", null);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }
    @Test
    @DisplayName("Update Stock - Not Found")
    void test_updateStock_NotFound(){
        Product update = new Product();
        ResponseEntity<Product> response = subject.updateStock("1", update);
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    @DisplayName("Delete Product")
    void test_deleteProduct(){
        Product product = new Product();
        product = subject.createProduct(product);

        ResponseEntity<Product> delete = subject.deleteProduct(product.getId().toString());

        assertThat(delete.getStatusCode().value()).isEqualTo(204);
    }
    @Test
    @DisplayName("Delete Product - Bad Request")
    void test_deleteProduct_BadRequest(){
        Product product = new Product();
        product.setQuantity(10);
        product = subject.createProduct(product);

        ResponseEntity<Product> response = subject.deleteProduct(product.getId().toString());
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }
    @Test
    @DisplayName("Delete Product - Not Found")
    void test_deleteProduct_NotFound(){

        ResponseEntity<Product> response = subject.deleteProduct("1");
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }
}
