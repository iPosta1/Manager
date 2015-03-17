package com.exadel.web;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class GameController {

    // for logging
    private static final Logger LOG = Logger.getLogger(GameController.class.getName());
    
    @RequestMapping("{gameID}")
    public String toDraft(@PathVariable("gameID") String gameID, HttpSession session,
            HttpServletRequest request) {
        return "game";
    }
}
