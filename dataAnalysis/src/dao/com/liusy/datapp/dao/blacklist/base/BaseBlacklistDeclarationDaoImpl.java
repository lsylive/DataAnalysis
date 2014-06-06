package com.liusy.datapp.dao.blacklist.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.blacklist.BlacklistDeclaration;

public abstract class BaseBlacklistDeclarationDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return BlacklistDeclaration.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public BlacklistDeclaration cast(Object object) {
		return (BlacklistDeclaration) object;
	}

	public BlacklistDeclaration get(Serializable id)  throws DAOException{
		return (BlacklistDeclaration) super.get(id);
	}

	public BlacklistDeclaration load(Serializable id)  throws DAOException{
		return (BlacklistDeclaration) super.load(id);
	}

	public Serializable save(BlacklistDeclaration blacklistDeclaration)  throws DAOException{
		return super.save(blacklistDeclaration);
	}

	public void saveOrUpdate(BlacklistDeclaration blacklistDeclaration)  throws DAOException{
		super.saveOrUpdate(blacklistDeclaration);
	}

	public void update(BlacklistDeclaration blacklistDeclaration)  throws DAOException{
		super.update(blacklistDeclaration);
	}

	public void delete(BlacklistDeclaration blacklistDeclaration)  throws DAOException{
		super.delete(blacklistDeclaration);
	}

	public void refresh(BlacklistDeclaration blacklistDeclaration)  throws DAOException{
		super.refresh(blacklistDeclaration);
	}
}
