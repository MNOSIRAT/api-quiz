package io.mohammad.apiquiz.module.cms.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Product extends Versioned {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
    /**
     * Date of creation
     */
    @Column(nullable = false)
    private Instant creationDate;
    /**
     * Category of the product
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Category category;


}
