package beautySystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Client
{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String name;
   private Integer age;
   private String birth_date;
   private String phone;

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

   public Integer getAge()
   {
      return this.age;
   }

   public void setAge(Integer age)
   {
      this.age = age;
   }

   public String getBirth_date()
   {
      return this.birth_date;
   }

   public void setBirth_date(String birth_date)
   {
      this.birth_date = birth_date;
   }

   public String getPhone()
   {
      return this.phone;
   }

   public void setPhone(String phone)
   {
      this.phone = phone;
   }
}
