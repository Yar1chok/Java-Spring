package sfu.rkis6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sfu.rkis6.model.Watch;

import java.util.List;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Integer> {
    List<Watch> findAllByOrderById();
    List<Watch> findWatchByPriceGreaterThan(double price);
}
