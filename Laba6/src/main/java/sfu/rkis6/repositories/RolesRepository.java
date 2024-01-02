package sfu.rkis6.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sfu.rkis6.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    @Transactional
    Roles getRoleById(Integer id);
    @Transactional
    Roles getRoleByName(String name);
}
