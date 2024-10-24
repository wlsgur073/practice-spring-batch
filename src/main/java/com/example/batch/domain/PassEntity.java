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
@Table(name = "pass")
public class PassEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pass_seq")
    private Integer id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "package_seq", nullable = false)
    private PackageEntity packageSeq;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @Setter @Column(name = "status", nullable = false, length = 10)
    private String status;

    @Column(name = "remaining_count")
    private Integer remainingCount;

    @Setter @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Setter @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Setter @Column(name = "expired_at")
    private LocalDateTime expiredAt;

}