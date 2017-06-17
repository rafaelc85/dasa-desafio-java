package com.dasa.dao;

import br.com.dbserver.dao.AbstractDao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dasa.domain.Participacao;

@Repository("participacaoDao")
public class ParticipacaoDaoImpl extends AbstractDao<Integer, Participacao> implements ParticipacaoDao {

        @Override
	public Participacao findById(int id) {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("id", id));
            return (Participacao) criteria.uniqueResult();
	}

        @Override
	public void saveParticipacao(Participacao participacao) {
		persist(participacao);
	}

        @Override
	public void deleteParticipacaoById(int id) {
		Query query = getSession().createSQLQuery("delete from participacao where id = " + id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
        @Override
	public List<Participacao> findAllParticipacoes() {
		Criteria criteria = createEntityCriteria();
		return (List<Participacao>) criteria.list();
	}
}
