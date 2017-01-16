package csjobs.model.dao.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.cfg.CreateKeySecondPass;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import csjobs.model.Job;
import csjobs.model.Review;
import csjobs.model.dao.JobDao;

@Repository
public class JobDaoImpl implements JobDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostAuthorize("returnObject.published or hasRole('ROLE_ADMIN')")
	public Job getJob(Long id) {
		return entityManager.find(Job.class, id);
	}

	@Override
	public List<Job> getJobs() {
		return entityManager
				.createQuery("from Job order by id desc", Job.class)
				.getResultList();
	}

	@Override
	public List<Job> getOpenJobs() {
		String query = "from Job where publishDate < :now "
				+ "and (closeDate is null or closeDate > :now) "
				+ "order by publishDate asc";

		return entityManager.createQuery(query, Job.class)
				.setParameter("now", new Date()).getResultList();
	}

	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Job saveJob(Job job) {
		return entityManager.merge(job);
	}

	@Override
	public List<Job> viewJob(String sea) {
		return entityManager.createNativeQuery("select * from jobs where plainto_tsquery(:sea) @@ tsv", Job.class).setParameter("sea", sea).getResultList();
	}

	@Transactional
	@Override
	public Review saveComment(Review review) {
		// TODO Auto-generated method stub
		return entityManager.merge(review);
	}

}
