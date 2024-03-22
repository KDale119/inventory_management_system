package edu.mcc.codeschool.inventorySystem.controllers;

import edu.mcc.codeschool.inventorySystem.models.Product;
import edu.mcc.codeschool.inventorySystem.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product Controller")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @PostMapping
    @Operation(
            description = "Create Product",
            summary = "Allows the user to create a product with the supplied data",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product JSON Body",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Product.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(schema = @Schema(example = " "))}
                    ),
            }
    )
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }
    @GetMapping
    @Operation(
            description = "Get all games",
            summary = "Returns all available products",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Product.class))
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }
    @GetMapping("/{id}")
    @Operation(
            description = "Get product by ID",
            summary = "Returns product by the supplied ID",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product JSON Body",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Product.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(schema = @Schema(example = " "))}
                    ),
            }
    )
    public ResponseEntity<Product> getProductByID(@Valid @PathVariable String id) {
        return productService.getProduct(id);
    }
    @PutMapping("/{id}")
    @Operation(
            description = "Update product by ID",
            summary = "Allows the user to update a product with the supplied data",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product JSON Body",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Product.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(schema = @Schema(example = " "))}
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(schema = @Schema(example = " "))}
                    ),
            }
    )
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @Valid @RequestBody Product update) {
        return productService.updateProduct(id, update);
    }
    @PutMapping("/{id}/stock")
    @Operation(
            description = "Update stock by ID",
            summary = "Allows the user to update only the quantity of a product and leaves the rest of the data the same",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product JSON Body",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Product.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(schema = @Schema(example = " "))}
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(schema = @Schema(example = " "))}
                    ),
            }
    )
    public ResponseEntity<Product> updatedStock(@PathVariable String id, @RequestBody Product stock){
        return productService.updateStock(id, stock);
    }
    @DeleteMapping("/{id}")
    @Operation(
            description = "Delete product by ID",
            summary = "Allows the user to delete a product from inventory",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product JSON Body",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Product.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(schema = @Schema(example = " "))}
                    ),
            }
    )
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }
}
