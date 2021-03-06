package com.ipn.escom.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ipn.escom.entity.UserEntity;
import com.ipn.escom.persistence.PersistenceUtil;
import com.ipn.escom.security.HashUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public UserEntity saveUser(UserEntity user) {
		EntityManager entityManager = PersistenceUtil.getDevEntityManager();
		entityManager.getTransaction().begin();
		user.setPassword(HashUtil.getHash(user.getPassword()));
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		user.setPassword(null);
		return user;
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		UserEntity userEntity = this.findUser(user);
		if (userEntity != null) {
			userEntity.setUsername(user.getUsername());
			userEntity.setPassword(HashUtil.getHash(user.getPassword()));
			userEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			EntityManager entityManager = PersistenceUtil.getDevEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(entityManager.contains(userEntity) ? userEntity : entityManager.merge(userEntity));
			entityManager.getTransaction().commit();
			entityManager.close();
		}
		return userEntity;
	}

	@Override
	public UserEntity findUser(UserEntity user) {
		EntityManager entityManager = PersistenceUtil.getDevEntityManager();
		UserEntity userEntity = entityManager.find(UserEntity.class, user.getIdUser());
		entityManager.close();
		userEntity.setPassword(null);
		return userEntity;
	}

	@Override
	public List<UserEntity> findAllUsers() {
		EntityManager entityManager = PersistenceUtil.getDevEntityManager();
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
		criteriaQuery.select(root);
		Query<UserEntity> query = session.createQuery(criteriaQuery);
		List<UserEntity> users = query.getResultList();
		entityManager.close();
		return users.stream().map(u -> {
			u.setPassword(null);
			return u;
		}).collect(Collectors.toList());
	}

	@Override
	public List<UserEntity> findUserByUsernameAndPassword(UserEntity user) {
		EntityManager entityManager = PersistenceUtil.getDevEntityManager();
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), user.getUsername()),
				criteriaBuilder.equal(root.get("password"), HashUtil.getHash(user.getPassword())));
		Query<UserEntity> query = session.createQuery(criteriaQuery);
		List<UserEntity> users = query.getResultList();
		entityManager.close();
		return users.stream().map(u -> {
			u.setPassword(null);
			return u;
		}).collect(Collectors.toList());
	}

	@Override
	public List<UserEntity> findUserByUsername(UserEntity user) {
		EntityManager entityManager = PersistenceUtil.getDevEntityManager();
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), user.getUsername()));
		Query<UserEntity> query = session.createQuery(criteriaQuery);
		List<UserEntity> users = query.getResultList();
		entityManager.close();
		return users.stream().collect(Collectors.toList());
	}

	@Override
	public List<UserEntity> findUserById(UserEntity user) {
		EntityManager entityManager = PersistenceUtil.getDevEntityManager();
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("idUser"), user.getIdUser()));
		Query<UserEntity> query = session.createQuery(criteriaQuery);
		List<UserEntity> users = query.getResultList();
		entityManager.close();
		return users.stream().map(u -> {
			u.setPassword(null);
			return u;
		}).collect(Collectors.toList());
	}

	@Override
	public boolean deleteUser(UserEntity user) {
		UserEntity userEntity = this.findUser(user);
		if (userEntity != null) {
			EntityManager entityManager = PersistenceUtil.getDevEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		}
		return false;
	}

}
