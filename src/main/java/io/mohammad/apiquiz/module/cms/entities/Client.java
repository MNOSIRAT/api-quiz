package io.mohammad.apiquiz.module.cms.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */
@Setter
@Getter
@Accessors(chain = true)
@Entity
public class Client  extends Versioned{

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String lastName;

    private String mobile;


}
