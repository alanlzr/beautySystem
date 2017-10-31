package beautySystem.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import beautySystem.models.PaginatedList;
import beautySystem.models.Employee;

@Repository
public class EmployeeDao
{

   @PersistenceContext
   private EntityManager manager;

   public List<Employee> all()
   {
      return manager.createQuery("select e from Employee e", Employee.class).getResultList();
   }

   public void save(Employee employee)
   {
      manager.persist(employee);
   }

   public Employee findById(Integer id)
   {
      return manager.find(Employee.class, id);
   }

   public void remove(Employee employee)
   {
      manager.remove(employee);
   }

   public void update(Employee employee)
   {
      manager.merge(employee);
   }

   public PaginatedList paginated(int page, int max)
   {
      return new PaginatorQueryHelper().list(manager, Employee.class, page, max);
   }

}
