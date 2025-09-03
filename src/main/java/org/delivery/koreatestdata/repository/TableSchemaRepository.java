package org.delivery.koreatestdata.repository;

import org.delivery.koreatestdata.domain.TableSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableSchemaRepository extends JpaRepository<TableSchema, Long> {
    Page<TableSchema> findByUserId(String userId, Pageable pageable);

    Optional<TableSchema> findByUserIdAndSchemaName(String userId, String schemaName);

    void deleteByUserIdAndSchemaName(String userId, String schemaName);
}
