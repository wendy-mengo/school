package com.wendymeng.school.admin

import com.wendymeng.school.exam.Exam
import com.wendymeng.school.exam.ExamRepository
import com.wendymeng.school.student.StudentRepository

class AdminServiceSpec extends spock.lang.Specification {
    StudentRepository mockStudentRepository = Mock()
    ExamRepository mockExamRepository = Mock()
    AdminService adminService = new AdminService(
            studentRepository: mockStudentRepository
    )

    def "ListAllExams"() {
        given:
        Exam exam = new Exam()

        when:
        List<Exam> exams = adminService.ListAllExams()

        then:
        1 * mockExamRepository.findAll() >> [exam]
        0 * _

        and:
        exams == [exam]

    }

    def "Save"() {
    }

    def "ListAllStudents"() {
    }

    def "TestSave"() {
    }

    def "Get"() {
    }

    def "CheckAdminPassword"() {
    }

    def "DeleteStudent"() {
    }
}
