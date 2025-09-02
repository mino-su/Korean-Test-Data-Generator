package org.delivery.koreatestdata.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 단위 테이블 스키마 정보
 * 식별자 {@link #userId}로 특정 할 수 있는 회원이 소유한다
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
@ToString(callSuper = true)
@Entity
public class TableSchema extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String schemaName;
    @Setter
    private String userId;
    @Setter
    private LocalDateTime exportedAt;


    @ToString.Exclude
    @OneToMany(mappedBy = "tableSchema", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<SchemaField> schemaFields = new LinkedHashSet<>();

    public void addSchemaField(SchemaField schemaField) {
        this.schemaFields.add(schemaField);
        schemaField.setTableSchema(this);
    }

    public void addSchemaFields(Collection<SchemaField> schemaFields) {
        schemaFields.forEach(this::addSchemaField);
    }

    public boolean isExported() {
        return exportedAt != null;
    }

    public void markExported() {
        exportedAt = LocalDateTime.now();
    }


    public TableSchema(String schemaName, String userId) {
        this.schemaName = schemaName;
        this.userId = userId;
        this.exportedAt = null;
    }



    public static TableSchema of(String schemaName, String userId) {
        return new TableSchema(schemaName, userId);
    }
}
