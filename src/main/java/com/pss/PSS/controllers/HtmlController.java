package com.pss.PSS.controllers;


import com.pss.PSS.models.AdEntity;
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

    AdController adController;

    @GetMapping("/MainWindow")
    public String MainWindow(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println(auth.getAuthorities().toString());
        switch (auth.getAuthorities().toString()){
            case "[ROLE_USER]":
                if(service.getUser(username)!=null) {
                    User user = service.getUser(username);
                    model.addAttribute("role", "Пользователь");
                    model.addAttribute("adress", "/UserProfile");
                    model.addAttribute("username", user.getUsername());
                    log.info("get main window from user:" + user.getUsername());
                    return "html/MainWindowFromUsers";
                }
                break;
            case "[ROLE_MODERATOR]":
                User user = service.getUser(username);
                model.addAttribute("role", "Модератор");
                model.addAttribute("adress", "/ModerationWindow");
                model.addAttribute("username", user.getUsername());
                log.info("get main window from user:" + user.getUsername());
                return "html/MainWindowFromUsers";
            default:
                log.info("get main window from none auth user");
                break;
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
            log.info("get main window from user:" + user.getUsername());
            switch (auth.getAuthorities().toString()){
                case "[ROLE_USER]":
                        model.addAttribute("role", "Пользователь");
                        model.addAttribute("adress", "/UserProfile");
                        break;
                case "[ROLE_MODERATOR]":
                    model.addAttribute("role", "Модератор");
                    model.addAttribute("adress", "/ModerationWindow");
                    break;
            }
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
    public String Ads(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if(service.getUser(username)!=null) {
            User user = service.getUser(username);
            model.addAttribute("username", user.getUsername());
            log.info("get main window from user:" + user.getUsername());
            switch (auth.getAuthorities().toString()){
                case "[ROLE_USER]":
                    model.addAttribute("role", "Пользователь");
                    model.addAttribute("adress", "/UserProfile");
                    break;
                case "[ROLE_MODERATOR]":
                    model.addAttribute("role", "Модератор");
                    model.addAttribute("adress", "/ModerationWindow");
                    break;
            }
            return "html/MapWindowFromUsers";
        }
        else{
            log.info("get main window from none auth user");
        }
        return "html/MapWindow";
    }

    @GetMapping("/ModerationWindow")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String moderation(Model model){
        log.info("get moderation window");
        return "html/ModerationWindow";
    }

    @GetMapping("/Ad")
    public String Ad(@RequestParam int ad,Model model){

        AdEntity adEntity = adController.getAd(ad).getBody();
        assert adEntity != null;
        model.addAttribute("date", adEntity.getDate());
        model.addAttribute("description", adEntity.getDescription());
        model.addAttribute("kind", adEntity.getKind());
        model.addAttribute("photo", adEntity.getPhoto());
        model.addAttribute("place", adEntity.getPlace());
        model.addAttribute("sex", adEntity.getSex());
        model.addAttribute("situation", adEntity.getSituation());//Перевести на русский
        model.addAttribute("user_description", adEntity.getUser_description());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if(service.getUser(username)!=null) {
            User user = service.getUser(username);
            model.addAttribute("username", user.getUsername());
            log.info("get main window from user:" + user.getUsername());
            switch (auth.getAuthorities().toString()){
                case "[ROLE_USER]":
                    model.addAttribute("role", "Пользователь");
                    model.addAttribute("adress", "/UserProfile");
                    break;
                case "[ROLE_MODERATOR]":
                    model.addAttribute("role", "Модератор");
                    model.addAttribute("adress", "/ModerationWindow");
                    break;
            }
            return "html/AdFromUsers";
        }
        else{
            log.info("get main window from none auth user");
        }
        return "html/Ad";
    }

}
