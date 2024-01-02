package sfu.rkis6.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sfu.rkis6.model.Users;
import sfu.rkis6.model.Watch;
import sfu.rkis6.repositories.WatchRepository;
import sfu.rkis6.service.UsersService;

import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {
    private final WatchRepository watchRepository;

    @Autowired
    public MainController(WatchRepository glassesRepository, UsersService userService) {
        this.watchRepository = glassesRepository;
        this.userService = userService;
    }

    private final UsersService userService;

    @GetMapping("/user/profile")
    public String profileGet(Model model, Principal principal){
        String username = principal.getName();
        Users user = userService.findUserByLogin(username);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/user/login")
    public String loginGet(Model model){
        model.addAttribute("user", new Users());
        return "login";
    }


    @PostMapping("/user/login")
    public String loginPost(@ModelAttribute("user") @Validated Users user,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "login";
        }
        if (!userService.loginUser(user.getLogin(), user.getPassword())){
            return "login";
        }

        return "redirect:/user/profile";
    }

    @GetMapping("/user/registration")
    public String registrationGet(Model model){
        model.addAttribute("user", new Users());
        return "registration";
    }

    @PostMapping("/user/registration")
    public String registrationPost(@ModelAttribute("user") @Validated Users user,
                                   BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userService.saveUser(user)){
            model.addAttribute("errorMessage",
                    "Пользователь с таким логином уже существует");
            return "registration";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/")
    public String showMenu(Authentication authentication, Model model) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if ("ROLE_ADMIN".equals(role)) {
                model.addAttribute("role", role);
                break;
            } else if ("ROLE_USER".equals(role)) {
                model.addAttribute("role", role);
            }
        }
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
    public String findByDioptersGet() {
        return "search";
    }

    @PostMapping("/search")
    public String findByDioptersPost(@RequestParam(value = "inputPrice")
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
