package com.lofri.catchtable.domain.user.entity;

import com.lofri.catchtable.common.code.GenderType;
import com.lofri.catchtable.common.entity.BaseEntity;
import com.lofri.catchtable.common.entity.Image;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column
    private String contact;

    @Column
    private Boolean contactVerified;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Builder
    private User(String email, String password, String nickname, GenderType gender, String contact) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.contact = contact;
        this.nickname = nickname;
        this.contactVerified = false;
    }
}
