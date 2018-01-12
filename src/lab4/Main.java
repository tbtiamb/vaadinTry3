package lab4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab4");
        EntityManager em = emf.createEntityManager();
//        Calendar c = Calendar.getInstance();
//        Query query = em.createQuery("Select stud FROM НУченикиEntity stud WHERE stud.конец < :current_date");
//        query.setParameter("current_date", c.get(c.YEAR));
//        List<НУченикиEntity> stud = query.getResultList();
//        System.out.println(stud.get(0).getИд().toString());
        ResultsEntity res = new ResultsEntity(1.1, 1.1, 1.1, true);
        em.getTransaction().begin();
        em.persist(res);
        em.getTransaction().commit();
    }
}
