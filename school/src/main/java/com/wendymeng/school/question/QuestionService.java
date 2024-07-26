package com.wendymeng.school.question;

import com.wendymeng.school.choice.Choice;
import com.wendymeng.school.choice.ChoiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ChoiceRepository choiceRepository;

    public List<Question> listAll() {
        return questionRepository.findAll();
    }


    public Question get(Long id) {
        return questionRepository.findById(id).get();
    }

    public boolean questionAnswered(Choice choice, Long questionID) {
        Question question = questionRepository.findById(questionID).get();
        List<Choice> choices = question.getChoices();
        question.setCount(question.getCount() + 1);
        if (Objects.equals(question.getAnswerID(), choice.getChoiceID())) {
            question.setCorrect(true);
            return true;
        }
        question.setCorrect(false);
        return false;
    }



public void initializeQuestion(List<Choice> choices, Question question) {
    question.setChoices(choices);
}
}
