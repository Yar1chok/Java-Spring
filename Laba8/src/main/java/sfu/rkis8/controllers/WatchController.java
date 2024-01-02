package sfu.rkis8.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sfu.rkis8.jms.JmsMessageSender;
import sfu.rkis8.models.Watch;
import sfu.rkis8.repositories.WatchRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class WatchController {
    private final WatchRepository watchRepository;
    private final JmsMessageSender jmsMessageSender;

    @Autowired
    public WatchController(WatchRepository watchRepository, JmsMessageSender jmsMessageSender) {
        this.watchRepository = watchRepository;
        this.jmsMessageSender = jmsMessageSender;
    }

    @GetMapping("/")
    public String showMenu() {
        return "index";
    }

    @GetMapping("/show")
    public String getAllWatches(Model model) {
        model.addAttribute("watchList", watchRepository.findAllByPurchasedIsFalseOrderById());
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
        jmsMessageSender.sendNotification("An operation has been performed to add an element, its id: " +
                watchRepository.findFirstByOrderByIdDesc().getId());
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteElementGet(Model model) {
        model.addAttribute("watchList", watchRepository.findAllByPurchasedIsFalseOrderById());
        model.addAttribute("func", Arrays.asList("delete", "удаление"));
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteElementPost(@RequestParam(value = "delete")
                                    @NotBlank @Pattern(regexp = "\\d+") String deleteId,
                                    Model model) {
        try {
            Optional<Watch> watch = watchRepository.findByIdAndPurchasedIsFalse(Integer.parseInt(deleteId));
            if (watch.isEmpty()){
                throw new IndexOutOfBoundsException();
            } else {
                jmsMessageSender.sendNotification("The operation of deleting the c id element has been performed: "
                        + watch.get().getId());
                watch.ifPresent(watchRepository::delete);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception){
            model.addAttribute("errorMessage", "Введите корректный id");
            model.addAttribute("watchList",  watchRepository.findAllByPurchasedIsFalseOrderById());
            model.addAttribute("func", Arrays.asList("delete", "удаление"));
            return "delete";
        }
        return "redirect:/";
    }

    @GetMapping("/inputId")
    public String inputEditGet(Model model) {
        model.addAttribute("watchList",  watchRepository.findAllByPurchasedIsFalseOrderById());
        model.addAttribute("func", Arrays.asList("inputId", "изменения"));
        return "inputId";
    }

    @PostMapping("/inputId")
    public String inputEditPost(Model model, @RequestParam(value = "inputId")
    @NotBlank @Pattern(regexp = "\\d+") String editId) {
        try {
            Optional<Watch> watch = watchRepository.findByIdAndPurchasedIsFalse(Integer.parseInt(editId));
            if (watch.isEmpty()){
                throw new IndexOutOfBoundsException();
            } else {
                model.addAttribute("watch",  watch.get());
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception){
            model.addAttribute("errorMessage", "Введите корректный id");
            model.addAttribute("watchList",  watchRepository.findAllByPurchasedIsFalseOrderById());
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
        jmsMessageSender.sendNotification("An operation has been performed to edit the c id element: " + watch.getId());
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
            model.addAttribute("watchList",  watchRepository.findAllByPurchasedIsFalseOrderById());
            return "search";
        }
        model.addAttribute("watchList",
                watchRepository.findWatchByPriceGreaterThanAndPurchasedIsFalse(price));
        return "show";
    }

    @GetMapping("/inputFind")
    public String inputFindGet(Model model) {
        model.addAttribute("watchList",  watchRepository.findAllByPurchasedIsFalseOrderById());
        model.addAttribute("func", Arrays.asList("inputFind", "вывода"));
        model.addAttribute("tableEnable", false);
        return "inputId";
    }

    @PostMapping("/inputFind")
    public String inputFindPost(Model model, @RequestParam(value = "inputId")
    @NotBlank @Pattern(regexp = "\\d+") String inputId) {
        try {
            Optional<Watch> watch = watchRepository.findByIdAndPurchasedIsFalse(Integer.parseInt(inputId));
            if (watch.isEmpty()){
                throw new IndexOutOfBoundsException();
            } else {
                List<Watch> glassesList = new ArrayList<>();
                glassesList.add(watch.get());
                model.addAttribute("watchList", glassesList);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception){
            model.addAttribute("errorMessage", "Введите корректный id");
            model.addAttribute("glassesList",  watchRepository.findAllByPurchasedIsFalseOrderById());
            model.addAttribute("func", Arrays.asList("inputFind", "вывода"));
            model.addAttribute("tableEnable", false);
            return "inputId";
        }
        return "show";
    }

    @PostMapping("/buy/{id}")
    public String buyPost(@PathVariable String id) {
        try {
            Optional<Watch> watch = watchRepository.findByIdAndPurchasedIsFalse(Integer.parseInt(id));
            if (watch.isEmpty()){
                throw new IndexOutOfBoundsException();
            } else {
                Watch watchToDB = watch.get();
                watchToDB.setPurchased(true);
                watchRepository.save(watchToDB);
                jmsMessageSender.sendNotification("The purchase operation of an item with an id has been completed: "
                        + watchToDB.getId());
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception){
            return "redirect:/";
        }
        return "redirect:/";
    }
}