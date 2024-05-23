package com.wendymeng.school.question;

import com.wendymeng.school.option.Option;
import com.wendymeng.school.option.OptionService;
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

     @Autowired
     private OptionService optionService;

     @RequestMapping("/question/{id}")
     private String questionPage(@PathVariable Long id, Model model){
         Question question = questionService.get(id);
         model.addAttribute("question", question);
         List<Option> options = optionService.listSpecificOptions(id);
         model.addAttribute("options", options);

         return "displayQuestion";
     }

//     @RequestMapping("/question/{id}/option/{id}")
//     private String checkAnswer(@PathVariable Long optionID){
//        if()
//     }

}
