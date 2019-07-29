package com.acme.games.rockpaperscissors.main.controller

import com.acme.games.rockpaperscissors.main.Paths
import com.acme.games.rockpaperscissors.main.domain.Game
import com.acme.games.rockpaperscissors.main.domain.Move
import com.acme.games.rockpaperscissors.main.domain.Move.*
import com.acme.games.rockpaperscissors.main.domain.Winner
import com.acme.games.rockpaperscissors.main.entities.GameEntity
import com.acme.games.rockpaperscissors.main.entities.RoundEntity
import com.acme.games.rockpaperscissors.main.service.GameService
import com.acme.games.rockpaperscissors.main.service.JudgeJosephDreddService
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.hasItem
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(RockPaperScissorsController::class)
class RockPaperScissorsControllerTest {

    val ID: Long = 1
    val userId: String = "sergii"

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val service: GameService? = null

    @MockBean
    private val judgeJosephDreddService: JudgeJosephDreddService? = null

    @BeforeEach
    internal fun setUp() {
        given(judgeJosephDreddService!!.judge(PAPER, SCISSORS)).willReturn(Winner.SYSTEM)
        given(judgeJosephDreddService.judge(SCISSORS, PAPER)).willReturn(Winner.USER)
        given(judgeJosephDreddService.judge(PAPER, PAPER)).willReturn(Winner.NONE)
        given(judgeJosephDreddService.judge(ROCK, ROCK)).willReturn(Winner.NONE)
        given(judgeJosephDreddService.judge(ROCK, PAPER)).willReturn(Winner.SYSTEM)
    }

    @Test
    @Throws(Exception::class)
    fun `statistics should return all rounds of moves`() {
        val game1 = GameEntity()
        game1.id = 1
        game1.round = round(PAPER, SCISSORS, Winner.SYSTEM)

        val game2 = GameEntity()
        game2.id = 2
        game2.round = round(ROCK, ROCK, Winner.NONE)


        given(service!!.findByUserId(userId)).willReturn(GamesStats(listOf(toGame(game1), toGame(game2))))
        mvc!!.perform(get(String.format("%s/game/%s", Paths.API_PATH, userId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$..id", hasItem<Any>(game1.id.toInt())))
                .andExpect(jsonPath("$..id", hasItem<Any>(game2.id.toInt())))
                .andExpect(jsonPath("$..userMove", hasItem<Any>(PAPER.toString())))
                .andExpect(jsonPath("$..userMove", hasItem<Any>(ROCK.toString())))
                .andExpect(jsonPath("$..systemMove", hasItem<Any>(SCISSORS.toString())))
                .andExpect(jsonPath("$..systemMove", hasItem<Any>(ROCK.toString())))
                .andExpect(jsonPath("$..winner", hasItem<Any>(Winner.SYSTEM.toString())))
                .andExpect(jsonPath("$..winner", hasItem<Any>(Winner.NONE.toString())))
    }

    @Test
    fun `can support scissors move`() {
        val game = GameEntity()
        game.id = ID
        game.round = round(SCISSORS, PAPER, Winner.USER)

        given(service!!.create(userId, SCISSORS)).willReturn(toGame(game))
        mvc!!.perform(post(String.format("%s/game/%s/%s", Paths.API_PATH, userId, SCISSORS.toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(ID.toInt())))
                .andExpect(jsonPath("$..userMove", hasItem(SCISSORS.toString())))
                .andExpect(jsonPath("$..systemMove", hasItem(PAPER.toString())))
                .andExpect(jsonPath("$..winner", hasItem(Winner.USER.toString())))
    }

    @Test
    fun `can support paper move`() {
        val game = GameEntity()
        game.id = ID
        game.round = round(PAPER, PAPER, Winner.NONE)

        given(service!!.create(userId, PAPER)).willReturn(toGame(game))
        mvc!!.perform(post(String.format("%s/game/%s/%s", Paths.API_PATH, userId, PAPER.toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(ID.toInt())))
                .andExpect(jsonPath("$..userMove", hasItem(PAPER.toString())))
                .andExpect(jsonPath("$..systemMove", hasItem(PAPER.toString())))
                .andExpect(jsonPath("$..winner", hasItem(Winner.NONE.toString())))
    }

    @Test
    fun `can support rock move`() {
        val game = GameEntity()
        game.id = ID
        game.round = round(ROCK, PAPER, Winner.SYSTEM)

        given(service!!.create(userId, ROCK)).willReturn(toGame(game))
        mvc!!.perform(post(String.format("%s/game/%s/%s", Paths.API_PATH, userId, ROCK.toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(ID.toInt())))
                .andExpect(jsonPath("$..userMove", hasItem(ROCK.toString())))
                .andExpect(jsonPath("$..systemMove", hasItem(PAPER.toString())))
                .andExpect(jsonPath("$..winner", hasItem(Winner.SYSTEM.toString())))
    }

    private fun createRound(paper: Move, scissors: Move) =
            round(paper, scissors, judgeJosephDreddService!!.judge(paper, scissors))

    private fun round(userMove: Move, systemMove: Move, winner: Winner): RoundEntity {
        val round = RoundEntity()
        round.userMove = userMove
        round.systemMove = systemMove
        round.winner = winner
        return round
    }

    private fun toGame(instance: GameEntity): Game {
        val result = Game()
        result.id = instance.id
        result.userId = instance.userId
        result.systemMove = instance.round.systemMove
        result.userMove = instance.round.userMove
        result.winner = instance.round.winner
        return result
    }
}
