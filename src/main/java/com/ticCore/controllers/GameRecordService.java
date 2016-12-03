package com.ticCore.controllers;

import com.ticCore.beans.GameRecord;
import com.ticCore.dataServices.BaseDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Responsible for responding to player game records request
 * Created by student on 11/18/16.
 */
@Controller
public class GameRecordService {
    @Autowired
    private BaseDao gameRecordsDao;
    private final Logger logger = Logger.getLogger(this.getClass());
    private Map<String,String> errors;

    public void setGameRecordsDao(BaseDao gameRecordsDao) {
        this.gameRecordsDao = gameRecordsDao;
    }

    /**
     * @param request player's current request
     * @return page to navigateto
     */
    @RequestMapping(value="myGames")
    public String getGameRecords(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("logged_in_email") == null) {
            logger.info("Game  Record Page Access failed");
            errors = new HashMap<String, String>();
            errors.put("auth", MainController.AUTH_ERROR);
            request.setAttribute("errors", errors);
            return MainController.LOGIN_ERROR_PAGE;
        }

        String playerId = MainController.getSessVal(session, "logged_in_email");
        HashMap<String,Object > restrictions = new HashMap<String, Object>();
        restrictions.put("playerId", playerId );
        List<GameRecord> gameRecords = (ArrayList<GameRecord>)  gameRecordsDao.get(restrictions);
        if(gameRecords.size() > 0)
            request.setAttribute("foundRecords", true);
        logger.info("Found " + gameRecords.size() + " game records for player");
        request.setAttribute("gameRecords", gameRecords);
        return MainController.GAME_RECORDS_PAGE;
    }

}
