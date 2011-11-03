package com.poc.domainclass1

class Car {
    Engine engine
    static constraints = {
        engine(nullable: true)
    }
}
