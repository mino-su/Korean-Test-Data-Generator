package org.delivery.koreatestdata.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table
@AllArgsConstructor
public class TableSchema {
    private String schemaName;

    @Id
    @GeneratedValue()
    private String userId;

    private LocalDateTime exportedAt;
    private boolean isExported;

}
