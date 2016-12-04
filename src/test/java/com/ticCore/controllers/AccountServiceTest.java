package com.ticCore.controllers;

import com.ticCore.beans.Player;
import com.ticCore.controllers.AccountService;
import com.ticCore.dataServices.PlayersDao;
import com.ticCore.validators.PlayerValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.HashMap;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


/**
 * Created by student on 9/24/16.
 */
public class AccountServiceTest {
    private HashMap<String, String> restrictions;
    private static final String TEST_PRE = "test";
    private  BindingResult bindingResult;


    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountService = new AccountService();
        accountService.setPlayersDao(new PlayersDao());
        accountService.setPlayersValidator(new PlayerValidator());
        bindingResult  = mock(BindingResult.class);
    }

    @Test
    public void testCrudOperations() throws Exception {
        Player testPlayer = new Player(TEST_PRE+"3@mail.com", "testPass", "test1", "testlast", "Mom", "Matrix");
        String redirectPage = accountService.register(testPlayer, bindingResult );
        assertEquals("Redirection page failed", MainController.REG_SUCCESSFUL, redirectPage);
        Player loadedPlayer = accountService.getPlayer(testPlayer);
        assertEquals("Wrong player fetched",testPlayer.getEmail(), loadedPlayer.getEmail());
    }


    @Test
    public void registerForm() throws Exception {
        Model model = mock(Model.class);
        String redirectPage = accountService.registerForm(model);
        assertEquals("Redirection to form failed","registerForm", redirectPage);
    }

}