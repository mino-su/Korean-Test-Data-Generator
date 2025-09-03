package org.delivery.koreatestdata.dto;

import org.delivery.koreatestdata.domain.MockData;
import org.delivery.koreatestdata.domain.constant.MockDataType;

public record MockDateDto(
        Long id,
        MockDataType mockDataType,
        String MockDataValue
) {
    public static MockDateDto fromEntity(MockData mockData) {
        return new MockDateDto(
                mockData.getId(),
                mockData.getMockDataType(),
                mockData.getMockDataValue());
    }
}
