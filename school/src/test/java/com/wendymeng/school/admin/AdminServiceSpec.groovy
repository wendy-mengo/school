package com.wendymeng.school.admin

import com.wendymeng.school.exam.Exam
import com.wendymeng.school.exam.ExamRepository
import com.wendymeng.school.student.Student
import com.wendymeng.school.student.StudentRepository
import spock.lang.Specification

class AdminServiceSpec extends Specification {
    StudentRepository mockStudentRepository = Mock()
    ExamRepository mockExamRepository = Mock()
    AdminRepository mockAdminRepository = Mock()
    AdminService adminService = new AdminService(
            studentRepository: mockStudentRepository,
            examRepository: mockExamRepository,
            adminRepository: mockAdminRepository
    )

    def "ListAllExams"() {
        given:
        Exam exam = new Exam()

        when:
        List<Exam> exams = adminService.listAllExams()

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
    }

    def "ListAllStudents"() {
        given:
        Student student = new Student()

        when:
        List<Student> students = adminService.listAllStudents()

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
    }

    def "Get -- HAPPY PATH"() {
        given:
        Admin admin = new Admin()

        when:
        Admin admin1 = adminService.get(admin.adminID)

        then:
        1 * mockAdminRepository.findById(admin.adminID) >> Optional.of(admin)
        0 * _

        and:
        admin1 == admin
    }

    def "Get -- SAD PATH"() {
        given:
        long adminID = 12345

        when:
        Admin admin1 = adminService.get(adminID)

        then:
        1 * mockAdminRepository.findById(adminID) >> null
        0 * _

        and:
        admin1 == null
    }

    def "CheckAdminPassword"() {
        given:
        Admin admin = new Admin(adminID: 12345, password: "abcd123")

        when:
        boolean correct = adminService.checkAdminPassword(admin.adminID, admin.password)

        then:
        1 * mockAdminRepository.existsById(admin.adminID) >> true
        1 * mockAdminRepository.findById(admin.adminID) >> Optional.of(admin)
        0 * _

        and:
        correct
    }

    def "DeleteStudent -- HAPPY PATH"() {
        given:
        Student student = new Student()

        when:
        adminService.deleteStudent(student.studentID)

        then:
        1 * mockStudentRepository.findById(student.studentID) >> Optional.of(student)
        1 * mockStudentRepository.delete(student)
        0 * _
    }

    def "DeleteStudent -- Cannot find"() {
        given:
        long studentID = 124335

        when:
        adminService.deleteStudent(studentID)

        then:
        1 * mockStudentRepository.findById(studentID) >> null
        0 * _
    }
}
