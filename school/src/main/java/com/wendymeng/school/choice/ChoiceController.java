package com.wendymeng.school.choice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ChoiceController {

    @Autowired
    private ChoiceService choiceService;
}
