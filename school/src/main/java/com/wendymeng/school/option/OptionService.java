package com.wendymeng.school.option;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;
}
