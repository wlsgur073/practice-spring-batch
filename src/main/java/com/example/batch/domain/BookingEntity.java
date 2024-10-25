package com.example.batch.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "booking")
public class BookingEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_seq")
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pass_seq", nullable = false)
    private PassEntity passSeq;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @Setter @Column(name = "status", nullable = false, length = 10)
    private String status;

    @Column(name = "used_pass", nullable = false)
    private Boolean usedPass = false;

    @Setter @Column(name = "attended", nullable = false)
    private Boolean attended = false;

    @Setter @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Setter @Column(name = "ended_at", nullable = false)
    private LocalDateTime endedAt;

    @Setter @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

}