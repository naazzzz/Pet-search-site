package com.pss.PSS.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/MainWindow")
    public String MainWindow(){
        return "html/MainWindow";
    }
    @GetMapping("/MainWindow/CreateAd")
    public String createAd(@RequestParam(defaultValue = "def") String form,@RequestParam(defaultValue = "0")int step, Model model){
        switch (form){
            case "find":
                model.addAttribute("wh"," Найден");
                model.addAttribute("wh_step"," Кого вы нашли?");
            case "lost":
                model.addAttribute("wh"," Потерян");
                model.addAttribute("wh_step"," Кто пропал:?");
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
        return "html/auth";
    }



}
