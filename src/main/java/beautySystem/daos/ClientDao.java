package beautySystem.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import beautySystem.models.PaginatedList;
import beautySystem.models.Client;

@Repository
public class ClientDao
{

   @PersistenceContext
   private EntityManager manager;

   public List<Client> all()
   {
      return manager.createQuery("select c from Client c", Client.class).getResultList();
   }

   public void save(Client client)
   {
      manager.persist(client);
   }

   public Client findById(Integer id)
   {
      return manager.find(Client.class, id);
   }

   public void remove(Client client)
   {
      manager.remove(client);
   }

   public void update(Client client)
   {
      manager.merge(client);
   }

   public PaginatedList paginated(int page, int max)
   {
      return new PaginatorQueryHelper().list(manager, Client.class, page, max);
   }

}
