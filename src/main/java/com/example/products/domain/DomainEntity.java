package com.example.products.domain;

import com.example.products.domain.jackson.DomainEntityViews;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.CreationTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class DomainEntity<T> implements DomainObjectConsistentable {

    private static final Logger log = LoggerFactory.getLogger(DomainEntity.class);

    @JsonView(DomainEntityViews.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @JsonView(DomainEntityViews.CreatedAt.class)
    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @Deprecated
    protected DomainEntity() {
        /* Required by Hibernate */
    }
}
