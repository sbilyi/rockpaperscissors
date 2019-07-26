package com.acme.games.rockpaperscissors.main

import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsJpaConfig
import com.acme.games.rockpaperscissors.main.config.RockPaperScissorsTestConfig
import com.acme.games.rockpaperscissors.main.repository.RockPaperScissorsRepository
import com.acme.games.rockpaperscissors.main.service.RockPaperScissorsService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.support.AnnotationConfigContextLoader
import javax.annotation.Resource
import javax.transaction.Transactional
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [RockPaperScissorsJpaConfig::class, RockPaperScissorsTestConfig::class], loader = AnnotationConfigContextLoader::class)
@Transactional
class InMemoryDBTest {

    private val ID: Long = 1L

    @Resource
    private val repository: RockPaperScissorsRepository? = null

    @Autowired
    private val service: RockPaperScissorsService? = null

    @BeforeEach
    internal fun setUp() {
    }

    @AfterEach
    internal fun tearDown() {
        repository!!.deleteAll()
    }

    @Test
    fun `should save the game`() {
        var game = service!!.create()
        game = repository!!.findById(game.id).get()
        assertEquals(ID, game.id)
        assertEquals(0, game.rounds.size)
    }

}