package io.mohammad.apiquiz.module.cms.repositories;


import io.mohammad.apiquiz.module.cms.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("select s from Sale s " +
            " join fetch s.client " +
            " left join fetch s.items i " +
            " left join fetch i.product " +
            " where s.id=?1 ")
    Optional<Sale> findByIdFetchItemsAndClient(Long id);

}
