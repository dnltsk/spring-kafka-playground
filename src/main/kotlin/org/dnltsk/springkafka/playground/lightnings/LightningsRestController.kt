package org.dnltsk.springkafka.playground.lightnings

import org.dnltsk.springkafka.playground.FooObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LightningsRestController @Autowired constructor(
        val lightningsRepository: LightningsRepository
){

    @RequestMapping("/lightnings")
    fun getLightnings(): List<Lightning>{
        return lightningsRepository.getLightnings()
    }


}