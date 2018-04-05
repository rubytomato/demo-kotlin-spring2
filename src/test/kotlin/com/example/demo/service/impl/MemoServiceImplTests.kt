package com.example.demo.service.impl

import com.example.demo.entity.Memo
import com.example.demo.repository.MemoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*

/**
 * 単体テスト
 */
class MemoServiceImplTests {

    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    lateinit var repository: MemoRepository
    @InjectMocks
    lateinit var sut: MemoServiceImpl

    @Test
    fun findById() {
        val expected = Memo(id = 1, title = "title", description = "description", done = true)
        Mockito.`when`(repository.findById(anyLong())).thenReturn(Optional.ofNullable(expected))

        val actual = sut.findById(1)

        assertThat(actual).`as`("actualは必ず検索できる").isNotNull()
        assertThat(actual).isEqualTo(expected)

        verify(repository, times(1)).findById(anyLong())
    }

    @Test
    fun findAllWithEmptyList() {
        val pageable: PageRequest = PageRequest.of(0, 5)
        val expected = emptyList<Memo>()
        val page = PageImpl<Memo>(expected, pageable, 0)
        Mockito.`when`(repository.findAll(eq(pageable))).thenReturn(page)

        val actual = sut.findAll(pageable)

        assertThat(actual).hasSize(0)

        verify(repository, times(1)).findAll(eq(pageable))
    }

    @Test
    fun findAll() {
        val pageable: PageRequest = PageRequest.of(0, 5)
        val expected = listOf(
                Memo(id = 1, title = "test title 1", description = "test description 1", done = true),
                Memo(id = 2, title = "test title 2", description = "test description 2", done = false),
                Memo(id = 3, title = "test title 3", description = "test description 3", done = true)
        )
        val page = PageImpl<Memo>(expected, pageable, 3)
        Mockito.`when`(repository.findAll(eq(pageable))).thenReturn(page)

        val actual = sut.findAll(pageable)

        assertThat(actual).hasSize(3)
        assertThat(actual).containsSequence(expected)

        verify(repository, times(1)).findAll(eq(pageable))
    }

}