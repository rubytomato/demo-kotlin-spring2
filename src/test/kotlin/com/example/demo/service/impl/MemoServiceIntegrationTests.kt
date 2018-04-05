package com.example.demo.service.impl

import com.example.demo.service.MemoService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit4.SpringRunner

/**
 * 結合テスト
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class MemoServiceIntegrationTests {

    @Autowired
    lateinit var sut: MemoService

    @Test
    fun findById() {
        val actual = sut.findById(1)

        assertThat(actual).`as`("actualは必ず検索できる").isNotNull()
        actual?.let {
            assertThat(it.id).isEqualTo(1)
        }
    }

    @Test
    fun findAll() {
        val page = PageRequest.of(0, 3)
        val actual = sut.findAll(page)

        assertThat(actual).hasSize(3)
    }

}