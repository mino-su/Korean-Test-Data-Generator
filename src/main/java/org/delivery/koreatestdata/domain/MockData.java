package org.delivery.koreatestdata.domain;

import jakarta.persistence.*;
import lombok.*;
import org.delivery.koreatestdata.domain.constant.MockDataType;

import java.util.Objects;


/**
 * 특정 가짜 데이터 자료형에 대응하는 가짜 데이터
 * 알고리즘으로 생성하지 않는 {@link MockDataType}의 경우, 이 가짜 데이터를 랜덤으로 뽑아 출력한다.
 *
 * @author mino-su
 */

@Getter
@ToString
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"mockDataType", "mockDataValue"})})
@NoArgsConstructor
public class MockData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MockDataType mockDataType;

    @Setter
    @Column(nullable = false, length = 1000)
    private String mockDataValue;

    public MockData(MockDataType mockDataType, String mockDataValue) {
        this.mockDataType = mockDataType;
        this.mockDataValue = mockDataValue;
    }

    public static MockData of(MockDataType mockDataType, String mockDataValue) {
        return new MockData(mockDataType, mockDataValue);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof MockData mockData)) return false;

        if (getId() == null) {
            return Objects.equals(this.getMockDataType(), mockData.getMockDataValue())
                    && Objects.equals(this.getMockDataValue(), mockData.getMockDataValue());
        }
        return Objects.equals(this.getId(), mockData.getId());
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return Objects.hash(getMockDataType(), getMockDataValue());
        }
        return Objects.hash(getId(), getMockDataType(), getMockDataValue());
    }
}
