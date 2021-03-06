package com.ticCore.controllers;

import com.ticCore.beans.Player;
import com.ticCore.dataServices.BaseDao;

import org.apache.log4j.*;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller ;

import java.util.*;


/** A spring controller responsible for handling requests for account services
 * like registration,
 * Created by student on 9/24/16.
 */

@Controller
public class AccountService {
    private static final String UPDATE_ERROR = "Error updating player info, please try again";
    private static final String CREATE_ERROR = "Error registering player. Please call our customer support for help";
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private BaseDao playersDao;

    @Qualifier("playerValidator")
    @Autowired
    private Validator playersValidator;

    @Qualifier("passResetValidator")
    @Autowired
    private Validator passResetValidator;

    //For testing purposes only
    public void setPassResetValidator(Validator passResetValidator) {
        this.passResetValidator = passResetValidator;
    }

    //For testing purposes only
    public void setPlayersValidator(Validator playersValidator) {
        this.playersValidator = playersValidator;
    }

    //For testing purposes only
    public void setPlayersDao(BaseDao playersDao) {
        this.playersDao = playersDao;
    }

    /**
     * @param player player entity representing the player to register
     * @param bindingResult Provides errors and validation results if any
     * @return redirect view name
     */
    @RequestMapping (value="savePlayer", method= RequestMethod.POST)
    public String register(@ModelAttribute Player player, BindingResult bindingResult) {
        String redirectPage = MainController.REG_SUCCESSFUL;
        playersValidator.validate(player,bindingResult);

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            logger.info("Code:" + fieldError.getCode() + ", field:"

                    + fieldError.getField());

            redirectPage = MainController.REGISTER_FORM_PAGE;
        } else {

            try {
                playersDao.create(player);
            } catch (HibernateException ex) {

               logger.trace(ex);
                bindingResult.addError(new ObjectError(MainController.SYSTEM_ERROR, CREATE_ERROR));
                redirectPage = MainController.REGISTER_FORM_PAGE;
            }
        }

        return redirectPage;
    }
    /**
     * @param model Map for all beans passed to and from the client
     * @return redirect view name
     */
    @RequestMapping(value = "register")
    public String registerForm(Model model) {
        model.addAttribute("player", new Player());
        return MainController.REGISTER_FORM_PAGE;
    }

    /**
     * @param player object version of player to fetch from db
     * @return Db version of the player if any, or null
     */
    public Player getPlayer(Player player) {
        HashMap<String,Object > restrictions = new HashMap<String, Object>();
        restrictions.put("email", player.getEmail() );
        List<Player> players = (ArrayList<Player>)  playersDao.get(restrictions);
        return players.size()==1?players.get(0):null;
    }

    /** Updates an existing if requested from browser
     * @param player player entity representing the player to update
     * @param bindingResult holds results for entity validation
     * @return redirect page
     */
    @RequestMapping (value="updateUser", method= RequestMethod.POST)
    public String update(@ModelAttribute Player player, BindingResult bindingResult) {
        String redirectPage = MainController.HOME_PAGE;
        passResetValidator.validate(player,bindingResult);
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            logger.info("Code:" + fieldError.getCode() + ", field:"
                    + fieldError.getField());
            redirectPage = MainController.PASS_RESET_PAGE;
        }
        else {
            try {
                playersDao.update(player);
            } catch (HibernateException ex) {
                logger.trace(ex);
                bindingResult.addError(new ObjectError(MainController.SYSTEM_ERROR, UPDATE_ERROR));
                redirectPage = MainController.PASS_RESET_PAGE;
            }

        }
        return redirectPage;

    }
}
