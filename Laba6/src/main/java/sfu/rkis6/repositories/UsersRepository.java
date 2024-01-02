package sfu.rkis6.repositories;

import org.springframework.stereotype.Repository;
import sfu.rkis6.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findUserByLogin(String login);
}
