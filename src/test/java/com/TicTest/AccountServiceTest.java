package com.TicTest;

import com.ticCore.beans.Player;
import com.ticCore.controllers.AccountService;
import com.ticCore.dataServices.PlayersDao;
import org.junit.Before;
import org.junit.Test;

import org.springframework.ui.Model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


/**
 * Created by student on 9/24/16.
 */
public class AccountServiceTest {
    private HashMap<String, String> restrictions;
    private static final String TEST_PRE = "test";

    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountService = new AccountService();
        accountService.setPlayersDao(new PlayersDao());
    }

    @Test
    public void testCrudOperations() throws Exception {


       /* Player testPlayer = new Player(TEST_PRE+"1@mail.com", "testPass", "test1", "testlast", "Mom", "Matrix");
       *//* Model model = mock(Model.class);
        when(model.asMap().get("player").thenReturn(testPlayer)*//*

        String redirectPage = accountService.register(testPlayer);
        assertEquals("Redirection page failed", "gamePage", redirectPage);

        List<Player> players = accountService.getPlayer(testPlayer);
        assertEquals("Player not created", 1, players.size());
        Player loadedPlayer =  players.get(0);
        assertEquals("Wrong player fetched",testPlayer.getEmail(), loadedPlayer.getEmail());
        *//*Player loadedPlayer =  accountService.updatePlayer(testPlayer, "lastName", "testLast1").get(0);
        assertEquals("Wrong player fetched",testPlayer.getEmail(), loadedPlayer.getEmail());*/
    }


    @Test
    public void registerForm() throws Exception {
        Model model = mock(Model.class);

        String redirectPage = accountService.registerForm(model);
        assertEquals("Redirection to form failed","registerForm", redirectPage);


    }

}