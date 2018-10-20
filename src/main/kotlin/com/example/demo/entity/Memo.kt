package com.example.demo.entity

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "memo")
data class Memo(
        @Column(name = "title", nullable = false)
        var title: String,
        @Column(name = "description", nullable = false)
        var description: String,
        @Column(name = "done", nullable = false)
        var done: Boolean = false,
        @Column(name = "updated", nullable = false)
        var updated: LocalDateTime = LocalDateTime.now(),
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
) : Serializable {

    fun merge(memo: Memo) {
        title = memo.title
        description = memo.description
        done = memo.done
    }

    @PrePersist
    fun prePersist() {
        updated = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        updated = LocalDateTime.now()
    }
}