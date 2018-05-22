package org.tw.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tw.jpa.domain.Student;


public interface StudentRepository extends JpaRepository<Student, Integer> {

}
