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

import beautySystem.models.Treatments;
import beautySystem.daos.TreatmentsDao;

@Controller
@RequestMapping("/treatments")
@Transactional
public class TreatmentsController
{

   @Autowired
   private TreatmentsDao treatmentsDao;

   @GetMapping("/form")
   public ModelAndView form(Treatments treatments)
   {
      ModelAndView modelAndView = new ModelAndView("treatments/form-add");
      return modelAndView;

   }

   @PostMapping
   public ModelAndView save(@Valid Treatments treatments, BindingResult bindingResult)
   {
      if (bindingResult.hasErrors())
      {
         return form(treatments);
      }
      treatmentsDao.save(treatments);
      return new ModelAndView("redirect:/treatments");
   }

   @GetMapping("/{id}")
   public ModelAndView load(@PathVariable("id") Integer id)
   {
      ModelAndView modelAndView = new ModelAndView("treatments/form-update");
      modelAndView.addObject("treatments", treatmentsDao.findById(id));
      return modelAndView;
   }

   @GetMapping
   public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page)
   {
      ModelAndView modelAndView = new ModelAndView("treatments/list");
      modelAndView.addObject("paginatedList", treatmentsDao.paginated(page, 10));
      return modelAndView;
   }

   //just because get is easier here. Be my guest if you want to change.
   @GetMapping("/remove/{id}")
   public String remove(@PathVariable("id") Integer id)
   {
      Treatments treatments = treatmentsDao.findById(id);
      treatmentsDao.remove(treatments);
      return "redirect:/treatments";
   }

   @PostMapping("/{id}")
   public ModelAndView update(@PathVariable("id") Integer id, @Valid Treatments treatments, BindingResult bindingResult)
   {
      treatments.setId(id);
      if (bindingResult.hasErrors())
      {
         return new ModelAndView("treatments/form-update");
      }
      treatmentsDao.update(treatments);
      return new ModelAndView("redirect:/treatments");
   }
}
