package com.wendymeng.school.question;

import com.wendymeng.school.option.Option;
import com.wendymeng.school.option.OptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OptionRepository optionRepository;

    public List<Question> listAll(){
        return questionRepository.findAll();
    }

//    public Boolean checkAnswer(Option option){
//        if (option == )
//    }

    public Question get(Long id){
        return questionRepository.findById(id).get();
    }


}
