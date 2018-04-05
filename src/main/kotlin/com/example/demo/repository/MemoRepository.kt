package com.example.demo.repository

import com.example.demo.entity.Memo
import org.springframework.data.jpa.repository.JpaRepository

interface MemoRepository : JpaRepository<Memo, Long>
