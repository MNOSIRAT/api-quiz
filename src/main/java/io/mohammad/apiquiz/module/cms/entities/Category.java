package io.mohammad.apiquiz.module.cms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

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

    private String name;



}
