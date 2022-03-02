package starbux.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import starbux.api.dto.Cart;
import starbux.service.CustomerService;

import java.util.HashMap;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;

    @MockBean
    private CustomerService customerService;

// failing test
    @Test
    public void testConfirmCart() throws Exception {
        Cart cart = new Cart(new HashMap<>(), 0.0, Optional.of(1L));
        mockMvc.perform(
                post("/cart/confirm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cart)))
                .andExpect(status().is2xxSuccessful());
        verify(customerService).confirmCart(cart);
    }

    @Test
    public void testGetDrinks() throws Exception {
                mockMvc
                .perform(get("/product/drink"))
                .andExpect(status().is2xxSuccessful());
        verify(customerService).getAllDrinks();
    }

    @Test
    public void testGetToppings() throws Exception {
        mockMvc
                .perform(get("/product/topping"))
                .andExpect(status().is2xxSuccessful());
        verify(customerService).getAllToppings();
    }
}