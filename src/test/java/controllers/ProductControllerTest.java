package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mcc.codeschool.inventorySystem.controllers.ProductController;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


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
    @DisplayName("Get Product - Not Found")
    void test_getProductById_NotFound() throws Exception {

        MockHttpServletResponse response = mockMvc
                .perform(get("/api/v1/products/{id}", 1)
                        .headers(httpHeaders))
                .andReturn()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    } // ret 200 should be 405

    @Test
    @DisplayName("Update Product - Success")
    void test_updateProductById_Success() throws Exception {

        MockHttpServletResponse response = mockMvc
                .perform(put("/api/v1/products/{id}", 1)
                        .headers(httpHeaders))
                .andReturn()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    } // ret 400 should be 200
    @Test
    @DisplayName("Update Product - Not Found")
    void test_updateProductById_NotFound() throws Exception {

        MockHttpServletResponse response = mockMvc
                .perform(put("/api/v1/products/{id}", 1)
                        .headers(httpHeaders))
                .andReturn()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }// ret 400

    @Test
    @DisplayName("Update Product Stock - Success")
    void test_updateProductStockById_Success() throws Exception {

        MockHttpServletResponse response = mockMvc
                .perform(put("/api/v1/products/{id}/stock", 1)
                        .headers(httpHeaders))
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

    @Test
    @DisplayName("Delete Product - Failure")
    void test_deleteProductById_Failure() throws Exception {

        MockHttpServletResponse response = mockMvc
                .perform(delete("/api/v1/products/{id}", 1)
                        .headers(httpHeaders))
                .andReturn()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }// ret 200
}
