package org.tw.data.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tw.data.jpa.service.TeacherService;
import org.tw.data.jpa.domain.Teacher;
import org.tw.data.jpa.repository.TeacherRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}

	@Override
	public List<Teacher> findAll(Sort sort) {
		return teacherRepository.findAll(sort);
	}

	@Override
	public List<Teacher> findAll(Iterable<Integer> ids) {
		return teacherRepository.findAll(ids);
	}

	@Override
	@Transactional
	public Teacher saveAndFlush(Teacher teacher) {
		return teacherRepository.saveAndFlush(teacher);
	}

	@Override
	//@Transactional
	public Teacher save(Teacher teacher){

		teacherRepository.save(teacher);
		teacher=teacherRepository.findOne(7);
		teacher.setName("00011");

		//teacherRepository.save(teacher);
		//teacher=teacherRepository.findOne(7);

		return teacher;
		//回滚
		//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		//回滚
		//throw new RuntimeException("11111111111111111");
		//不会回滚
		//throw new Exception("222222222222");
	}

	@Override
	public void deleteInBatch(Iterable<Teacher> entities) {
		teacherRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch() {
		teacherRepository.deleteAllInBatch();
	}

	@Override
	public Teacher getOne(Integer id) {
		return teacherRepository.getOne(id);
	}

	@Override
	public List<Teacher> findByName(String name) {
		return teacherRepository.findByName(name);
	}

	@Override
	public void delete(Integer id) {
		teacherRepository.delete(id);
	}
}