package org.delivery.koreatestdata.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ObjectAssert;
import org.delivery.koreatestdata.domain.MockData;
import org.delivery.koreatestdata.domain.SchemaField;
import org.delivery.koreatestdata.domain.TableSchema;
import org.delivery.koreatestdata.domain.constant.MockDataType;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("[Repository] JPA 테스트")
@DataJpaTest
public class JpaRepositoryTest {

    private static final String TEST_AUDITOR = "test_uno";
    @Autowired
    private MockDataRepository mockDataRepository;

    @Autowired
    private SchemaFieldRepository schemaFieldRepository;
    @Autowired
    private TableSchemaRepository tableSchemaRepository;

    @Autowired
    private TestEntityManager entityManager;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void selectTest() {
        //given

        //when
        List<MockData> mockDataList = mockDataRepository.findAll();
        List<SchemaField> schemaFields = schemaFieldRepository.findAll();
        List<TableSchema> tableSchemas = tableSchemaRepository.findAll();

        //then
        assertThat(mockDataList).hasSize(231);
        assertThat(schemaFields)
                .hasSize(4).first()
                .extracting("tableSchema")
                .isEqualTo(tableSchemas.getFirst());

        assertThat(tableSchemas)
                .hasSize(1)
                .first()
                .hasFieldOrPropertyWithValue("schemaName", "test_schema1")
                .hasFieldOrPropertyWithValue("userId", "djkeh")
                .extracting("schemaFields", InstanceOfAssertFactories.COLLECTION)
                .hasSize(4);
    }

    @Test
    void insertTest() {
        //given
        TableSchema tableSchema = TableSchema.of("test_schema", "uno");
        tableSchema.addSchemaFields(
                List.of(
                        SchemaField.of("id", MockDataType.ROW_NUMBER, 1, 0, null, null),
                        SchemaField.of("age", MockDataType.NUMBER, 2, 10, null, null),
                        SchemaField.of("name", MockDataType.NAME, 3, 20, null, null)
                )
        );
        //when

        TableSchema saved = tableSchemaRepository.save(tableSchema);

        //then
        entityManager.clear(); // 영속성 컨텍스트 초기화 (캐시 초기화)

        TableSchema newTableSchema = tableSchemaRepository.findById(saved.getId()).orElseThrow();
        assertThat(newTableSchema)
                .hasFieldOrPropertyWithValue("schemaName", "test_schema")
                .hasFieldOrPropertyWithValue("userId", "uno")
                .hasFieldOrPropertyWithValue("createdBy", TEST_AUDITOR)
                .hasFieldOrPropertyWithValue("modifiedBy", TEST_AUDITOR)
                .hasFieldOrProperty("createdAt")
                .hasFieldOrProperty("modifiedAt")
                .extracting("schemaFields", InstanceOfAssertFactories.COLLECTION)
                .hasSize(3)
                .extracting("fieldOrder", Integer.class)
                .containsExactly(1, 2, 3);

        assertThat(newTableSchema.getCreatedAt()).isEqualTo(newTableSchema.getModifiedAt());
    }

    @Test
    void updateTest() throws JsonProcessingException {
        //given
        TableSchema tableSchema = tableSchemaRepository.findAll().getFirst();
        tableSchema.setSchemaName("test_modified");
        tableSchema.clearSchemaFields();
        tableSchema.addSchemaField(
                SchemaField.of("age", MockDataType.ROW_NUMBER, 3, 0,
                        json(Map.of("min", 1, "max", 30)), null)
        );

        //when
        TableSchema updated = tableSchemaRepository.saveAndFlush(tableSchema);

        //then
        assertThat(updated)
                .hasFieldOrPropertyWithValue("schemaName", "test_modified")
                .hasFieldOrPropertyWithValue("createdBy", "uno")
                .hasFieldOrPropertyWithValue("modifiedBy", TEST_AUDITOR)
                .extracting("schemaFields", InstanceOfAssertFactories.COLLECTION)
                .hasSize(1);

        assertThat(updated.getCreatedAt().isBefore(updated.getModifiedAt()));

    }


    @Test
    void deleteTest() {
        //given
        TableSchema tableSchema = tableSchemaRepository.findAll().getFirst();

        //when
        tableSchemaRepository.delete(tableSchema);

        //then
        assertThat(tableSchemaRepository.count()).isEqualTo(0);
        assertThat(schemaFieldRepository.count()).isEqualTo(0);
    }

    @Test
    void insertUKConstraintTest() {
        //given
        MockData mockData1 = MockData.of(MockDataType.CAR, "벤츠");
        MockData mockData2 = MockData.of(MockDataType.CAR, "벤츠");


        //when
        Throwable t = catchThrowable(
                () -> mockDataRepository.saveAll(List.of(mockData1, mockData2))
        );

        // then
        assertThat(t).isInstanceOf(DataIntegrityViolationException.class)
                .hasCauseInstanceOf(ConstraintViolationException.class)
                .hasRootCauseInstanceOf(SQLIntegrityConstraintViolationException.class)
                .rootCause()
                .message()
                .contains("Unique index or primary key violation");

    }

    private String json(Object obj) throws JsonProcessingException {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 변환 실패", e);
        }
    }

    @EnableJpaAuditing
    @TestConfiguration
    static class TestJpaConfig {
        @Bean
        public AuditorAware<String> auditorAware() {
            return () -> Optional.of(TEST_AUDITOR);
        }

    }

}




