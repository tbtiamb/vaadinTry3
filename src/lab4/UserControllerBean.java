package lab4;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Stateful
public class UserControllerBean{// implements UserControllerInterface{

//    public UserControllerBean() {
//    }

    public void addUser(String login, String password){
        UsersEntity.addUser(login, password);
    }
    public boolean authentication(String login, String password){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab4");
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("Select user FROM UsersEntity user WHERE user.login = :login AND user.password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password.hashCode());
            UsersEntity result = (UsersEntity) query.getSingleResult();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
