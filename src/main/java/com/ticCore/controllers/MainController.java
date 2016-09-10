
package com.ticCore.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private static final Log logger = LogFactory
            .getLog(MainController.class);

    @RequestMapping(value="/")
    public String home () {
        return "index";
    }

    @RequestMapping(value="/asyncReq")
    public String asyncReq () {
        return "asyncRequest";
    }
}

