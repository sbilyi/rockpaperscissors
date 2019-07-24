package com.acme.games.rockpaperscissors.main

import com.acme.games.rockpaperscissors.main.domain.Move
import com.acme.games.rockpaperscissors.main.domain.Round
import org.hamcrest.CoreMatchers
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
import java.util.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@RunWith(SpringRunner::class)
@WebMvcTest(RockPaperScissorsController::class)
class RockPaperScissorsControllerTest {

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val service: GameService? = null

    @Test
    @Throws(Exception::class)
    fun givenEmployees_whenGetEmployees_thenReturnJsonArray() {
        val moves = Arrays.asList(Round(Move.PAPER, Move.SCISSORS))
        given(service!!.rounds(1)).willReturn(moves)
        mvc!!.perform(get("/api/game/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath<Collection<*>>("$", hasSize<Any>(1)))
//                .andExpect(jsonPath<String>("$[0].name", CoreMatchers.`is`(alex.name)))
    }

    @Test
    fun `when game started unique id has returned`() {
        val game = Game()
        game.id = 1L
        given(service!!.create()).willReturn(game)
        mvc!!.perform(put("/api/game/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", CoreMatchers.`is`(1)))
    }
}