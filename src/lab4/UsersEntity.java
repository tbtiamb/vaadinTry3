package lab4;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users", schema = "s223552", catalog = "studs")
public class UsersEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    private String login;
    private Integer password;

    public UsersEntity() {}

    public UsersEntity(String login, Integer password) {
        this.login = login;
        this.password = password;
    }

    public static void addUser(String login, String password){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab4");
        EntityManager em = emf.createEntityManager();
        UsersEntity user = new UsersEntity(login, password.hashCode());
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
