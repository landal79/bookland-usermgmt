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
import javax.transaction.Transactional;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.landal.bookland.usrmgmt.model.User;
import org.landal.bookland.usrmgmt.model.User_;

@Transactional
public abstract class UserService extends AbstractEntityRepository<User, Long> {


	/*
	public User findById(Long id) {
		return em.find(User.class, id);
	}
	*/

	public List<User> findByExample(User user) {
		CriteriaBuilder cb = entityManager().getCriteriaBuilder();
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

		return entityManager().createQuery(criteria).getResultList();
	}

}
