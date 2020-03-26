package io.mohammad.apiquiz.module.cms.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Sale  extends Versioned {

    @Id
    @GeneratedValue
    private Long id;

    private Instant creationDate;

    /**
     * the client who brought the Sale
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    private String seller;

    /**
     * List of items represents the Products in the Sale
     */
    @OneToMany(mappedBy = "sale" )
    private List<SaleItem> items = new ArrayList<>();



    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", client.id=" + client.getId() +
                ", seller='" + seller + '\'' +
                '}';
    }
}
