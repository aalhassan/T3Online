
package com.ticCore.controllers;

import org.apache.log4j.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by student on 9/5/16.
 */
@ Controller
public class MainController {
    private final Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value="/")
    public String home () {
        return "index";
    }

    @RequestMapping(value="/resetPassword")
    public String resetPassword () {
        return "resetPassword";
    }

    @RequestMapping(value="/adminLogin")
    public String adminLogin () {
        return "adminLogin";
    }

    @RequestMapping(value="/adminError")
    public String adminError() {
        return "adminError";
    }


    /** Gets value of a request attribute without need for casting
     *@param request current request
     *@param attribute session attribute
     *@return value of the attribute or null
     */
    public static  <T> T getReqVal (HttpServletRequest request, String attribute) {
        if (request.getAttribute(attribute) != null)
            return (T) request.getAttribute(attribute);
        return null;
    }
}

