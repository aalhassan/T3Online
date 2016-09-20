package com.ticCore.controllers;

import com.ticCore.beans.Login;
import com.ticCore.dataServices.AbstractDBService;
import com.ticCore.dataServices.LoginDao;
import com.ticCore.validators.LoginValidator;
import com.ticCore.validators.RawValidator;
import org.apache.log4j.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by student on 9/5/16.
 */
@ Controller
public class AccountServices {
    private final Logger logger = Logger.getLogger(this.getClass());
    private AbstractDBService dbService;
    private RawValidator rawValidator;

    public AbstractDBService getDbService() {
        return dbService;
    }

    public void setDbService(AbstractDBService dbService) {
        this.dbService = dbService;
    }

    @RequestMapping (value="/login", method= RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {

        rawValidator = new LoginValidator();
        rawValidator.validate(request);
        List<String> errors =  ( ArrayList<String>) rawValidator.getValidationResults();

        setDbService(new LoginDao());
        HttpSession session =  request.getSession();

       if (errors == null || errors.size() == 0) {
           //TODO: validate login with help of hibernate
           String email = (String) request.getAttribute("email");
           String password = (String) request.getAttribute("password");

           ArrayList<HashMap<String,Object>> restrictions = new ArrayList<HashMap<String, Object>>();
           HashMap<String, Object> emailRestriction = new HashMap<String, Object>();
           emailRestriction.put("email", email);
           HashMap<String, Object> passRestriction = new HashMap<String, Object>();
           passRestriction.put("password", password);
           restrictions.add(emailRestriction);
           restrictions.add(passRestriction);

           List<Login> loginInfo = (ArrayList<Login>)   dbService.getWithLimit(restrictions, 1);
           if (loginInfo.size() == 1){
               logger.info("Login was succesful");

               session.setAttribute("loggedIn", "successful");
               session.setAttribute("logged_in_email", loginInfo.get(0).getEmail());
           }

       } else {
           request.setAttribute("errors", errors);
       }
       return "index";

    }

}
