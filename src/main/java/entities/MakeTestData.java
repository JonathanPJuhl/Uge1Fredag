package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jonathan
 */
public class MakeTestData {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(new BankCustomer("Mare33", "Ridt", "aax4567", 0, 0, "Han er dum"));
        em.getTransaction().commit();
        
        em.persist(new BankCustomer("Mare2", "Ridt2", "aax45678", 10, 10, "Han er mindre dumb"));
        em.getTransaction().commit();
        em.persist(new BankCustomer("Mare3", "Ridt2", "aax45678", 10, 10, "Han er mindre dumb"));
        em.getTransaction().commit();
        em.persist(new BankCustomer("Mare4", "Ridt2", "aax45678", 10, 10, "Han er mindre dumb"));
        em.getTransaction().commit();
        em.close();
    }
}
