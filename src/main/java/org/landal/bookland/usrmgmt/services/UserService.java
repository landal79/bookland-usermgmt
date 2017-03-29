package org.landal.bookland.usrmgmt.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.landal.bookland.usrmgmt.model.User;
import org.landal.bookland.usrmgmt.model.User_;

@Stateless
public class UserService {

	@Inject
	private EntityManager em;

	@Inject
	private Event<User> userEvent;

	public void save(User user) throws Exception {

		if (user.getId() == null) {
			em.persist(user);
		} else {
			user = em.merge(user);
		}

		userEvent.fire(user);

	}

	public void remove(User user) {
		Preconditions.checkNotNull(user);
		user = em.find(User.class, user.getId());
		em.remove(user);
	}

	public void remove(Long id) {
		Preconditions.checkNotNull(id);
		remove(em.find(Long.class, id));
	}

	public User findById(Long id) {
		return em.find(User.class, id);
	}

	public List<User> findByExample(User user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);

		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotEmpty(user.getName())) {
			predicates.add(cb.equal(root.get(User_.name), user.getName()));
		}

		if (StringUtils.isNotEmpty(user.getSurname())) {
			predicates.add(cb.equal(root.get(User_.surname), user.getSurname()));
		}

		if (BooleanUtils.isTrue(user.getHasNumber())) {
			predicates.add(cb.isNotEmpty(root.get(User_.phoneNumbers)));
		}

		criteria.where(predicates.toArray(new Predicate[predicates.size()]));

		return em.createQuery(criteria).getResultList();
	}

}
