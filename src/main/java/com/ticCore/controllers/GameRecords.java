package com.ticCore.controllers;

import com.ticCore.beans.GameRecord;
import com.ticCore.beans.Player;
import com.ticCore.dataServices.BaseDao;
import org.apache.log4j.chainsaw.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**Responsible for responding to player game records request
 * Created by student on 11/18/16.
 */
@Controller
public class GameRecords {
    @Autowired
    private BaseDao gameRecordsDao;
    /**
     * @param request player's current request
     * @return page to navigate to
     */
    @RequestMapping(value="myGames")
    public String getGameRecords(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("logged_in_email") == null)
            return "LoginError";
        String playerId = MainController.getSessVal(session, "logged_in_email");
        HashMap<String,Object > restrictions = new HashMap<String, Object>();
        restrictions.put("playerId", playerId );
        List<GameRecord> gameRecords = (ArrayList<GameRecord>)  gameRecordsDao.get(restrictions);
        if(gameRecords.size() > 0)
            request.setAttribute("foundRecords", true);
        request.setAttribute("gameRecords", gameRecords);

        return "myGames";
    }

}
