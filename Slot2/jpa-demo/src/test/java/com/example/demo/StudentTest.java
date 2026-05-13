package com.example.demo;

import com.example.demo.Service.StudentService;
import com.example.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class StudentTest {

    @Autowired
    private StudentService studentService;

    @PersistenceContext
    private EntityManager entityManager;

    private Long savedId;

    @BeforeEach
    public void setup() {
        // Xóa data cũ trước để tránh trùng email
        entityManager.createQuery("DELETE FROM Student").executeUpdate();
        entityManager.flush();

        // Insert mới
        Student student = new Student("Nguyễn Văn A", "a@fpt.edu.vn", 20);
        entityManager.persist(student);
        entityManager.flush();

        savedId = student.getId(); // Lưu lại ID thực tế
    }

    @Test
    public void testUpdateStudent() {
        studentService.updateStudent(savedId, "Nguyễn Văn A Updated", "a.updated@fpt.edu.vn", 22);
        entityManager.flush();

        Student student = entityManager.find(Student.class, savedId);

        assertNotNull(student);
        assertEquals("Nguyễn Văn A Updated", student.getFullName());
        assertEquals("a.updated@fpt.edu.vn", student.getEmail());
        assertEquals(22, student.getAge());
    }
}