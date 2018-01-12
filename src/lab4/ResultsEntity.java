package lab4;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "results", schema = "s223552", catalog = "studs")
public class ResultsEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab4");
    public static EntityManager em = emf.createEntityManager();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double x;
    private Double y;
    private Double radius;
    private Boolean status;

    public ResultsEntity(){}
    public ResultsEntity(Double x, Double y, Double radius, Boolean status) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.status = status;
    }

    public static void addElem(Double x, Double y, Double radius, Boolean status){
        ResultsEntity el1 = new ResultsEntity(x, y, radius, status);
        em.getTransaction().begin();
        em.persist(el1);
        em.getTransaction().commit();
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "x")
    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    @Basic
    @Column(name = "y")
    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Basic
    @Column(name = "radius")
    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Basic
    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultsEntity that = (ResultsEntity) o;

        if (id != that.id) return false;
        if (x != null ? !x.equals(that.x) : that.x != null) return false;
        if (y != null ? !y.equals(that.y) : that.y != null) return false;
        if (radius != null ? !radius.equals(that.radius) : that.radius != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (x != null ? x.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (radius != null ? radius.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
