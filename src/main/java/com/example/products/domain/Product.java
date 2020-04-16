package com.example.products.domain;

import com.example.products.common.util.BigDecimalUtils;
import com.example.products.common.util.ExceptionUtils;
import com.example.products.domain.jackson.ProductViews;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table
public class Product extends DomainEntity<Long> {

    private static final Logger log = LoggerFactory.getLogger(Product.class);

    private String name;
    private BigDecimal price;
    private Status status = Status.CREATED;

    @Deprecated
    Product() {
        /* Required by Hibernate */
    }

    Product(final String name, final BigDecimal price) {
        ExceptionUtils.notNull(name, "ERR_20200416162959283", log);
        ExceptionUtils.notNull(price, "ERR_20200416162959284", log);
        if (BigDecimalUtils.isLessThanZero(price)) {
            ExceptionUtils.throwIllegalArgument("ERR_20200416162959285", log);
        }

        this.name = name;
        this.price = price;
    }

    public void updateProductInfo(final String name, final BigDecimal price) {
        if (!isUpdatable()) {
            ExceptionUtils.throwIllegalState("ERR_20200416222605651", log);
        }

        ExceptionUtils.notNull(name, "ERR_20200416222605652", log);
        ExceptionUtils.notNull(price, "ERR_20200416222605653", log);
        if (BigDecimalUtils.isLessThanZero(price)) {
            ExceptionUtils.throwIllegalArgument("ERR_20200416222605654", log);
        }

        this.name = name;
        this.price = price;
        this.status = Status.UPDATED;
    }

    @Transient
    private boolean isUpdatable() {
        return status != Status.DELETED;
    }

    @JsonView(ProductViews.Name.class)
    @Column(nullable = false)
    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @JsonView(ProductViews.Price.class)
    @Column(nullable = false)
    private BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        this.price = price;
    }

    @JsonIgnore
    private Status getStatus() {
        return status;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    static enum Status {
        CREATED, UPDATED, DELETED
    }

}
