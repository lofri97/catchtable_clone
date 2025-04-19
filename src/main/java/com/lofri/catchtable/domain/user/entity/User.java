package com.lofri.catchtable.domain.user.entity;

import com.lofri.catchtable.common.code.GenderType;
import com.lofri.catchtable.common.entity.BaseEntity;
import com.lofri.catchtable.common.entity.Image;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
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
    private String region;

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

    @Transient
    private Long followingCnt;

    @Transient
    private Long followerCnt;

    @Builder
    private User(String email, String password, String nickname, GenderType gender, String contact) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.contact = contact;
        this.nickname = nickname;
        this.contactVerified = false;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateRegion(String region) {
        this.region = region;
    }
  
    public User(Long id,
                   String email,
                   String nickname,
                   String description,
                   GenderType gender,
                   String contact,
                   Boolean contactVerified,
                   Image image,
                   Long followingCnt,
                   Long followerCnt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.description = description;
        this.gender = gender;
        this.contact = contact;
        this.contactVerified = contactVerified;
        this.image = image;
        this.followingCnt = followingCnt;
        this.followerCnt = followerCnt;
    }
}
