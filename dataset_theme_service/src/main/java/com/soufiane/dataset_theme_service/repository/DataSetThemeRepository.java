package com.soufiane.dataset_theme_service.repository;

import com.soufiane.dataset_theme_service.entity.DataSetTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface DataSetThemeRepository extends JpaRepository<DataSetTheme, Long> {
    DataSetTheme findByNameAndDeletedFalse(String name);
    DataSetTheme findByUuidAndDeletedFalse(UUID uuid);
    long countByDeletedFalse();
    List<DataSetTheme> findByDeletedFalse();
}