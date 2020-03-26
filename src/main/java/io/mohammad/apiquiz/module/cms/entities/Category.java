package io.mohammad.apiquiz.module.cms.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */

/**
 * Category of the product
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Category  extends Versioned {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;



}
