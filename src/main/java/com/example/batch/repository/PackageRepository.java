package com.example.batch.repository;

import com.example.batch.domain.PackageEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PackageRepository extends JpaRepository<PackageEntity, Long> {
    List<PackageEntity> findByCreatedAtAfter(LocalDateTime dateTime, Pageable pageable);

    // TODO: 테스트용으로 일시적 메서드 선언
    @Transactional
    @Modifying // 하.. 이 seki 쓰면 안된다. flush는 되지만 persistence context를 clear해주지 않아서 findById에서 mock 을 불러온다.
    @Query("update PackageEntity p " +
            " set p.count = :count," +
            "     p.period = :period" +
            " where p.id = :id")
    int updatePackageEntityByCountAndPeriod(Long id, Integer count, Integer period);
}
