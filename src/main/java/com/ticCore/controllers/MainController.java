
package com.ticCore.controllers;

import com.ticCore.beans.Player;
import org.apache.log4j.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by student on 9/5/16.
 */
@ Controller
public class MainController {
    private final Logger logger = Logger.getLogger(this.getClass());
    private Map<String,String> errors;
    public static final String AUTH_ERROR = "Authentication error while getting Session TempDir";
    public static final String LOGGED_IN_EMAIL = "logged_in_email";
    public static final String LOGGED_IN = "loggedIn";
    public static final String FOUND_RECORD= "foundRecords";
    public static final String HOME_PAGE = "index";
    public static final String PASS_RESET_PAGE = "resetPassword";
    public static final String ADMIN_LOGIN_PAGE= "adminLogin";
    public static final String LOGIN_ERROR_PAGE = "LoginError";
    public static final String ADMIN_ERROR_PAGE = "adminError";
    public static final String GAME_BOARD_PAGE= "gamePage";
    public static final String GAME_RECORDS_PAGE= "myGames";
    public static final String REGISTER_FORM_PAGE= "registerForm";
    public static final String GAME_RECORDS = "gameRecords";
    public static final String SYSTEM_ERROR = "System Error";
    public static final String REG_SUCCESSFUL = "regSuccess";



    /**
     * @return
     */
    @RequestMapping(value="")
    public String home () {
        return HOME_PAGE;
    }

    /** Handler for home page
     * @return Redirect page
     */
    @RequestMapping(value="home")
    public String homeNav () {
        return HOME_PAGE;
    }

    /** Handler for password reset
     * @param model player bean to be intitialized and passed to browser
     * @return  Redirect page
     */
    @RequestMapping(value="resetPassword")
    public String resetPassword (Model model) {
        model.addAttribute("player", new Player());
        return  PASS_RESET_PAGE;
    }

    /**
     * @return Redirect page
     */
    @RequestMapping(value="adminLogin")
    public String adminLogin () {
        return ADMIN_LOGIN_PAGE;
    }

    /**
     * @return Redirect page
     */
    @RequestMapping(value="adminError")
    public String adminError() {
        return ADMIN_ERROR_PAGE;
    }

    /**
     * @param request Current request
     * @param session Session associated with current request
     * @return Redirect page
     */
    @RequestMapping(value="gameBoard")
    public String gamePage(HttpServletRequest request,  HttpSession session) {
        if (session.getAttribute("logged_in_email") != null) {
            logger.info("Game Page Accesses successfully");
        return GAME_BOARD_PAGE;

        }
        else {
            logger.info("Game Page Access failed");
            errors = new HashMap<String, String>();
            errors.put("auth", AUTH_ERROR);
            request.setAttribute("errors", errors);
            return LOGIN_ERROR_PAGE;
        }
    }

    /** Gets value of a request attribute without need for casting
     *@param request current request
     *@param attribute request parameter
     *@return value of the attribute or null
     */
    public static  <T> T getReqParam (HttpServletRequest request, String attribute) {
        if (request.getParameter(attribute) != null)
            return (T) request.getParameter(attribute);
        return null;
    }


    /** Gets value of a request attribute without need for casting
     *@param session current session
     *@param attribute session attribute
     *@return value of the attribute or null
     */
    public static  <T> T getSessVal (HttpSession session, String attribute) {

        if (session.getAttribute(attribute) != null)
            return (T) session.getAttribute(attribute);
        return null;
    }


    public class REG_SUCCESSFUL {
    }
}



