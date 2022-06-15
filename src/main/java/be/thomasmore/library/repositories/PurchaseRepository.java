package be.thomasmore.library.repositories;

import be.thomasmore.library.model.Purchase;
import be.thomasmore.library.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    List<Purchase> findByUser(User user);

    @Query("SELECT p FROM Purchase p WHERE p.user = ?1 AND p.isFinalised = false")
    Optional<Purchase> findByUserAndNotFinalised(User user);

    @Query("SELECT p FROM Purchase p WHERE p.user = ?1 AND p.isFinalised = true")
    List<Purchase> findByUserAndIsFinalised(User user);
}
