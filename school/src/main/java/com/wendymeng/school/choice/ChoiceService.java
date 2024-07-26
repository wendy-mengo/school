package com.wendymeng.school.choice;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChoiceService {

    @Autowired
    private ChoiceRepository choiceRepository;

    public List<Choice> listSpecificOptions(Long questionID) {
        List<Choice> allChoices = choiceRepository.findAll();
        List<Choice> specificChoices = new ArrayList<>();
        for (Choice choice : allChoices) {
            if (questionID == choice.getQuestionID()) {
                specificChoices.add(choice);
            }
        }
        return specificChoices;
    }
}
