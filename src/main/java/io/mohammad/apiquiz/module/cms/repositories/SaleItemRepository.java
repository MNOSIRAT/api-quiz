package io.mohammad.apiquiz.module.cms.repositories;

import io.mohammad.apiquiz.module.cms.entities.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
