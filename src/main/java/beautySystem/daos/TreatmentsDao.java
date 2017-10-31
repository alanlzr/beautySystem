package beautySystem.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import beautySystem.models.PaginatedList;
import beautySystem.models.Treatments;

@Repository
public class TreatmentsDao
{

   @PersistenceContext
   private EntityManager manager;

   public List<Treatments> all()
   {
      return manager.createQuery("select t from Treatments t", Treatments.class).getResultList();
   }

   public void save(Treatments treatments)
   {
      manager.persist(treatments);
   }

   public Treatments findById(Integer id)
   {
      return manager.find(Treatments.class, id);
   }

   public void remove(Treatments treatments)
   {
      manager.remove(treatments);
   }

   public void update(Treatments treatments)
   {
      manager.merge(treatments);
   }

   public PaginatedList paginated(int page, int max)
   {
      return new PaginatorQueryHelper().list(manager, Treatments.class, page, max);
   }

}
