package com.htbeyond.controller

import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.RestController


@RestController
class ResourceController {

    @GetMapping("/beyond")
    fun getBeyond(): Array<String> {
        return arrayOf("Beyond 1", "Beyond 2", "Beyond 3")
    }
}