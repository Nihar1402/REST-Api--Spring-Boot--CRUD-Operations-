package com.CrudApi.controllers;

import com.CrudApi.entity.Student;
import com.CrudApi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    // Api to Insert Strudent Record in database
    @PostMapping("/insertStudent")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok("Student Register Successfully");
    }

    //Api to get all student record from database
    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    @PostMapping("updateStudentRecord/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {

        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        } else {

            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setAge(updatedStudent.getAge());
            student.setRollNo(updatedStudent.getRollNo());
            student.setMarks(updatedStudent.getMarks());
            studentRepository.save(student);
            return ResponseEntity.ok(student);
        }
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Student Record deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found with ID " + id + ".");
        }
    }

}