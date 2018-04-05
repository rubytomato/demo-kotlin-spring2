package com.example.demo.service

import com.example.demo.entity.Memo
import org.springframework.data.domain.Pageable

interface MemoService {
    fun findById(id: Long): Memo?
    fun findAll(page: Pageable): List<Memo>
    fun store(memo: Memo)
    fun removeById(id: Long)
}
