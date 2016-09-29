package com.ticCore.controllers;

import com.ticCore.beans.Login;
import com.ticCore.dataServices.BaseDao;
import com.ticCore.dataServices.LoginDao;
import com.ticCore.validators.LoginValidator;
import com.ticCore.validators.RawValidator;
import org.apache.log4j.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller ;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by student on 9/5/16.
 */
@ Controller
//@RequestMapping (value="/loginService", method= RequestMethod.POST)
public class LoginServices {
    private final Logger logger = Logger.getLogger(this.getClass());
    private BaseDao dbService;
    private RawValidator rawValidator;
    private Map<String,String> errors;
    private static final String  LOGIN_SUCCESSFUL = "Successful";
    private static final String  AUTH_FAILED = "Invalid username or password";

    public void setDbService(BaseDao dbService) {
        this.dbService = dbService;
    }

    public void setRawValidator(RawValidator rawValidator) {
        this.rawValidator = rawValidator;
    }

    /**
     * @param request Request from browser
     * @return redirect page
     */
    @RequestMapping (value="/login", method= RequestMethod.POST)
    public String login(HttpServletRequest request) {
        String redirectPage = "index";
        setDbService(new LoginDao());
        setRawValidator(new LoginValidator());

        errors =   (HashMap<String,String>) rawValidator.validate(request);

        List<Login> loginInfo = verifyLogin(request);

        if (loginInfo != null && loginInfo.size() == 1 ) {
           logger.info("Login was successful");
           request.getSession().setAttribute("loggedIn", LOGIN_SUCCESSFUL);
           request.getSession().setAttribute("logged_in_email", loginInfo.get(0).getEmail());
           redirectPage = "index";
        } else if (errors == null) {
           errors = new HashMap<String,String>();
           errors.put("auth", AUTH_FAILED);
        }

        request.setAttribute("errors", errors);
        return redirectPage;

    }

    /** Verifies login from request attributes
     *@param request current request
     *@return A list of login details, expected to be of size 1 for valid logins
     */

    private  List<Login>  verifyLogin (HttpServletRequest request) {
        List <Login> loginInfo = null;
        if (errors == null) {
            ArrayList<HashMap<String, Object>> restrictions = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> emailCriteria = new HashMap<String, Object>();
            HashMap<String, Object> passCriteria = new HashMap<String, Object>();

            emailCriteria.put("email", MainController.getReqVal(request, "email"));
            passCriteria.put("password", MainController.getReqVal(request, "password"));

            restrictions.add(emailCriteria);
            restrictions.add(passCriteria);

            loginInfo =  (ArrayList<Login>) dbService.getWithLimit(restrictions, 1);
        }

        return loginInfo;
    }



}
