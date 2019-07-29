package com.acme.games.rockpaperscissors.main.service

import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsTestConfig
import com.acme.games.rockpaperscissors.main.domain.Move
import com.acme.games.rockpaperscissors.main.domain.Winner
import com.acme.games.rockpaperscissors.main.entities.GameEntity
import com.acme.games.rockpaperscissors.main.entities.RoundEntity
import com.acme.games.rockpaperscissors.main.repository.RockPaperScissorsRepository
import com.acme.games.rockpaperscissors.main.repository.RoundsRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.support.AnnotationConfigContextLoader
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [RockPaperScissorsTestConfig::class], loader = AnnotationConfigContextLoader::class)
class RockPaperScissorsServiceTest {

    @Autowired
    val service: RockPaperScissorsService? = null

    @MockBean
    private val repository: RockPaperScissorsRepository? = null

    @MockBean
    private val roundsRepository: RoundsRepository? = null

    @Test
    internal fun `game stats could handle one game`() {
        val game = gameEntity("sergii", Move.SCISSORS, Move.PAPER, Winner.USER)

        BDDMockito.given(repository!!.findByUserId("sergii")).willReturn(listOf(game))

        val data = service!!.findByUserId("sergii")
        Assertions.assertAll(
                Executable { assertEquals(1, data!!.gamesNumber) },
                Executable { assertEquals(0f, data!!.noneWins) },
                Executable { assertEquals(1f, data!!.userWins) },
                Executable { assertEquals(0f, data!!.systemWins) },
                Executable { assertEquals(1, data!!.games.size) }
        )
        assertEquals(1, data!!.gamesNumber)
        assertEquals(0f, data.noneWins)
        assertEquals(1f, data.userWins)
        assertEquals(0f, data.systemWins)
        assertEquals(1, data.games.size)
    }

    @Test
    internal fun `game stats could handle 2 games`() {
        val game1 = gameEntity("sergii", Move.SCISSORS, Move.PAPER, Winner.USER)
        val game2 = gameEntity("sergii", Move.PAPER, Move.PAPER, Winner.USER)

        BDDMockito.given(repository!!.findByUserId("sergii")).willReturn(listOf(game1))

        val data = service!!.findByUserId("sergii")
        Assertions.assertAll(
                Executable { assertEquals(1, data!!.gamesNumber) },
                Executable { assertEquals(0.5f, data!!.noneWins) },
                Executable { assertEquals(0.5f, data!!.userWins) },
                Executable { assertEquals(0f, data!!.systemWins) },
                Executable { assertEquals(2, data!!.games.size) }
        )
    }

    private fun gameEntity(name: String, userMove: Move, systemMove: Move, winner: Winner): GameEntity {
        val game = GameEntity()
        game.userId = name
        game.round = RoundEntity()
        game.round.systemMove = systemMove
        game.round.userMove = userMove
        game.round.winner = winner
        return game
    }
}
