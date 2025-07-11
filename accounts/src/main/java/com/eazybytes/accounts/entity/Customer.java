package com.eazybytes.accounts.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="customer")
@Data
public class Customer extends BaseEntity {

    @Id
    @Column(name="customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;

    private String email;

    @Column(name="mobile_number")
    private String mobileNo;

}
