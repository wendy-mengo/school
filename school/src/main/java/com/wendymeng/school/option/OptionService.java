package com.wendymeng.school.option;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public List<Option> listSpecificOptions(Long id){
        List<Option> allOptions = optionRepository.findAll();
        List<Option> specificOptions = new ArrayList<>();
        for (Option option : allOptions){
            if(id==option.getQuestionID()){
                specificOptions.add(option);
            }
        }
        return specificOptions;
    }
}
