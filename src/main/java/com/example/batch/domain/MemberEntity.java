package com.example.batch.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;

@Getter
@Entity
@Table(name = "member")
public class MemberEntity extends BaseEntity {
    @Id
    @Column(name = "member_id", length = 20)
    private String memberId;

    @Setter @Column(name = "member_name", nullable = false, length = 50)
    private String memberName;

    @Setter @Column(name = "status", nullable = false, length = 10)
    private String status;

    @Setter @Column(name = "phone", length = 50)
    private String phone;

    @Setter @Column(name = "meta")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> meta;

    @OneToMany(mappedBy = "member")
    private List<BookingEntity> bookingEntities = new ArrayList<>(); // 한 회원이 여러 개의 예약 가능함으로 중복 허용해도 상관 없음

    @OneToMany(mappedBy = "member")
    private Set<PassEntity> passes = new LinkedHashSet<>(); // pass에는 사용자 ID와 이용건 순번이 있어 중복 허용하면 안됨

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "memberId = " + getMemberId() + ", " +
                "createdAt = " + getCreatedAt() + ", " +
                "modifiedAt = " + getModifiedAt() + ", " +
                "memberName = " + getMemberName() + ", " +
                "status = " + getStatus() + ", " +
                "phone = " + getPhone() + ", " +
                "meta = " + getMeta() + ")";
    }
}