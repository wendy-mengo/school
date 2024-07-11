package com.wendymeng.school.admin

import com.wendymeng.school.exam.Exam
import com.wendymeng.school.exam.ExamRepository
import com.wendymeng.school.student.Student
import com.wendymeng.school.student.StudentRepository

class AdminServiceSpec extends spock.lang.Specification {
    StudentRepository mockStudentRepository = Mock()
    ExamRepository mockExamRepository = Mock()
    AdminRepository mockAdminRepository = Mock()
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
        given:
        Exam exam = new Exam()

        when:
        adminService.save(exam)

        then:
        1 * mockExamRepository.save(exam)
        0 * _

        and:
        exam == mockExamRepository.existsById(exam.examid)

    }

    def "ListAllStudents"() {
        given:
        Student student = new Student()

        when:
        List<Student> students = adminService.ListAllStudents()

        then:
        1 * mockStudentRepository.findAll() >> [student]
        0 * _

        and:
        students == [student]
    }

    def "TestSave"() {
        given:
        Student student = new Student()

        when:
        adminService.save(student)

        then:
        1 * mockStudentRepository.save(student)
        0 * _

        and:
        student == mockStudenetRepository.existsById(student.studentID)
    }

    def "Get"() {
        given:
        Long admin = new Admin()

        when:
        Admin admin1 = adminService.get(admin.adminID)

        then:
        1 * mockAdminRepository.findById(admin.adminID).get() >> [admin]
        0 * _

        and:
        admin1 == [admin]
    }

    def "CheckAdminPassword"() {
        given:
        Admin admin = new Admin()

        when:
        boolean correct = adminService.checkAdminPassword(admin.adminID, admin.password)

        then:
        1 * !mockAdminRepository.existsById(admin.adminID) >> [false]
        2 * Objects.equals(admin.password, adminService.get(admin.adminID).getPassword()) >> [true]
        0 * _

        and:
        correct == [true]

    }

    def "DeleteStudent"() {
        given:
        Student student = new Student()

        when:
        adminService.deleteStudent(student.studentID)

        then:
        1 * mockStudentRepository.delete(mockStudentRepository.findById(student.studentID).get())
        0 * _

        and:
        false == mockAdminRepository.existsById(student.studentID)

    }
}
