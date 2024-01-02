package sfu.rkis7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sfu.rkis7.model.Watch;

import java.util.List;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Integer> {
    List<Watch> findAllByOrderById();
    List<Watch> findWatchByPriceGreaterThan(double price);
}
