package sfu.rkis6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sfu.rkis6.model.Roles;
import sfu.rkis6.model.Users;
import sfu.rkis6.repositories.RolesRepository;
import sfu.rkis6.repositories.UsersRepository;

import java.util.*;

@Service
public class UsersService {
    private final UsersRepository userRepository;
    private final RolesRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository userRepository, RolesRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users findUserById(Long id) {
        Optional<Users> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new Users());
    }

    public Users findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    public List<Users> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(Users user) {
        Users userFromDB = userRepository.findUserByLogin(user.getLogin());
        if (userFromDB != null) {
            return false;
        }
        Set<Roles> roleSet = new HashSet<>();
        Roles userRole = user.getLogin().equals("admin") ? roleRepository.getRoleByName("ROLE_ADMIN") :
                roleRepository.getRoleByName("ROLE_USER");
        roleSet.add(userRole);
        user.setRoles(roleSet);
        user.setPasswordEncoded(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public boolean loginUser(String login, String password) {
        Users user = userRepository.findUserByLogin(login);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

}
