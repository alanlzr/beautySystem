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

import beautySystem.models.Client;
import beautySystem.daos.ClientDao;

@Controller
@RequestMapping("/client")
@Transactional
public class ClientController
{

   @Autowired
   private ClientDao clientDao;

   @GetMapping("/form")
   public ModelAndView form(Client client)
   {
      ModelAndView modelAndView = new ModelAndView("client/form-add");
      return modelAndView;

   }

   @PostMapping
   public ModelAndView save(@Valid Client client, BindingResult bindingResult)
   {
      if (bindingResult.hasErrors())
      {
         return form(client);
      }
      clientDao.save(client);
      return new ModelAndView("redirect:/client");
   }

   @GetMapping("/{id}")
   public ModelAndView load(@PathVariable("id") Integer id)
   {
      ModelAndView modelAndView = new ModelAndView("client/form-update");
      modelAndView.addObject("client", clientDao.findById(id));
      return modelAndView;
   }

   @GetMapping
   public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page)
   {
      ModelAndView modelAndView = new ModelAndView("client/list");
      modelAndView.addObject("paginatedList", clientDao.paginated(page, 10));
      return modelAndView;
   }

   //just because get is easier here. Be my guest if you want to change.
   @GetMapping("/remove/{id}")
   public String remove(@PathVariable("id") Integer id)
   {
      Client client = clientDao.findById(id);
      clientDao.remove(client);
      return "redirect:/client";
   }

   @PostMapping("/{id}")
   public ModelAndView update(@PathVariable("id") Integer id, @Valid Client client, BindingResult bindingResult)
   {
      client.setId(id);
      if (bindingResult.hasErrors())
      {
         return new ModelAndView("client/form-update");
      }
      clientDao.update(client);
      return new ModelAndView("redirect:/client");
   }
}
