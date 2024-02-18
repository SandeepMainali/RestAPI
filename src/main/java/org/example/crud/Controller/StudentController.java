package org.example.crud.Controller;

import org.example.crud.Repository.StudentRepository;
import org.example.crud.model.Students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/student")
    public String CreatedNewStudent(@RequestBody Students students){
        studentRepository.save(students);
        return "Student date Created in database";

    }

    @GetMapping("/student")
    public ResponseEntity<List<Students>> getAllStudent(){
        List<Students> StudentList = new ArrayList<>();
        studentRepository.findAll().forEach(StudentList::add);
        return new ResponseEntity<List<Students>>(StudentList, HttpStatus.OK);
    }

    @GetMapping("/student/{stdid}")
    public ResponseEntity<Students> getStudentsById(@PathVariable int  stdid){
        Optional<Students> std =studentRepository.findById(stdid);
        if(std.isPresent()){
            return new ResponseEntity<Students>(std.get(),HttpStatus.FOUND);
        }else {
            return new ResponseEntity<Students>(HttpStatus.NOT_FOUND);

        }


    }













}
