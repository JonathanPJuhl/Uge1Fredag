package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public CustomerDTO getCustomerByID(int id){
        EntityManager em = emf.createEntityManager();
        try{
            BankCustomer customer = em.find(BankCustomer.class,id);
            CustomerDTO dto = new CustomerDTO(customer);
            return dto;
        }finally{  
            em.close();
        }
        
    }
     public BankCustomer addCustomer(BankCustomer customer){
        BankCustomer customer1 = new BankCustomer(customer.getFirstName(), 
                customer.getLastName(), customer.getAccountNumber(), 
                customer.getBalance(), customer.getCustomerRanking(), customer.getInternalInfo());
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer1);
            em.getTransaction().commit();
            return customer1;
        }finally {
            em.close();
        }
    }
     List<CustomerDTO> getCustomersByName(String name){
          EntityManager em = emf.createEntityManager();
          String[] splited = name.split("\\s+");
        try{
            TypedQuery<BankCustomer> q1 = em.createQuery("SELECT c FROM BankCustomer c WHERE c.firstName=:name1 AND c.lastName=:name2", BankCustomer.class);
            q1.setParameter("name1", splited[0]);
            q1.setParameter("name2", splited[1]);
            List<CustomerDTO> list = new ArrayList();
            for(BankCustomer bc : q1.getResultList()){
                list.add(new CustomerDTO(bc));
            }
            return list;
        }finally {
            em.close();
        }
         
         
     }
     public List<BankCustomer> getAllCustomers(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<BankCustomer> query = 
                       em.createQuery("Select c from BankCustomer c",BankCustomer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

}
