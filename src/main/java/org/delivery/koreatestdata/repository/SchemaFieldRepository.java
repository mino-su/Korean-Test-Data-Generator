package org.delivery.koreatestdata.repository;

import org.delivery.koreatestdata.domain.SchemaField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemaFieldRepository extends JpaRepository<SchemaField, Long> {
}
