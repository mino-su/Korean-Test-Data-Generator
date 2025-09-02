package org.delivery.koreatestdata.domain;

import jakarta.persistence.*;
import lombok.*;
import org.delivery.koreatestdata.domain.constant.MockDataType;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 특정 TableSchema 의 단위 필드 정보
 * 이 필드들이 모여서 테이블 스키마를 구성한다.
 */
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class SchemaField extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false) // 왼쪽이 나 오른쪽이 join할 table
    private TableSchema tableSchema;

    @Setter
    @Column(nullable = false)
    private String fieldName;
    @Setter
    @Column(nullable = false)
    private MockDataType mockDataType;
    @Setter
    @Column(nullable = false)
    private Integer fieldOrder;
    @Setter
    @Column(nullable = false)
    private Integer blankPercent;

    @Setter private String typeOptionJson; // {"min", "max"}
    @Setter private String forceValue;


    public static SchemaField of(TableSchema tableSchema, String fieldName, MockDataType mockDataType, Integer fieldOrder, Integer blankPercent, String typeOptionJson, String forceValue) {
        return new SchemaField(tableSchema, fieldName, mockDataType, fieldOrder, blankPercent, typeOptionJson, forceValue);
    }

    public SchemaField(TableSchema tableSchema, String fieldName, MockDataType mockDataType, Integer fieldOrder, Integer blankPercent,  String typeOptionJson,String forceValue) {
        this.tableSchema = tableSchema;
        this.fieldName = fieldName;
        this.mockDataType = mockDataType;
        this.fieldOrder = fieldOrder;
        this.typeOptionJson = typeOptionJson;
        this.blankPercent = blankPercent;
        this.forceValue = forceValue;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SchemaField that)) return false;
        if(getId() == null){
            return Objects.equals(getFieldName(), that.getFieldName()) &&
                    getMockDataType() == that.getMockDataType() &&
                    Objects.equals(getFieldOrder(), that.getFieldOrder()) &&
                    Objects.equals(getBlankPercent(), that.getBlankPercent()) &&
                    Objects.equals(getTypeOptionJson(), that.getTypeOptionJson()) &&
                    Objects.equals(getForceValue(), that.getForceValue());

        }        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFieldName(), getMockDataType(), getFieldOrder(), getBlankPercent(), getTypeOptionJson(), getForceValue());
    }
}
