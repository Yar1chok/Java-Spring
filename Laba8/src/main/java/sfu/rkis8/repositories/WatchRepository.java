package sfu.rkis8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sfu.rkis8.models.Watch;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Integer> {
    List<Watch> findWatchByPriceGreaterThanAndPurchasedIsFalse(double price);
    List<Watch> findAllByPurchasedIsFalseOrderById();
    Optional<Watch> findByIdAndPurchasedIsFalse(Integer id);
    Watch findFirstByOrderByIdDesc();
}
