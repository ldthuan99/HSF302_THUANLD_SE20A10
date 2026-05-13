package com.example.demo.Service;

import com.example.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createStudent(String name, String email, int age) {
        Student s = new Student(name, email, age);
        em.persist(s);
        System.out.println("Saved with ID = " + s.getId());
    }

    @Transactional(readOnly = true)
    public void printAll() {
        em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList()
                .forEach(System.out::println);
    }

    @Transactional
    public void updateStudent(long id, String name, String newEmail, int age) {
        Student s = em.find(Student.class, id);
        if (s != null) {
            s.setFullName(name);
            s.setEmail(newEmail);
            s.setAge(age);
            System.out.println("Updated student ID = " + id);
        } else {
            System.out.println("Student not found with ID = " + id);
        }
        // Không cần gọi em.merge() vì s đang ở trạng thái managed
    }
}