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
import starbux.repository.entity.Drink;
import starbux.repository.entity.Topping;
import starbux.service.AdminService;
import starbux.service.CustomerService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;

    @MockBean
    private AdminService adminService;
    @MockBean
    private CustomerService customerService;

    // failing test
    @Test
    public void testCreateDrink() throws Exception {
        Drink drink = new Drink("Smoothie", 10.0);
        mockMvc.perform(
                post("/admin/drink")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(drink)))
                .andExpect(status().is2xxSuccessful());
        verify(adminService).createDrink(drink);
    }

    // failing test
    @Test
    public void testCreateTopping()  throws Exception {
        Topping topping = new Topping("sparkles", 10.0);
        mockMvc.perform(
                        post("/admin/topping")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(topping)))
                .andExpect(status().is2xxSuccessful());
        verify(adminService).createTopping(topping);
    }

    // failing test
    @Test
    public void testDeleteDrink()  throws Exception {
        mockMvc.perform(
                        delete("/admin/drink/1"))
                .andExpect(status().is2xxSuccessful());
        verify(adminService).deleteDrink(1l);
    }

    // failing test
    @Test
    public void testDeleteTopping()  throws Exception {
        mockMvc.perform(
                        delete("/admin/topping/1"))
                .andExpect(status().is2xxSuccessful());
        verify(adminService).deleteTopping(1l);
    }

    // failing test
    @Test
    public void testUpdateTopping() throws Exception {
        Topping topping = new Topping("sparkles", 10.0);
        mockMvc.perform(
                        put("/admin/topping/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(topping)))
                .andExpect(status().is2xxSuccessful());
        verify(adminService).updateTopping(1l, topping);
    }

    // failing test
    @Test
    public void testUpdateDrink() throws Exception {
        Drink drink = new Drink("Smoothie", 10.0);
        mockMvc.perform(
                        put("/admin/drink/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(drink)))
                .andExpect(status().is2xxSuccessful());
        verify(adminService).updateDrink(1l, drink);
    }

    @Test
    public void testGetAmountOfOrdersPerCustomer() throws Exception {
        mockMvc
                .perform(get("/admin/order/customer/1"))
                .andExpect(status().is2xxSuccessful());
        verify(customerService).getAmountOfOrdersPerCustomer(1l);
    }

    @Test
    public void testGetMostUsedTopping() throws Exception {
        mockMvc
                .perform(get("/admin/topping/most-used"))
                .andExpect(status().is2xxSuccessful());
        verify(adminService).getMostUsedTopping();

    }

}
