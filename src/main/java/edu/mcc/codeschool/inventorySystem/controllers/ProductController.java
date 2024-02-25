package edu.mcc.codeschool.inventorySystem.controllers;

import edu.mcc.codeschool.inventorySystem.models.Product;
import edu.mcc.codeschool.inventorySystem.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@Valid @PathVariable String id) {
        return productService.getProduct(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @Valid @RequestBody Product update) {
        return productService.updateProduct(id, update);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Product> updatedStock(@PathVariable String id, @RequestBody Product stock){
        return productService.updateStock(id, stock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

}
