package demo.fls.eshop.orders;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<ProductOrder, UUID>  {
    Collection<ProductOrder> findByProfileId(UUID profileId);
}