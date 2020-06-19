package com.example.demo.annotation.enum

enum class RetentionPolicy {
    /*注解只保留在源码中，编译为字节码之后就丢失了，也就是class文件中就不存在了*/
    SOURCE,
    /*注解只保留在源码和字节码中，运行阶段会丢失*/
    CLASS,
    /*源码、字节码、运行期间都存在*/
    RUNTIME
}