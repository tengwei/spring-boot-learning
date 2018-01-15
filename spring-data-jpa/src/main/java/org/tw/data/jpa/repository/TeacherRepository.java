package org.tw.data.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.tw.data.jpa.domain.Teacher;

import javax.persistence.LockModeType;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

	/**
	 * findByName
	 * @param name
	 * @return
	 */
@Lock(LockModeType.READ)
	List<Teacher>findByName(String name);


}
