package org.delivery.koreatestdata.repository;

import org.delivery.koreatestdata.domain.MockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockDataRepository extends JpaRepository<MockData,Long> {

}
