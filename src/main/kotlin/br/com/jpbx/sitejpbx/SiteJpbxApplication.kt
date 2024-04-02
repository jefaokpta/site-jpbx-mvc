package br.com.jpbx.sitejpbx

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.concurrent.ForkJoinPool

@SpringBootApplication
class SiteJpbxApplication

fun main(args: Array<String>) {
    runApplication<SiteJpbxApplication>(*args)

    val log = LoggerFactory.getLogger(SiteJpbxApplication::class.java)

    log.info("MAX HEAP SIZE: ${Runtime.getRuntime().maxMemory() / 1024 / 1024} MB")
    log.info("CURRENT HEAP SIZE: ${Runtime.getRuntime().totalMemory() / 1024 / 1024} MB")
    log.info("FREE HEAP SIZE: ${Runtime.getRuntime().freeMemory() / 1024 / 1024} MB")
    log.info("CPUs DISPONIVEIS: ${Runtime.getRuntime().availableProcessors()}")
    log.info("CommonPool Parallelism: " + ForkJoinPool.commonPool().parallelism)
    log.info("CommonPool Common Parallelism: " + ForkJoinPool.getCommonPoolParallelism())
}
