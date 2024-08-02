package com.wendymeng.school.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.wendymeng.school.admin.Admin;
import com.wendymeng.school.question.Level;
import com.wendymeng.school.question.Question;
import com.wendymeng.school.question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<Exam> listAll() {
        return examRepository.findAll();
    }

    public List<Exam> listStudentExam(Long id) {
//        List<Exam> listExams = new ArrayList<>();
//        for (Exam exam : examRepository.findAll()) {
//            if (Objects.equals(exam.getStudentID(), id)) {
//                listExams.add(exam);
//            }
//        }
        return examRepository.findByStudentID(id);

    }

    public void save(Exam exam) {
        examRepository.save(exam);
    }

    public Exam get(Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        return exam.orElse(null);
    }

    public void delete(Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        exam.ifPresent(value -> examRepository.delete(value));
    }

    public List<Question> initializeExam(Long id) {
        Exam exam = get(id);
        List<Question> allQuestions = questionRepository.findAll();
        List<Question> rightQuestions = new ArrayList<>();
        List<Question> mediumQuestions = new ArrayList<>();
        List<Question> hardQuestions = new ArrayList<>();
        for (Question question : allQuestions) {
            if (question.getType() == exam.getSubject()) {
                question.setCount(0);
                if (question.getHardLevel() == Level.EASY) {
                    rightQuestions.add(question);
                } else if (question.getHardLevel() == Level.MEDIUM) {
                    mediumQuestions.add(question);
                } else {
                    hardQuestions.add(question);
                }
            }
        }
        rightQuestions.addAll(mediumQuestions);
        rightQuestions.addAll(hardQuestions);
        exam.setQuestions(rightQuestions);
        for (Question question : exam.getQuestions()) {
            question.setCorrect(null);
            exam.setScore(0.0F);
        }
        save(exam);
        return rightQuestions;
    }

    public List<Question> wrongQuestionsFinder(Long examID) {
        Exam exam = get(examID);
        List<Question> wrongQuestions = new ArrayList<>();
        for (Question question : exam.getQuestions()) {
            if (!question.getCorrect()) {
                wrongQuestions.add(question);
            }
        }
        return wrongQuestions;
    }

    public Question findNextQuestion(Long examID) {
        Exam exam = examRepository.findById(examID).get();
        for (Question question : exam.getQuestions()) {
            if (question.getCorrect() == null) {
                return question;
            }
        }
        return null;
    }

    public void calcScore(Long examID) {
        Exam exam = get(examID);
        float count = 0.0F;
        float total = 0.0F;
        float score = 0.0F;

        for (Question question : exam.getQuestions()) {
            total++;
            if (question.getCorrect()) {
                count = count + 1 / question.getCount().floatValue();
            }
        }
        score = count / total * 100;
        exam.setScore(score);
        save(exam);
    }
}
