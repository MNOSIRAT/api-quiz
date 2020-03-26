package io.mohammad.apiquiz.module.cms.dtos;

import io.mohammad.apiquiz.module.cms.entities.Client;

public class ClientViewDto {

    public Long id;

    public String name;

    public String lastName;

    public String mobile;

    public ClientViewDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.mobile = client.getMobile();
    }
}
