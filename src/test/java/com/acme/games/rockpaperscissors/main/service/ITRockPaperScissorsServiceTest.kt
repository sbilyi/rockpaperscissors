package com.acme.games.rockpaperscissors.main.service

import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsJpaConfig
import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsTestConfig
import com.acme.games.rockpaperscissors.main.domain.Game
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
class ITRockPaperScissorsServiceTest {

    @Autowired
    val rockPaperScissorsService: RockPaperScissorsService? = null

    @Autowired
    val rockPaperScissorsRepository: RockPaperScissorsRepository? = null

    @AfterEach
    internal fun tearDown() {
        rockPaperScissorsRepository!!.deleteAll()
    }

    val iterations = 200
    val expectedRate = 0.5

    @Test
    internal fun `service can predict user move ROCK`() {

        val move = Move.ROCK
        var successPredictCounter = `test predicion system`(iterations, move)
        assertTrue { successPredictCounter > iterations * expectedRate }
    }

    @Test
    internal fun `service can predict user move PAPER`() {
        val move = Move.PAPER
        var successPredictCounter = `test predicion system`(iterations, move)
        assertTrue { successPredictCounter > iterations * expectedRate }
    }

    @Test
    internal fun `service can predict user move SCISSORS`() {
        val move = Move.SCISSORS
        var successPredictCounter = `test predicion system`(iterations, move)
        assertTrue { successPredictCounter > iterations * expectedRate }
    }

    @Test
    internal fun `stats could handle empty games list`() {
        val stats = rockPaperScissorsService!!.findByUserId("nonameuserid")
        assertEquals(0, stats!!.gamesNumber)
        assertEquals(0f, stats.userWins)
        assertEquals(0f, stats.systemWins)
        assertEquals(0f, stats.noneWins)
        assertEquals(0, stats.games.size)
    }

    private fun `test predicion system`(iterations: Int, move: Move): Int {
        for (i in 1..iterations) {
            rockPaperScissorsService!!.create("sergii", move)
        }

        var successPredictCounter = 0
        for (i in 1..iterations) {
            successPredictCounter += test(Winner.SYSTEM, rockPaperScissorsService!!.create("sergii", move))
        }
        return successPredictCounter
    }

    fun Boolean.toInt() = if (this) 1 else 0

    private fun test(expected: Winner, game: Game): Int {
        return (expected == game.winner).toInt()
    }

}