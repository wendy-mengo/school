package com.wendymeng.school.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class QuestionController {

     @Autowired
     private QuestionService questionService;

     @RequestMapping("/question/{id}")
     private String questionPage(@PathVariable Long id, Model model){
         Question question = questionService.get(id);
         model.addAttribute("question", question);

         return "displayQuestion";
     }

//     @RequestMapping("/question/{id}/option/{id}")
//     private String checkAnswer(@PathVariable Long optionID){
//        if()
//     }

}
