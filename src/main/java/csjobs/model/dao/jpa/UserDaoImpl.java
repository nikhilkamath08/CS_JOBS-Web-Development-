package csjobs.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import csjobs.model.Round;
import csjobs.model.User;
import csjobs.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User getUser(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User getUser(String email) {
		String query = "from User user left join fetch user.roles "
				+ "where lower(email) = :email";

		List<User> users = entityManager.createQuery(query, User.class)
				.setParameter("email", email.toLowerCase()).getResultList();
		return users.size() == 0 ? null : users.get(0);
	}

	@Override
	public List<User> getUsers(String role) {
		String query = "select user from User user left join user.roles role "
				+ "where role = :role";

		return entityManager.createQuery(query, User.class)
				.setParameter("role", role).getResultList();
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		return entityManager.merge(user);
	}

	@Override
	public Round saveRound(Long appid, Integer rindex) {
		// TODO Auto-generated method stub
		return entityManager.createQuery(
						"from Round r where r.application.id=:appid and index=:rindex",
						Round.class).setParameter("appid", appid)
				.setParameter("rindex", rindex).getSingleResult();
	}

	@Transactional
	@Override
	public Round saveRound(Round rounds) {
		// TODO Auto-generated method stub
		return entityManager.merge(rounds);
	}

	/*
	 * @Override public Round saveRound(Long appid, Integer index) {
	 * 
	 * return entityManager.createNamedQuery(
	 * "from Round where application.id=:appid and index=:index",
	 * Round.class).setParameter("appid", appid).setParameter("index",
	 * index).getSingleResult(); }
	 */

}
