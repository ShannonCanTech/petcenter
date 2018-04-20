package com.example.petcenter;

import com.example.petcenter.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    AnimalRepository animalRepo;

    @RequestMapping("/")
    public String showIndex(Model model)
    {
        model.addAttribute("animals", animalRepo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addAnimal(Model model)
    {
        model.addAttribute("anAnimal", new Animal());
        return "addanimal";
    }

    @PostMapping("/saveanimal")
    public String saveAnimal(@Valid @ModelAttribute("animal") Animal animal, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "addanimal";
        }
        animalRepo.save(animal);
        return "redirect:/";
    }

    @RequestMapping("/changestatus/{id}")
    public String lostFound(@PathVariable("id") long id)
    {
        Animal thisAnimal = animalRepo.findById(id).get();

//        If elseif???
        thisAnimal.setLost(!thisAnimal.isLost());
        thisAnimal.setFound(!thisAnimal.isFound());
        animalRepo.save(thisAnimal);
        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String updateAnimal(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("anAnimal", animalRepo.findById(id));
        return "addanimal";
    }
}
