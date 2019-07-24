package com.acme.games.rockpaperscissors.main.domain

import com.acme.games.rockpaperscissors.main.service.JudgeJosephDreddService
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JudgeJosephDreddServiceTest {

    @Test
    internal fun `same moves should return none`() {
        assertEquals(Winner.NONE, JudgeJosephDreddService.judge(Move.ROCK, Move.ROCK))
        assertEquals(Winner.NONE, JudgeJosephDreddService.judge(Move.PAPER, Move.PAPER))
        assertEquals(Winner.NONE, JudgeJosephDreddService.judge(Move.SCISSORS, Move.SCISSORS))
    }

    @Test
    internal fun `user beats the system`() {
        assertEquals(Winner.USER, JudgeJosephDreddService.judge(Move.PAPER, Move.ROCK))
        assertEquals(Winner.USER, JudgeJosephDreddService.judge(Move.SCISSORS, Move.PAPER))
        assertEquals(Winner.USER, JudgeJosephDreddService.judge(Move.ROCK, Move.SCISSORS))
    }

    @Test
    internal fun `system beats the user`() {
        assertEquals(Winner.SYSTEM, JudgeJosephDreddService.judge(Move.ROCK, Move.PAPER))
        assertEquals(Winner.SYSTEM, JudgeJosephDreddService.judge(Move.SCISSORS, Move.ROCK))
        assertEquals(Winner.SYSTEM, JudgeJosephDreddService.judge(Move.PAPER, Move.SCISSORS))
    }
}