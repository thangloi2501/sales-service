package com.demo.sales.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Loi Nguyen
 *
 */
@Data
@MappedSuperclass
@AllArgsConstructor @NoArgsConstructor
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = -5505173567335846034L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;
}
