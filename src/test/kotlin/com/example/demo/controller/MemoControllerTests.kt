package com.example.demo.controller

import com.example.demo.entity.Memo
import com.example.demo.service.MemoService
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.nio.charset.Charset

/**
 * 単体テスト
 */
@RunWith(SpringRunner::class)
@WebMvcTest(MemoController::class)
class MemoControllerTests {

    @Autowired
    lateinit var mvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper
    @MockBean
    lateinit var memoService: MemoService

    private val contentType = MediaType(MediaType.APPLICATION_JSON.type,
            MediaType.APPLICATION_JSON.subtype, Charset.forName("utf8"))

    @Test
    fun getMemo() {
        val expected = Memo(id = 1L, title = "test title", description = "test description", done = true)
        val expectedJson = objectMapper.writeValueAsString(expected)
        Mockito.`when`(memoService.findById(anyLong())).thenReturn(expected)

        val builder = MockMvcRequestBuilders.get("/memo/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8)

        val result = mvc.perform(builder)
                .andExpect(status().isOk)
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$").isNotEmpty)
                .andExpect(jsonPath("$.title").value(expected.title))
                .andExpect(jsonPath("$.description").value(expected.description))
                .andExpect(jsonPath("$.done").value(expected.done))
                .andDo(`print`())
                .andReturn()

        assertThat(result.response.contentAsString).isEqualTo(expectedJson)
    }

}