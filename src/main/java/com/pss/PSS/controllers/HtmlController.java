package com.pss.PSS.controllers;


import com.pss.PSS.models.User;
import com.pss.PSS.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Slf4j
public class HtmlController {

    @Autowired
    UserDetailsServiceImpl service;
    @GetMapping("/MainWindow")
    public String MainWindow(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if(service.getUser(username)!=null) {
            User user = service.getUser(username);
            model.addAttribute("username", user.getUsername());
            log.info("get main window from user:" + user.getUsername());
            return "html/MainWindowFromUsers";
        }
        else{
            log.info("get main window from none auth user");
        }
        return "html/MainWindow";
    }


    @GetMapping("/MainWindow/CreateAd")
    public String createAd(@RequestParam(defaultValue = "def") String form,@RequestParam(defaultValue = "0")int step, Model model){
        switch (form){
            case "find": {
                model.addAttribute("wh", " Найден");
                model.addAttribute("wh_step", " Кого вы нашли?");
                break;
            }
            case "lost": {
                model.addAttribute("wh", " Потерян");
                model.addAttribute("wh_step", " Кто пропал:?");
                break;
            }
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if(service.getUser(username)!=null) {
            User user = service.getUser(username);
            model.addAttribute("username", user.getUsername());
            log.info("get createAd window from user:" + user.getUsername());

            switch (step){
                case 1:
                    return "html/wH_form1";
                case 2:
                    return "html/wH_form2";
                case 3:
                    return "html/wH_form3";
                case 4:
                    return "html/wH_form4";
                case 5:
                    return "html/wH_form5";
                default:
                    return "html/wHFromUsers";
            }
        }
        else{
            log.info("get createAd window from none auth user");
        }

        switch (step){
            case 1:
                return "html/wH_form1";
            case 2:
                return "html/wH_form2";
            case 3:
                return "html/wH_form3";
            case 4:
                return "html/wH_form4";
            case 5:
                return "html/wH_form5";
            default:
                return "html/wH";
        }
    }
    @GetMapping("/Auth")
    public String Auth(){
        log.info("get auth window");
        return "html/Auth";
    }

    @GetMapping("/MainWindow/Ads")
    public String Ads(){
        log.info("get ads window");
        return "html/MapWindow";
    }

}
