package com.acme.games.rockpaperscissors.main.service

import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsJpaConfig
import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsTestConfig
import com.acme.games.rockpaperscissors.main.domain.Move
import org.flywaydb.test.FlywayTestExecutionListener
import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [RockPaperScissorsJpaConfig::class, RockPaperScissorsTestConfig::class, FlywayAutoConfiguration::class], loader = AnnotationConfigContextLoader::class)
@TestExecutionListeners(listeners = [DependencyInjectionTestExecutionListener::class, FlywayTestExecutionListener::class])
@FlywayTest
class RockPaperScissorsServiceTest {

    @Autowired
    val rockPaperScissorsService: RockPaperScissorsService? = null

    @Test
    internal fun `service should create every game with a new ID`() {
        assertEquals(0, rockPaperScissorsService!!.count())
        for (i in 1..1000) {
            rockPaperScissorsService.create("sergii", Move.ROCK)
        }

        val gameIds = rockPaperScissorsService.findAll().map { e -> e.id }.toSet()
        assertEquals(1000, gameIds.size)
        assertEquals(1000, rockPaperScissorsService.count())
    }

    @Test
    @Disabled
    internal fun `service can predict user move`() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}