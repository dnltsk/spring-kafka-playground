package org.dnltsk.springkafka.playground

import org.springframework.stereotype.Repository

@Repository
class SomeRepository {

    fun loadSomeFoo(id: Int): FooObject {
        /* external load */
        return FooObject(id)
    }

}