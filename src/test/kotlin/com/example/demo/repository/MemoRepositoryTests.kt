package com.example.demo.repository

import com.example.demo.entity.Memo
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@DataJpaTest
class MemoRepositoryTests {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var sut: MemoRepository

    @Test
    @Sql(statements = ["INSERT INTO memo (id, title, description, done, updated) VALUES (1, 'memo test', 'memo description', FALSE, CURRENT_TIMESTAMP)"])
    fun findById() {
        val expected = entityManager.find(Memo::class.java, 1L)
        val actual = sut.findById(expected.id).orElseGet { null }
        assertThat(actual).isNotNull()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun save() {
        val updated = LocalDateTime.of(2018, 2, 19, 18, 12, 0)
        val expected = Memo(title = "test title", description = "test description", done = true, updated = updated)
        sut.saveAndFlush(expected)
        val actual = entityManager.find(Memo::class.java, expected.id)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    @Sql(statements = ["INSERT INTO memo (id, title, description, done, updated) VALUES (1, 'memo test', 'memo description', FALSE, CURRENT_TIMESTAMP)"])
    fun delete() {
        val expected = entityManager.find(Memo::class.java, 1L)
        sut.deleteById(expected.id)
        sut.flush()
        val actual = entityManager.find(Memo::class.java, expected.id)
        assertThat(actual).`as`("actualは削除されている").isNull()
    }

}