package com.example.demo.controller

import com.example.demo.entity.Memo
import com.example.demo.service.MemoService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["memo"])
class MemoController(
        private val service: MemoService) {

    @GetMapping(path = ["{id}"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun id(@PathVariable(value = "id") id: Long): ResponseEntity<Memo> {
        val memo = service.findById(id)
        return if (memo != null) {
            ResponseEntity.ok(memo)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping(path = ["list"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun list(page: Pageable): ResponseEntity<List<Memo>> {
        return ResponseEntity.ok(service.findAll(page))
    }

    @PostMapping(produces = [MediaType.TEXT_PLAIN_VALUE], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun store(@RequestBody memo: Memo): ResponseEntity<String> {
        service.store(memo)
        return ResponseEntity.ok("success")
    }

    @DeleteMapping(path = ["{id}"], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun remove(@PathVariable(value = "id") id: Long): ResponseEntity<String> {
        service.removeById(id)
        return ResponseEntity.ok("success")
    }

}