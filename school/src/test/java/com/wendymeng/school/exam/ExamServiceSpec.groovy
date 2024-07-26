package com.wendymeng.school.exam

import com.wendymeng.school.admin.Admin
import com.wendymeng.school.question.Question
import com.wendymeng.school.question.QuestionRepository
import com.wendymeng.school.student.Student
import spock.lang.Specification

class ExamServiceSpec extends Specification {
    ExamRepository mockExamRepository = Mock()
    QuestionRepository mockQuestionRepository = Mock()
    ExamService examService = new ExamService(
            examRepository: mockExamRepository,
            questionRepository: mockQuestionRepository
    )

    def "ListAll"() {
        given:
        Exam exam = new Exam()

        when:
        List<Exam> exams = examService.listAll()

        then:
        1 * mockExamRepository.findAll() >> [exam]
        0 * _

        and:
        exams == [exam]
    }

    def "listStudentExam - No exam"() {
        given:
        Long studentID = 1234
        Exam exam = new Exam(studentID: 1234)

        when:
        List<Exam> exams = examService.listStudentExam(studentID)

        then:
        1 * mockExamRepository.findAll() >> []
        0 * _

        and:
        !exams
    }

    def "listStudentExam - one exam does not match"() {
        given:
        Long studentID = 1234
        Exam exam = new Exam(studentID: 4321)

        when:
        List<Exam> exams = examService.listStudentExam(studentID)

        then:
        1 * mockExamRepository.findAll() >> [exam]
        0 * _

        and:
        !exams
    }

    def "listStudentExam - one exam does  match"() {
        given:
        Long studentID = 1234
        Exam exam = new Exam(studentID: studentID)

        when:
        List<Exam> exams = examService.listStudentExam(studentID)

        then:
        1 * mockExamRepository.findAll() >> [exam]
        0 * _

        and:
        exams
        exams[0] == exam
    }

    def "save"() {
        given:
        Exam exam = new Exam()

        when:
        examService.save(exam)

        then:
        1 * mockExamRepository.save(exam)
        0 * _
    }

    def "initializeExam"() {
        //given:


        //  when:


        //   then:
        //   1 *
        //   0 * _
    }

    def "Get -- HAPPY PATH"() {
        given:
        Exam exam = new Exam()

        when:
        Exam exam1 = examService.get(exam.examid)

        then:
        1 * mockExamRepository.findById(exam.examid) >> Optional.of(exam)
        0 * _

        and:
        exam1 == exam
    }

    def "Get -- SAD PATH"() {
        given:
        long examid = 12345

        when:
        Exam exam1 = examService.get(examid)

        then:
        1 * mockExamRepository.findById(examid) >> null
        0 * _

        and:
        exam1 == null
    }

    def "Delete -- HAPPY PATH"() {
        given:
        Exam exam = new Exam()

        when:
        examService.delete(exam.examid)

        then:
        1 * mockExamRepository.findById(exam.examid) >> Optional.of(exam)
        1 * mockExamRepository.delete(exam)
        0 * _
    }

    def "Delete -- Cannot find"() {
        given:
        long examid = 124335

        when:
        examService.delete(examid)

        then:
        1 * mockExamRepository.findById(examid) >> null
        0 * _
    }

    def "wrongQuestionsFinder -- none wrong"() {
        given:
        long examid = 1234
        Question question = new Question(correct: true, exams: [examid])
        Exam exam = new Exam(examid: examid, questions: [question])


        when:
        List<Question> questions = examService.wrongQuestionsFinder(examid)

        then:
        1 * mockExamRepository.findById(examid) >> Optional.of(exam)
        0 * _

        and:
        !questions
    }

    def "wrongQuestionsFinder -- one wrong"() {
        given:
        long examid = 1234
        Question question = new Question(correct: false, exams: [examid])
        Exam exam = new Exam(examid: examid, questions: [question])


        when:
        List<Question> questions = examService.wrongQuestionsFinder(examid)

        then:
        1 * mockExamRepository.findById(examid) >> Optional.of(exam)
        0 * _

        and:
        questions
        questions[0] == question


    }

    def "findNextQuestion -- exists"() {
        given:
        Long examid = 1234
        Question question = new Question(exams: [examid], correct: null)
        Exam exam = new Exam(examid: examid, questions: [question])


        when:
        Question question1 = examService.findNextQuestion(examid)

        then:
        1 * mockExamRepository.findById(examid) >> Optional.of(exam)
        0 * _

        and:
        question1 == question
    }

    def "findNextQuestion -- doesn't exist"() {
        given:
        Long examid = 1234
        Question question = new Question(exams: [examid], correct: true)
        Exam exam = new Exam(examid: examid, questions: [question])


        when:
        Question question1 = examService.findNextQuestion(examid)

        then:
        1 * mockExamRepository.findById(examid) >> Optional.of(exam)
        0 * _

        and:
        !question1
    }

    def "calcScore"() {
        given:
        Long examid = 1234
        Question question1 = new Question(exams: [examid], correct: true, count: 1)
        Question question2 = new Question(exams: [examid], correct: false, count: 1)
        Exam exam = new Exam(examid: examid, questions: [question1, question2])

        when:
        examService.calcScore(examid)

        then:
        1 * mockExamRepository.findById(examid) >> Optional.of(exam)
        1 * mockExamRepository.save(exam)
        0 * _

        and:
        exam.score == 50.00
    }
}
