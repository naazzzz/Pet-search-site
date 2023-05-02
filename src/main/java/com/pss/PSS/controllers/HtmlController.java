package com.pss.PSS.controllers;


import com.pss.PSS.models.AdEntity;
import com.pss.PSS.models.User;
import com.pss.PSS.models.UserDescriptionEntity;
import com.pss.PSS.service.AdServiceImpl;
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

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Controller
@Slf4j
public class HtmlController {


    @Autowired
    UserDetailsServiceImpl service;
    @Autowired
    AdServiceImpl adService;

    @GetMapping("/MainWindow")
    public String MainWindow(Model model){
//        System.out.println(adService.getTopAdsByDate());
//System.out.println(LocalDate. now().toString());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
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
                    model.addAttribute("username",username);
                    if(user.getUserDescriptionId()!=null){
                        UserDescriptionEntity userDescriptionEntity= service.findDescById(user.getUserDescriptionId());
                        model.addAttribute("name",userDescriptionEntity.getName());
                        model.addAttribute("email",userDescriptionEntity.getEmail());
                        model.addAttribute("telephone",userDescriptionEntity.getTelephone_number());
                    }
                    else {
                        model.addAttribute("name","");
                        model.addAttribute("email","");
                        model.addAttribute("telephone","");
                    }
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


    @GetMapping("/UserProfile")
    @PreAuthorize("hasRole('USER')")
    public String userProfile(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username",username);
        User user = service.getUser(username);
        if(user.getUserDescriptionId()!=null){
            UserDescriptionEntity userDescriptionEntity= service.findDescById(user.getUserDescriptionId());
            model.addAttribute("name",userDescriptionEntity.getName());
            model.addAttribute("email",userDescriptionEntity.getEmail());
            model.addAttribute("telephone",userDescriptionEntity.getTelephone_number());
        }
        else {
            model.addAttribute("name","");
            model.addAttribute("email","");
            model.addAttribute("telephone","");
        }
        log.info("get user profile window");
        return "html/UserProfile";
    }

    @GetMapping("/Ad")
    public String Ad(@RequestParam int ad,Model model){

        AdEntity adEntity = adService.getAd(ad).getBody();
        assert adEntity != null;
        model.addAttribute("date", adEntity.getDate());
        model.addAttribute("description", adEntity.getDescription());
        model.addAttribute("photo", adEntity.getPhoto());
        model.addAttribute("place", adEntity.getPlace());
        if (Objects.equals(adEntity.getKind(), "cat")){
            model.addAttribute("kind", " Кошка");
        }else {
            model.addAttribute("kind", " Собака");
        }
        switch (adEntity.getSex()){
            case "girl":
                model.addAttribute("sex", "Девочка");
                break;
            case "boy":
                model.addAttribute("sex", "Мальчик");
                break;
            case "indefined":
                model.addAttribute("sex", "Не определен");
                break;
        }
        if (Objects.equals(adEntity.getSituation(), "find")){
            model.addAttribute("situation", "Найден(-а)");
        }else {
            model.addAttribute("situation", "Потерян(-а)");
        }

        UserDescriptionEntity userDescriptionEntity = service.findDescById(adEntity.getUser_description());
        model.addAttribute("user_description_name", userDescriptionEntity.getName());
        model.addAttribute("user_description_email", userDescriptionEntity.getEmail());
        model.addAttribute("user_description_telephone", userDescriptionEntity.getTelephone_number());

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
