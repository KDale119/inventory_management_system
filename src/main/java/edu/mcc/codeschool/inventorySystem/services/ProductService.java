package edu.mcc.codeschool.inventorySystem.services;

import edu.mcc.codeschool.inventorySystem.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    List<Product> products = new ArrayList<>();

    public Product createProduct(){
        Product product = new Product();
        product.setId(UUID.randomUUID());
        products.add(product);
        return product;
    }

    public List<Product> getProducts() {
        return products;
    }

    //    public ResponseEntity<Product> getAllProducts(String id) {
//        Optional<Product> product = products.stream().filter(p -> p.getId().toString().equals(id)).findFirst();
//        if (product.isPresent()){
//            return ResponseEntity.ok(product.get());
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
}
