package edu.mcc.codeschool.inventorySystem.services;

import edu.mcc.codeschool.inventorySystem.models.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ProductService {

    List<Product> products = new ArrayList<>();


    public Product createProduct(Product product) {
//        Product product = new Product();
        product.setId(UUID.randomUUID());
        products.add(product);
        return product;
    }

    public List<Product> getProducts() {
        return products;
    }

    public ResponseEntity<Product> getProduct(String id) {
        Optional<Product> prdct = products.stream().filter(p -> p.getId().toString().equals(id)).findFirst();
        if (prdct.isPresent()){
            return ResponseEntity.ok(prdct.get());
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Product> updateProduct(String id,Product update) {
        Optional<Product> prdct = products.stream().filter(products -> products.getId().toString().equals(id)).findFirst();
        if (prdct.isPresent()) {
            products.remove(prdct.get());
            products.add(update);
            return ResponseEntity.ok(update);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Product> updateStock(String id, Product stock){
        Optional<Product> prdct = products.stream().filter(products -> products.getId().toString().equals(id)).findFirst();
        if (prdct.isPresent()) {
            Product currentProduct = prdct.get();
            products.remove(currentProduct);
            currentProduct.setQuantity(stock.getQuantity());
            products.add(currentProduct);
            return ResponseEntity.ok(currentProduct);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        Optional<Product> prdct = products.stream().filter(products -> products.getId().toString().equals(id)).findFirst();
        if (prdct.get().getQuantity() == 0) {
            products.remove(prdct.get());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
