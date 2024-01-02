package sfu.rkis7.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sfu.rkis7.model.Watch;
import sfu.rkis7.repositories.WatchRepository;

import java.util.Arrays;
import java.util.Optional;

@Controller
public class WatchController {
    private final WatchRepository watchRepository;

    @Autowired
    public WatchController(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @GetMapping("/")
    public String showMenu() {
        return "index";
    }

    @GetMapping("/show")
    public String getAllWatches(Model model) {
        model.addAttribute("watchList", watchRepository.findAllByOrderById());
        return "show";
    }

    @GetMapping("/add")
    public String addElementGet(Model model) {
        model.addAttribute("watch", new Watch());
        return "add";
    }

    @PostMapping("/add")
    public String addElementPost(@Valid @ModelAttribute("watch") Watch watch,
                                 BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Введите корректные данные");
            return "add";
        }
        watchRepository.save(watch);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteElementGet(Model model) {
        model.addAttribute("watchList", watchRepository.findAllByOrderById());
        model.addAttribute("func", Arrays.asList("delete", "удаление"));
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteElementPost(@RequestParam(value = "delete")
                                    @NotBlank @Pattern(regexp = "\\d+") String deleteId,
                                    Model model) {
        try {
            Optional<Watch> watch = watchRepository.findById(Integer.parseInt(deleteId));
            if (watch.isEmpty()){
                throw new IndexOutOfBoundsException();
            } else {
                watch.ifPresent(watchRepository::delete);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception){
            model.addAttribute("errorMessage", "Введите корректный id");
            model.addAttribute("watchList",  watchRepository.findAllByOrderById());
            model.addAttribute("func", Arrays.asList("delete", "удаление"));
            return "delete";
        }
        return "redirect:/";
    }

    @GetMapping("/inputId")
    public String inputEditGet(Model model) {
        model.addAttribute("watchList",  watchRepository.findAllByOrderById());
        model.addAttribute("func", Arrays.asList("inputId", "изменения"));
        return "inputId";
    }

    @PostMapping("/inputId")
    public String inputEditPost(Model model, @RequestParam(value = "inputId")
    @NotBlank @Pattern(regexp = "\\d+") String editId) {
        try {
            Optional<Watch> watch = watchRepository.findById(Integer.parseInt(editId));
            if (watch.isEmpty()){
                throw new IndexOutOfBoundsException();
            } else {
                model.addAttribute("watch",  watch.get());
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception){
            model.addAttribute("errorMessage", "Введите корректный id");
            model.addAttribute("watchList",  watchRepository.findAllByOrderById());
            model.addAttribute("func", Arrays.asList("inputId", "изменения"));
            return "inputId";
        }
        return "edit";
    }

    @GetMapping("/edit")
    public String editElementGet(Model model, @ModelAttribute("watch") Watch watch) {
        model.addAttribute("watch",  watch);
        return "edit";
    }

    @PostMapping("/edit")
    public String editElementPost(Model model, @Valid @ModelAttribute("watch") Watch watch,
                                  BindingResult result){
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Введите корректные данные");
            model.addAttribute("watch", watch);
            return "edit";
        }
        watchRepository.save(watch);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String findByPriceGet() {
        return "search";
    }

    @PostMapping("/search")
    public String findByPricePost(@RequestParam(value = "inputPrice")
                                     @NotBlank @Pattern(regexp = "^-?\\d+(\\.\\d+)?$") String inputPrice,
                                     Model model) {
        double price;
        try {
            price = Double.parseDouble(inputPrice);
        } catch (NumberFormatException exception){
            model.addAttribute("errorMessage", "Введите корректный id");
            model.addAttribute("watchList",  watchRepository.findAllByOrderById());
            return "search";
        }
        model.addAttribute("watchList",
                watchRepository.findWatchByPriceGreaterThan(price));
        return "show";
    }
}