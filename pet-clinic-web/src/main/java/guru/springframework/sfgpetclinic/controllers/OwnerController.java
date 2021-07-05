package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

//only accessible directory is /owners
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public static final String OWNER_FORM_TEMPLATE = "owners/createOrUpdateOwnerForm";

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //data binder handles binding form objects to java objects
    @InitBinder
    public void setAllowedFields(DataBinder dataBinder) {
        //dont allow web forms to address id property
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping({"", "/", "/index.html", "/index"})
    public String processFindForm(Owner owner, BindingResult result, Model model) {

        if(owner.getLastName() == null) {
            owner.setLastName("");
        }

        //percentages for enabling like-searching
        //in SQL it works as wildcard character
        List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");

        if(results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            //get the only owner from the list
            owner = results.get(0);
            //redirect to this owner's page
            return "redirect:/owners/" + owner.getId();
        } else {
            //multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        Owner owner = new Owner();
        model.addAttribute("owner", owner);
        return OWNER_FORM_TEMPLATE;
    }

    //binding result holds result of the form validation and binding
    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if(result.hasErrors()) {
            return OWNER_FORM_TEMPLATE;
        } else {
            ownerService.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId);
        model.addAttribute("owner", owner);
        return OWNER_FORM_TEMPLATE;
    }

    @PostMapping("/{ownerId}/edit")
    //no need to declare PathVariable's name as long as variable name is the same
    public String processUpdateOwnerForm(@PathVariable Long ownerId,
                                        @Valid Owner owner, BindingResult result) {
        if(result.hasErrors()) {
            return OWNER_FORM_TEMPLATE;
        } else {
            owner.setId(ownerId);
            ownerService.save(owner);
            return "redirect:/owners/"+ownerId;
        }
    }


}
