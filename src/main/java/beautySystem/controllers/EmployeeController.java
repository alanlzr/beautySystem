package beautySystem.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import beautySystem.daos.EmployeeDao;
import beautySystem.models.Employee;

@Controller
@RequestMapping("/employee")
@Transactional
public class EmployeeController
{

   @Autowired
   private EmployeeDao employeeDao;

   @GetMapping("/form")
   public ModelAndView form(Employee employee)
   {
      ModelAndView modelAndView = new ModelAndView("employee/form-add");
      return modelAndView;

   }

   @PostMapping
   public ModelAndView save(@Valid Employee employee, BindingResult bindingResult)
   {
      if (bindingResult.hasErrors())
      {
         return form(employee);
      }
      employeeDao.save(employee);
      return new ModelAndView("redirect:/employee");
   }

   @GetMapping("/{id}")
   public ModelAndView load(@PathVariable("id") Integer id)
   {
      ModelAndView modelAndView = new ModelAndView("employee/form-update");
      modelAndView.addObject("employee", employeeDao.findById(id));
      return modelAndView;
   }

   @GetMapping
   public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page)
   {
      ModelAndView modelAndView = new ModelAndView("employee/list");
      modelAndView.addObject("paginatedList", employeeDao.paginated(page, 10));
      return modelAndView;
   }

   //just because get is easier here. Be my guest if you want to change.
   @GetMapping("/remove/{id}")
   public String remove(@PathVariable("id") Integer id)
   {
      Employee employee = employeeDao.findById(id);
      employeeDao.remove(employee);
      return "redirect:/employee";
   }

   @PostMapping("/{id}")
   public ModelAndView update(@PathVariable("id") Integer id, @Valid Employee employee, BindingResult bindingResult)
   {
      employee.setId(id);
      if (bindingResult.hasErrors())
      {
         return new ModelAndView("employee/form-update");
      }
      employeeDao.update(employee);
      return new ModelAndView("redirect:/employee");
   }
}
