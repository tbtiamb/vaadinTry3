//package lab4;
//
//import javax.ejb.Local;
//import javax.ejb.Remote;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.Query;
//
//@Local
//public interface UserControllerInterface {
//    public static void addUser(String login, String password){//(@FormParam("login") String login, @FormParam("password") String password){
//        UsersEntity.addUser(login, password);
//    }
//    public static boolean authentication(String login, String password){//(@FormParam("login") String login, @FormParam("password") String password){
//        try {
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab4");
//            EntityManager em = emf.createEntityManager();
//            //String p = password;
//            Query query = em.createQuery("Select user FROM UsersEntity user WHERE user.login = :login AND user.password = :password");
//            query.setParameter("login", login);
//            query.setParameter("password", password.hashCode());
//            UsersEntity result = (UsersEntity) query.getSingleResult();
//            System.out.println("123");
//            return true;
//        }
//        catch (Exception e){
//            // return "321";
//            return false;
//        }
//    }
//}
