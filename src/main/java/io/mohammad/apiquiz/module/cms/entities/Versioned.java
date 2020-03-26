package io.mohammad.apiquiz.module.cms.entities;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class Versioned  {
    @Version
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
