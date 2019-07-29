package com.acme.games.rockpaperscissors.main.service

import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsJpaConfig
import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsTestConfig
import com.acme.games.rockpaperscissors.main.domain.Move
import com.acme.games.rockpaperscissors.main.domain.Winner
import com.acme.games.rockpaperscissors.main.repository.RockPaperScissorsRepository
import org.flywaydb.test.FlywayTestExecutionListener
import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.AfterEach
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
import kotlin.test.assertTrue

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [RockPaperScissorsJpaConfig::class, RockPaperScissorsTestConfig::class, FlywayAutoConfiguration::class], loader = AnnotationConfigContextLoader::class)
@TestExecutionListeners(listeners = [DependencyInjectionTestExecutionListener::class, FlywayTestExecutionListener::class])
@FlywayTest
class RockPaperScissorsServiceTest {

    @Autowired
    val rockPaperScissorsService: RockPaperScissorsService? = null

    @Autowired
    val rockPaperScissorsRepository: RockPaperScissorsRepository? = null

    @AfterEach
    internal fun tearDown() {
        rockPaperScissorsRepository!!.deleteAll()
    }

    @Test
    internal fun `service can predict user move ROCK`() {
        for (i in 1..10) {
            rockPaperScissorsService!!.create("sergii", Move.ROCK)
        }
        for (i in 1..10) {
            assertEquals(Winner.SYSTEM, rockPaperScissorsService!!.create("sergii", Move.ROCK).winner)
        }
    }

    @Test
    internal fun `service can predict user move PAPER`() {
        for (i in 1..10) {
            rockPaperScissorsService!!.create("sergii", Move.PAPER)
        }
        for (i in 1..10) {
            assertEquals(Winner.SYSTEM, rockPaperScissorsService!!.create("sergii", Move.PAPER).winner)
        }
    }

    @Test
    internal fun `service can predict user move SCISSORS`() {
        for (i in 1..10) {
            rockPaperScissorsService!!.create("sergii", Move.SCISSORS)
        }
        for (i in 1..10) {
            assertEquals(Winner.SYSTEM, rockPaperScissorsService!!.create("sergii", Move.SCISSORS).winner)
        }
    }

    @Test
    internal fun `service can predict user move SCISSORS OR PAPER`() {
        for (i in 1..10) {
            rockPaperScissorsService!!.create("sergii", Move.SCISSORS)
            rockPaperScissorsService!!.create("sergii", Move.PAPER)
        }
        for (i in 1..10) {
            val game = rockPaperScissorsService!!.create("sergii", Move.SCISSORS)
            assertTrue {  (Move.ROCK.equals(game.systemMove)) || (Move.SCISSORS.equals(game.systemMove))}
        }
    }

}