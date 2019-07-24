package com.acme.games.rockpaperscissors.main.controller

import com.acme.games.rockpaperscissors.main.Paths
import com.acme.games.rockpaperscissors.main.domain.Move
import com.acme.games.rockpaperscissors.main.domain.Move.*
import com.acme.games.rockpaperscissors.main.domain.Round
import com.acme.games.rockpaperscissors.main.domain.Winner
import com.acme.games.rockpaperscissors.main.entities.Game
import com.acme.games.rockpaperscissors.main.service.GameService
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@RunWith(SpringRunner::class)
@WebMvcTest(RockPaperScissorsController::class)
class RockPaperScissorsControllerTest {

    val ID: Long = 1

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val service: GameService? = null

    @Test
    @Throws(Exception::class)
    fun `statistics should return all rounds of moves`() {
        val moves = Arrays.asList(Round(PAPER, SCISSORS), Round(ROCK, ROCK))
        val game = Game()
        game.rounds = moves
        game.id = ID

        given(service!!.find(ID)).willReturn(game)
        mvc!!.perform(get(String.format("%s/game/%d", Paths.API_PATH, ID))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(ID.toInt())))
                .andExpect(jsonPath("$.rounds", hasSize<Any>(2)))
                .andExpect(jsonPath("$..userMove", hasItem<Any>(PAPER.toString())))
                .andExpect(jsonPath("$..userMove", hasItem<Any>(ROCK.toString())))
                .andExpect(jsonPath("$..systemMove", hasItem<Any>(SCISSORS.toString())))
                .andExpect(jsonPath("$..systemMove", hasItem<Any>(ROCK.toString())))
                .andExpect(jsonPath("$..winner", hasItem<Any>(Winner.SYSTEM.toString())))
                .andExpect(jsonPath("$..winner", hasItem<Any>(Winner.NONE.toString())))
    }

    @Test
    fun `new game shouldn't have any rounds`() {
        val game = Game()
        game.id = ID
        given(service!!.create()).willReturn(game)
        mvc!!.perform(post(String.format("%s/game/", Paths.API_PATH))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(1)))
                .andExpect(jsonPath("$.rounds", hasSize<Any>(0)))
    }

    @Test
    fun `can support scissors move`() {
        val game = Game()
        game.id = ID
        game.rounds = Arrays.asList(Round(Move.SCISSORS, Move.PAPER))

        given(service!!.move(ID, SCISSORS)).willReturn(game)
        mvc!!.perform(post(String.format("%s/game/%d/%s", Paths.API_PATH, ID, Move.SCISSORS.toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(ID.toInt())))
                .andExpect(jsonPath("$.rounds", hasSize<Any>(1)))
                .andExpect(jsonPath("$..userMove", hasItem(Move.SCISSORS.toString())))
                .andExpect(jsonPath("$..systemMove", hasItem(Move.PAPER.toString())))
                .andExpect(jsonPath("$..winner", hasItem(Winner.USER.toString())))
    }

    @Test
    fun `can support paper move`() {
        val game = Game()
        game.id = ID
        game.rounds = Arrays.asList(Round(Move.PAPER, Move.PAPER))

        given(service!!.move(ID, PAPER)).willReturn(game)
        mvc!!.perform(post(String.format("%s/game/%d/%s", Paths.API_PATH, ID, Move.PAPER.toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(ID.toInt())))
                .andExpect(jsonPath("$.rounds", hasSize<Any>(1)))
                .andExpect(jsonPath("$..userMove", hasItem(Move.PAPER.toString())))
                .andExpect(jsonPath("$..systemMove", hasItem(Move.PAPER.toString())))
                .andExpect(jsonPath("$..winner", hasItem(Winner.NONE.toString())))
    }

    @Test
    fun `can support rock move`() {
        val game = Game()
        game.id = ID
        game.rounds = Arrays.asList(Round(Move.ROCK, Move.PAPER))

        given(service!!.move(ID, ROCK)).willReturn(game)
        mvc!!.perform(post(String.format("%s/game/%d/%s", Paths.API_PATH, ID, Move.ROCK.toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(ID.toInt())))
                .andExpect(jsonPath("$.rounds", hasSize<Any>(1)))
                .andExpect(jsonPath("$..userMove", hasItem(Move.ROCK.toString())))
                .andExpect(jsonPath("$..systemMove", hasItem(Move.PAPER.toString())))
                .andExpect(jsonPath("$..winner", hasItem(Winner.SYSTEM.toString())))
    }
}