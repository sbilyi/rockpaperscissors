package com.acme.games.rockpaperscissors.main.service

import com.acme.games.rockpaperscissors.main.domain.Move
import com.acme.games.rockpaperscissors.main.domain.Winner
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import kotlin.test.assertEquals

class JudgeJosephDreddServiceTest {

    @Test
    internal fun `same moves should return none`() {
        Assertions.assertAll(
                Executable { assertEquals(Winner.NONE, JudgeJosephDreddService.judge(Move.ROCK, Move.ROCK)) },
                Executable { assertEquals(Winner.NONE, JudgeJosephDreddService.judge(Move.PAPER, Move.PAPER)) },
                Executable { assertEquals(Winner.NONE, JudgeJosephDreddService.judge(Move.SCISSORS, Move.SCISSORS)) }
        )
    }

    @Test
    internal fun `user beats the system`() {
        Assertions.assertAll(
                Executable { assertEquals(Winner.USER, JudgeJosephDreddService.judge(Move.PAPER, Move.ROCK)) },
                Executable { assertEquals(Winner.USER, JudgeJosephDreddService.judge(Move.SCISSORS, Move.PAPER)) },
                Executable { assertEquals(Winner.USER, JudgeJosephDreddService.judge(Move.ROCK, Move.SCISSORS)) }
        )
    }

    @Test
    internal fun `system beats the user`() {
        Assertions.assertAll(
                Executable { assertEquals(Winner.SYSTEM, JudgeJosephDreddService.judge(Move.ROCK, Move.PAPER)) },
                Executable { assertEquals(Winner.SYSTEM, JudgeJosephDreddService.judge(Move.SCISSORS, Move.ROCK)) },
                Executable { assertEquals(Winner.SYSTEM, JudgeJosephDreddService.judge(Move.PAPER, Move.SCISSORS)) }
        )
    }
}