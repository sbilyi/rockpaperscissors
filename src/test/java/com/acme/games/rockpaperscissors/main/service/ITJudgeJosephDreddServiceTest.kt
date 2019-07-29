package com.acme.games.rockpaperscissors.main.service

import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsJpaConfig
import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsTestConfig
import com.acme.games.rockpaperscissors.main.domain.Move
import com.acme.games.rockpaperscissors.main.domain.Winner
import org.flywaydb.test.FlywayTestExecutionListener
import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
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
class ITJudgeJosephDreddServiceTest {

    @Autowired
    private val judgeJosephDreddService: JudgeJosephDreddService? = null

    @Test
    internal fun `same moves should return none`() {
        Assertions.assertAll(
                Executable { assertEquals(Winner.NONE, judgeJosephDreddService!!.judge(Move.ROCK, Move.ROCK)) },
                Executable { assertEquals(Winner.NONE, judgeJosephDreddService!!.judge(Move.PAPER, Move.PAPER)) },
                Executable { assertEquals(Winner.NONE, judgeJosephDreddService!!.judge(Move.SCISSORS, Move.SCISSORS)) }
        )
    }

    @Test
    internal fun `user beats the system`() {
        Assertions.assertAll(
                Executable { assertEquals(Winner.USER, judgeJosephDreddService!!.judge(Move.PAPER, Move.ROCK)) },
                Executable { assertEquals(Winner.USER, judgeJosephDreddService!!.judge(Move.SCISSORS, Move.PAPER)) },
                Executable { assertEquals(Winner.USER, judgeJosephDreddService!!.judge(Move.ROCK, Move.SCISSORS)) }
        )
    }

    @Test
    internal fun `system beats the user`() {
        Assertions.assertAll(
                Executable { assertEquals(Winner.SYSTEM, judgeJosephDreddService!!.judge(Move.ROCK, Move.PAPER)) },
                Executable { assertEquals(Winner.SYSTEM, judgeJosephDreddService!!.judge(Move.SCISSORS, Move.ROCK)) },
                Executable { assertEquals(Winner.SYSTEM, judgeJosephDreddService!!.judge(Move.PAPER, Move.SCISSORS)) }
        )
    }
}