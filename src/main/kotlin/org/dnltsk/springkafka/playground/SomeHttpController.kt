package org.dnltsk.springkafka.playground

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SomeHttpController @Autowired constructor(
        val someRepository: SomeRepository
){

    @RequestMapping("/foo")
    fun getFoo(
            @RequestParam id: Int
    ): FooObject {
        return someRepository.loadSomeFoo(id)
    }


}

