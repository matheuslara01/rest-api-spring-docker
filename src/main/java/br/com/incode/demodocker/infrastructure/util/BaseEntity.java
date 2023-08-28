package br.com.incode.demodocker.infrastructure.util;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity {

    @Column(name = "DATE_CREATED")
    protected LocalDateTime dateCreated;

    @Column(name = "DATE_UPDATED")
    protected LocalDateTime dateUpdated;

    @PrePersist
    private void prePersist() {
        this.dateCreated = LocalDateTime.now();
        this.dateUpdated = LocalDateTime.now();
    }
}
