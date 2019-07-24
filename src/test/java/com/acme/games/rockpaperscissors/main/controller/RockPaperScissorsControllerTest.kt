package com.acme.games.rockpaperscissors.main.controller

import com.acme.games.rockpaperscissors.main.domain.Move.*
import com.acme.games.rockpaperscissors.main.domain.Round
import com.acme.games.rockpaperscissors.main.domain.Winner
import com.acme.games.rockpaperscissors.main.entities.Game
import com.acme.games.rockpaperscissors.main.service.GameService
import org.hamcrest.CoreMatchers
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

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val service: GameService? = null

    @Test
    @Throws(Exception::class)
    fun `statistics should return all rounds of moves`() {
        val moves = Arrays.asList(Round(PAPER, SCISSORS), Round(ROCK, ROCK))
        given(service!!.rounds(1)).willReturn(moves)
        mvc!!.perform(get("/api/game/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize<Any>(2)))
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
        game.id = 1L
        given(service!!.create()).willReturn(game)
        mvc!!.perform(post("/api/game/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", CoreMatchers.`is`(1)))
                .andExpect(jsonPath("$.rounds", hasSize<Any>(0)))
    }


}