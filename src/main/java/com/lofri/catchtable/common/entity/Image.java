package com.lofri.catchtable.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Image {

    @Id
    private Long id;

    @Column
    private String src;

    @Column
    private LocalDateTime createdAt;
}
