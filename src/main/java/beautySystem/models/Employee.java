package beautySystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee
{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String name;
   private Integer cpf;
   private String role;
   private String birth_date;

   public Integer getId()
   {
      return this.id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public String getName()
   {
      return this.name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Integer getCpf()
   {
      return this.cpf;
   }

   public void setCpf(Integer cpf)
   {
      this.cpf = cpf;
   }

   public String getRole()
   {
      return this.role;
   }

   public void setRole(String role)
   {
      this.role = role;
   }

   public String getBirth_date()
   {
      return this.birth_date;
   }

   public void setBirth_date(String birth_date)
   {
      this.birth_date = birth_date;
   }
}
