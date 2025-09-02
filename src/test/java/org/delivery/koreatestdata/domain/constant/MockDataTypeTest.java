package org.delivery.koreatestdata.domain.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[Domain] 테스트 데이터 자료형 테스트")
class MockDataTypeTest {
    @Test
    @DisplayName("자료형이 주어지면, 해당 원소의 이름을 리턴한다.")
    void givenMockDataType_WhenReading_thenReturnsEnumElementName() {
        // Given
        MockDataType mockDataType = MockDataType.STRING;

        // When
        String name = mockDataType.toString();

        // Then
        System.out.println("name = " + name);
        assertThat(name).isEqualTo(MockDataType.STRING.name());
    }


    @Test
    @DisplayName("자료형이 주어지면, 해당 원소의 데이터를 리턴한다")
    void givenMockDataType_whenReading_thenReturnsEnumElementObject() {
        // Given
        MockDataType mockDataType = MockDataType.STRING;

        // When
        MockDataType.MockDataTypeObject result = mockDataType.toObject();

        // Then
        assertThat(result.toString()).contains("name","requiredOptions","baseType");
    }
}