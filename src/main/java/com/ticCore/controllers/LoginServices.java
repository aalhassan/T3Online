package com.ticCore.controllers;

import com.ticCore.beans.Login;
import com.ticCore.dataServices.BaseDao;
import com.ticCore.dataServices.LoginDao;
import com.ticCore.validators.LoginValidator;
import com.ticCore.validators.RawValidator;
import org.apache.log4j.*;

import org.apache.log4j.chainsaw.Main;
import org.hibernate.HibernateException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** Responsible for handling login/logout requests
 * Created by Mej on 9/5/16.
 */
@ Controller
public class LoginServices {
    private static final String SYSTEM_LOGIN_ERROR = "System could not verify login info. Please try agin later";
    private final Logger logger = Logger.getLogger(this.getClass());
    private BaseDao dbService;
    private RawValidator rawValidator;
    private Map<String,String> errors;
    private static final String  AUTH_FAILED = "Invalid username or password";

    public void setDbService(BaseDao dbService) {
        this.dbService = dbService;
    }
    public void setRawValidator(RawValidator rawValidator) {
        this.rawValidator = rawValidator;
    }

    /**Request handler for player login
     * @param request Request from browser
     * @return redirect page
     */
    @RequestMapping (value="loggedIn", method= RequestMethod.POST)
    public String login(HttpServletRequest request) {
        String redirectPage = MainController.HOME_PAGE;
        setDbService(new LoginDao());
        setRawValidator(new LoginValidator());

        errors =   (HashMap<String,String>) rawValidator.validate(request);

        List<Login> loginInfo = verifyLogin(request);

        if (loginInfo != null && loginInfo.size() == 1 ) {
           logger.info("Login was successful");
           request.getSession().setAttribute(MainController.LOGGED_IN, true);
           request.getSession().setAttribute(MainController.LOGGED_IN_EMAIL, loginInfo.get(0).getEmail());
        } else if (errors == null) {
           errors = new HashMap<String,String>();
           errors.put(MainController.AUTH_ERROR, AUTH_FAILED);
            redirectPage = MainController.LOGIN_ERROR_PAGE;
        } else {
            errors.put(MainController.AUTH_ERROR, AUTH_FAILED);
            redirectPage = MainController.LOGIN_ERROR_PAGE;
        }

        request.setAttribute("errors", errors);
        return redirectPage;

    }

    /** Logs out players already in session
     *@param session current session
     *@return A list of login details, expected to be of size 1 for valid logins     */

    @RequestMapping(value="loggedOut")
    public String logout(HttpSession session) {
        session.invalidate();
        return MainController.HOME_PAGE;
    }


    /**Verifies player credentials using the Dao
     * @param request Player's current request
     * @return
     */
    private  List<Login>  verifyLogin (HttpServletRequest request) {

        if (errors != null)
            return null;

        List<Login> loginInfo = null;
        HashMap<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put("email", MainController.getReqParam(request, "email"));
        restrictions.put("password", MainController.getReqParam(request, "password"));
        try {
        loginInfo = (ArrayList<Login>) dbService.getWithLimit(restrictions, 1);
        } catch (HibernateException ex) {
            errors = new HashMap<String, String>();
            errors.put(MainController.SYSTEM_ERROR, SYSTEM_LOGIN_ERROR);
        }finally {
            return loginInfo;
        }
    }
}
