package io.mohammad.apiquiz.module.cms.repositories;


import io.mohammad.apiquiz.module.cms.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
//    @Query("select s from Sale s " +
//            " join fetch s.client " +
//            " join fetch s.items i " +
//            " join fetch i.product " +
//            " where s.id=?1 ")
    @Query("select s from Sale s " +
            " left join fetch s.items  " +
            " join fetch s.client " +
            " where s.id=?1 ")
    Optional<Sale> findByIdFetchItemsAndClient(Long id);

}
