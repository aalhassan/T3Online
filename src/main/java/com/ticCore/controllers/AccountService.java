package com.ticCore.controllers;

import com.ticCore.beans.Player;
import com.ticCore.dataServices.BaseDao;

import org.apache.log4j.*;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller ;

import java.util.*;


/**
 * Created by student on 9/24/16.
 */

@Controller
//@RequestMapping (value="accountService")
public class AccountService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private BaseDao playersDao;


    //For testin purposes only
    public void setPlayersDao(BaseDao playersDao) {
        this.playersDao = playersDao;
    }

    @RequestMapping (value="/savePlayer", method= RequestMethod.POST)


    public String register(@ModelAttribute Player player) {
        String redirectPage = "gamePage";
        try {
            playersDao.create(player);
        } catch ( HibernateException ex) {
            redirectPage = "registerForm";
        }

        return redirectPage;

    }

    @RequestMapping(value = "/register")
    public String registerForm(Model model) {
        model.addAttribute("player", new Player());
        return "registerForm";
    }

    public List<Player> getPlayer(Player player) {
        HashMap<String,Object > restrictions = new HashMap<String, Object>();
        restrictions.put("email", player.getEmail() );
        return (ArrayList<Player>)  playersDao.get(Arrays.asList(restrictions));
    }



}
