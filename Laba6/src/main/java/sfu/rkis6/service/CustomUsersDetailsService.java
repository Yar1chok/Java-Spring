package sfu.rkis6.service;

import sfu.rkis6.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUsersDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Autowired
    public CustomUsersDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        sfu.rkis6.model.Users user = usersRepository.findUserByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (user.getLogin().equals("admin")){
            return User.builder()
                    .username(user.getLogin())
                    .password(user.getPassword())
                    .roles("ADMIN")
                    .build();
        }
        return User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
