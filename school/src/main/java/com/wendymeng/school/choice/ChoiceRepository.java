package com.wendymeng.school.choice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {

}
