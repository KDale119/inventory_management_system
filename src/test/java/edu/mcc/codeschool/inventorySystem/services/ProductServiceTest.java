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
//        assertThat(product.getName()).isNotNull();
//        assertThat(product.getPrice()).isNotNull();
//        assertThat(product.getQuantity()).isNotNull();
//        assertThat(product.getSupplier()).isNotNull();
//        assertThat(product.getBrand()).isNotNull();
    }

    @Test
    @DisplayName("Get Product")
    void test_getProduct(){
        Product product = new Product();
        subject.createProduct(product);
        product.setId(product.getId());

        assertThat(product.getId()).isNotNull();

    }
    @Test
    @DisplayName("Update Product")
    void test_updateProduct(){
        Product product = new Product();
        subject.createProduct(product);
        Product update = new Product();
        update.setId(product.getId());

        assertThat(update.getId()).isNotNull();
    }

    @Test
    @DisplayName("Update Stock")
    void test_updateStock(){
        Product product = new Product();
        subject.createProduct(product);

        Product currentProduct = new Product();
        currentProduct.setQuantity(product.getQuantity());

        assertThat(currentProduct.getId()).isNotNull();
//        assertThat(currentProduct.getQuantity()).isNotNull();
    }

    @Test
    @DisplayName("Delete Product")
    void test_deleteProduct(){
        Product product = new Product();
        subject.createProduct(product);

        ResponseEntity<Product> delete = subject.deleteProduct(product.getId().toString());

        assertThat(delete.getStatusCode().value()).isEqualTo(204);

    }
}
