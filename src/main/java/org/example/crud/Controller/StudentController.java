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



@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/student")
    public ResponseEntity<String> CreatedNewStudent(@RequestBody Students students){
        studentRepository.save(students);
        return  new ResponseEntity<>("Created Success in DB",HttpStatus.OK);

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

    @PutMapping("/student/{stdid}")
    public ResponseEntity<String>updateById(@PathVariable int stdid, @RequestBody Students students){
        Optional<Students> std =studentRepository.findById(stdid);
           if(std.isPresent()){
               Students exist= std.get();
               exist.setName(students.getName());
               exist.setAddress(students.getAddress());
               exist.setAge(students.getAge());
               studentRepository.save(exist);

               return new ResponseEntity<>("Students Details against id" + std + "Update",HttpStatus.OK);
           }else {

               return new ResponseEntity<>("Students Details doesn't exist  for id" +std,HttpStatus.OK);
           }


    }


    @DeleteMapping("/student/{stdid}")
    public ResponseEntity<String> deletebyId(@PathVariable int stdid){
        studentRepository.deleteById(stdid);
        return new ResponseEntity<>("Student details delete by id" + stdid  ,HttpStatus.OK);
    }

    @DeleteMapping("/student")
    public ResponseEntity<String> deleteAllStudent(){
        studentRepository.deleteAll();
        return new ResponseEntity<>("All Student Details Delete from Db",HttpStatus.OK);
    }



}
