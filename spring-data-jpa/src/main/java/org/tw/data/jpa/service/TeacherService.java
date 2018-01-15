package org.tw.data.jpa.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.tw.data.jpa.domain.Teacher;

import javax.persistence.EntityManager;
import java.util.List;

public interface TeacherService {

	/**
	 * findAll
	 * @return
	 */
	List<Teacher> findAll();

	/**
	 * @param sort
	 * @return
	 */
	List<Teacher> findAll(Sort sort);

	/**
	 * 
	 * @param ids
	 * @return
	 */
	List<Teacher> findAll(Iterable<Integer> ids);

	/**
	 * Saves an entity and flushes changes instantly.
	 * @param entity
	 * @return the saved entity
	 */
	Teacher saveAndFlush(Teacher entity);

	Teacher save(Teacher entity) throws Exception;

	/**
	 * Deletes the given entities in a batch which means it will create a single {@link Query}. Assume that we will clear
	 * the {@link javax.persistence.EntityManager} after the call.
	 * 
	 * @param entities
	 */
	void deleteInBatch(Iterable<Teacher> entities);

	/**
	 * Deletes all entites in a batch call.
	 */
	void deleteAllInBatch();

	/**
	 * Returns a reference to the entity with the given identifier.
	 * @param id must not be {@literal null}.
	 * @return a reference to the entity with the given identifier.
	 * @see EntityManager#getReference(Class, Object)
	 */
	Teacher getOne(Integer id);
	
	/**
	 * findByName
	 * @param name
	 * @return
	 */
	List<Teacher>findByName(String name);
	
	/**
	 * delete
	 * @param id
	 */
	void delete(Integer id);
}
