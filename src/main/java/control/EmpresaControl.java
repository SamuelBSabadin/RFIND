package control;

import model.Empresa;
import jakarta.persistence.*;

import java.util.List;

public class EmpresaControl {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rfind-db");
    private EntityManager entityManager;
    public EmpresaControl()
    {
        entityManager = entityManagerFactory.createEntityManager();
    }
    public void insert(Empresa empresa){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(empresa);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Empresa> select()
    {
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Empresa> listEmpresas = entityManager.createQuery("select e from Empresa e",Empresa.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return listEmpresas;
    }
    public void deleteById(int id)
    {
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete e from Empresa e where e.id = :id").setParameter("id",id).executeUpdate();
        entityManager.close();
    }
}
