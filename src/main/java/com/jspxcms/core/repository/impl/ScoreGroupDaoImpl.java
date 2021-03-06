package com.jspxcms.core.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.QueryHints;

import com.jspxcms.core.domain.ScoreGroup;
import com.jspxcms.core.domaindsl.QScoreGroup;
import com.jspxcms.core.repository.ScoreGroupDaoPlus;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

public class ScoreGroupDaoImpl implements ScoreGroupDaoPlus {

	public ScoreGroup findTopOne(Integer siteId) {
		JPAQuery query = new JPAQuery(this.em);
		query.setHint(QueryHints.HINT_CACHEABLE, true);
		QScoreGroup bean = QScoreGroup.scoreGroup;
		query.from(bean);
		BooleanBuilder exp = new BooleanBuilder();
		if (siteId != null) {
			exp = exp.and(bean.site.id.eq(siteId));
		}
		query.where(exp);
		query.orderBy(bean.seq.asc(), bean.id.asc());
		query.limit(1);
		List<ScoreGroup> list = query.list(bean);
		return list.isEmpty() ? null : list.get(0);
	}

	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
}
