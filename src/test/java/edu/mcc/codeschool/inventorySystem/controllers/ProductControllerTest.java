package edu.mcc.codeschool.inventorySystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mcc.codeschool.inventorySystem.controllers.ProductController;
import edu.mcc.codeschool.inventorySystem.models.Product;
import edu.mcc.codeschool.inventorySystem.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Product Controller Test")
public class ProductControllerTest {
    @InjectMocks
    private ProductController subject;
    @Mock
    private ProductService productService;

    private MockMvc mockMvc;
    private HttpHeaders httpHeaders;
    private ObjectMapper mapper;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subject).build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ReflectionTestUtils.setField(this, "httpHeaders", httpHeaders);
        ReflectionTestUtils.setField(this, "mapper", new ObjectMapper());
    }


    // post for create
    @Test
    @DisplayName("Create Product - Success")
    void test_createProduct_Success() throws Exception {

        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Kayla");
        product.setPrice(100.00);
        MockHttpServletResponse response = mockMvc
                .perform(post("/api/v1/products")
                        .headers(httpHeaders)
                        .content(mapper.writeValueAsString(product)))
                .andReturn()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Get All Products - Success")
    void test_getAllProducts_Success() throws Exception {

        MockHttpServletResponse response = mockMvc
                .perform(get("/api/v1/products")
                        .headers(httpHeaders))
                .andReturn()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    @DisplayName("Get Product - Success")
    void test_getProductById_Success() throws Exception {

        MockHttpServletResponse response = mockMvc
                .perform(get("/api/v1/products/{id}", 1)
                        .headers(httpHeaders))
                .andReturn()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Update Product - Success")
    void test_updateProductById_Success() throws Exception {

        Product update = new Product();
        update.setId(UUID.randomUUID());
        update.setName("Kayla");
        update.setPrice(100.00);
        MockHttpServletResponse response = mockMvc
                .perform(put("/api/v1/products/{id}", 1)
                        .headers(httpHeaders)
                        .content(mapper.writeValueAsString(update)))
                .andReturn()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    } //ret 200

    @Test
    @DisplayName("Update Product Stock - Success")
    void test_updateProductStockById_Success() throws Exception {

        Product stock = new Product();
        MockHttpServletResponse response = mockMvc
                .perform(put("/api/v1/products/{id}/stock", 1)
                        .headers(httpHeaders)
                        .content(mapper.writeValueAsString(stock)))
                .andReturn()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    } // ret 400

    @Test
    @DisplayName("Update Product Stock- Not Found")
    void test_updateProductStockById_NotFound() throws Exception {

        MockHttpServletResponse response = mockMvc
                .perform(put("/api/v1/products/{id}/stock", 1)
                        .headers(httpHeaders))
                .andReturn()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Delete Product - Success")
    void test_deleteProductById_Success() throws Exception {

        MockHttpServletResponse response = mockMvc
                .perform(delete("/api/v1/products/{id}", 1)
                        .headers(httpHeaders))
                .andReturn()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


}
