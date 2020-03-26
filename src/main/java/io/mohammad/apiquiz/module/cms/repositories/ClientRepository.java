package io.mohammad.apiquiz.module.cms.repositories;

import io.mohammad.apiquiz.module.cms.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}
