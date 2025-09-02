package org.delivery.koreatestdata.repository;

import org.delivery.koreatestdata.domain.TableSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableSchemaRepository extends JpaRepository<TableSchema, Long> {
}
