package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static FacadeExample facade;

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = FacadeExample.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("BankCustomer.deleteAllRows").executeUpdate();
            em.persist(new BankCustomer("Mare45", "Ridt2", "aax45678", 10, 10, " Han er mindre dumb"));
            em.persist(new BankCustomer("Mare46", "Ridt2", "aax45678", 10, 10, " Han er mindre dumb"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
  @Test
    public void testOfAddAndFindCustomerCount() {
        BankCustomer customer = new BankCustomer("Mare4", "Ridt2", "aax45678", 10, 10, " Han er mindre dumb");
        BankCustomer add = facade.addCustomer(customer);
        List<BankCustomer> count = facade.getAllCustomers();
        int expected = 3;
        int actual = count.size();
        assertEquals( expected, actual, "Expects the same output of employee from the database");
   
    }
    @Test
    public void testOfFindCustomerByName() {
        BankCustomer customer = new BankCustomer("Mare4", "Ridt2", "aax45678", 10, 10, " Han er mindre dumb");
        facade.addCustomer(customer);
        facade.addCustomer(customer);
        List<CustomerDTO> list = facade.getCustomersByName("Mare4 Ridt2");
        int actual = list.size();
        int expected = 2;
        assertEquals( expected, actual, "Expects the same output of nr of employees from the database");
   
    }
     @Test
    public void testOfFindCustomerByID() {
        BankCustomer customer = new BankCustomer("Mare4444", "Ridt2", "aax45678", 10, 10, " Han er mindre dumb");
        BankCustomer cust = facade.addCustomer(customer);
        CustomerDTO cust1 = facade.getCustomerByID(cust.getId());
         Assertions.assertTrue(cust1!=null); 
   
    }
   
}
