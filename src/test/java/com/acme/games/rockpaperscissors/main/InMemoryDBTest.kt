package com.acme.games.rockpaperscissors.main

import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsJpaConfig
import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsTestConfig
import com.acme.games.rockpaperscissors.main.domain.Move
import com.acme.games.rockpaperscissors.main.repository.RockPaperScissorsRepository
import com.acme.games.rockpaperscissors.main.service.RockPaperScissorsService
import org.flywaydb.test.FlywayTestExecutionListener
import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import javax.annotation.Resource
import javax.validation.ConstraintViolationException
import kotlin.test.assertEquals
import kotlin.test.assertNull

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [RockPaperScissorsJpaConfig::class, RockPaperScissorsTestConfig::class, FlywayAutoConfiguration::class], loader = AnnotationConfigContextLoader::class)
@TestExecutionListeners(listeners = [DependencyInjectionTestExecutionListener::class, FlywayTestExecutionListener::class])
@FlywayTest
class InMemoryDBTest {

    private val ID: Long = 1L

    @Resource
    private val repository: RockPaperScissorsRepository? = null

    @Autowired
    private val service: RockPaperScissorsService? = null

    @Test
    internal fun `should not save game with no user id`() {
        assertThrows(ConstraintViolationException::class.java) {
            val expected = service!!.create(null, Move.ROCK)
            val actual = repository!!.findById(expected.id).get()
            assertEquals(expected.id, actual.id)
            assertNull(actual.userId)
        }
    }

    @Test
    fun `should save the game`() {
        val userId = "sergii"
        val expected = service!!.create(userId, Move.ROCK)
        val actuals = service.loadStats(userId)
        assertEquals(1, actuals!!.games.size)
        val actual = actuals.games[0]
        assertEquals(expected.userId, actual.userId)
        assertEquals(expected.userMove, actual.userMove)

    }

}