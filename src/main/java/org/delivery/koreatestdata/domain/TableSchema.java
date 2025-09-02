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
@ToString
@AllArgsConstructor
public class TableSchema {
    private String schemaName;
    private String userId;

    private LocalDateTime exportedAt;
    private boolean isExported;

}
