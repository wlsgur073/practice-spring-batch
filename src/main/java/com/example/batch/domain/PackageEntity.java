package com.example.batch.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "package")
public class PackageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_seq")
    private Long id;

    @Setter @Column(name = "package_name", nullable = false, length = 50)
    private String packageName;

    @Setter @Column(name = "count")
    private Integer count;

    @Setter @Column(name = "period")
    private Integer period;

    @OneToMany(mappedBy = "packageSeq")
    private Set<PassEntity> passes = new LinkedHashSet<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "createdAt = " + getCreatedAt() + ", " +
                "modifiedAt = " + getModifiedAt() + ", " +
                "packageName = " + packageName + ", " +
                "count = " + count + ", " +
                "period = " + period + ")";
    }
}