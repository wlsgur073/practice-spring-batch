package com.example.batch.repository;

import com.example.batch.config.JpaConfigTest;
import com.example.batch.domain.PackageEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Package Repository Test")
@DataJpaTest
@Import(JpaConfigTest.class)
public class PackageRepositoryTest {

    @PersistenceContext
    private EntityManager em;


    @Autowired private PackageRepository packageRepository;

    @Test
    @DisplayName("simple packe entity save test")
    void test_save() {
        //given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("test package name");
        packageEntity.setPeriod(80);

        //when
        packageRepository.save(packageEntity);

        //then
        assertNotNull(packageEntity.getId());
    }

    @Test
    @DisplayName("Returns the latest package entity created after a specified time.")
    void test_findByCreatedAtAfter() {
        //given
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(1);

        PackageEntity packageEntity0 = new PackageEntity();
        packageEntity0.setPackageName("3 months for students only");
        packageEntity0.setPeriod(90);
        packageRepository.save(packageEntity0);

        PackageEntity packageEntity1 = new PackageEntity();
        packageEntity1.setPackageName("6 months for students only");
        packageEntity1.setPeriod(180);
        packageRepository.save(packageEntity1);

        //when
        final List<PackageEntity> packageEntities = packageRepository.findByCreatedAtAfter(
                dateTime, PageRequest.of(0, 1, Sort.by("id").descending())
        );

        //then
        then(packageEntities).isNotNull().hasSize(1);
        then(packageEntities.get(0).getId()).isEqualTo(packageEntity1.getId());
    }

    @Test
    @DisplayName("Updates package entity's count and period")
    void test_updateCountAndPeriod() {
        //given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("A event 3 months");
        packageRepository.save(packageEntity);

        //when
        int updatedCount = packageRepository.updatePackageEntityByCountAndPeriod(packageEntity.getId(), 30, 90);
        em.flush();
        em.clear();
        final PackageEntity updatedPackageEntity = packageRepository.findById(packageEntity.getId()).orElseThrow();

        //then
        then(updatedCount).isEqualTo(1);
        then(updatedPackageEntity.getCount()).isEqualTo(30);
        then(updatedPackageEntity.getPeriod()).isEqualTo(90);
    }

    @Test
    @DisplayName("delete package entity")
    void test_delete() {
        //given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("remove package entity");
        packageEntity.setCount(1);
        PackageEntity saveEntity = packageRepository.save(packageEntity);

        //when
        packageRepository.deleteById(saveEntity.getId());

        //then
        then(packageRepository.findById(saveEntity.getId())).isEmpty();
    }
}