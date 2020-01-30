package com.xworkz.medi.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.medi.dto.ForgotPwdDTO;
import com.xworkz.medi.entity.AppointmentEntity;
import com.xworkz.medi.entity.MediEntity;

@Repository
public class RegisterDAOImpl implements RegisterDAO {
	@Autowired
	private SessionFactory factory;

	public void SingUpDAOImpl(SessionFactory factory) {
		System.out.println("invoke" + this.getClass().getCanonicalName());
		System.out.println("SessionFactory method");
		this.factory = factory;
	}

	@Override
	public boolean saveService(MediEntity entity) {
		System.out.println("saving" + entity);
		Session session = null;
		try {
			session = factory.openSession();
			System.out.println("open session");
			Transaction transaction = session.beginTransaction();
			System.out.println("trasaction begin");
			String empid = entity.getEmpId();
			String email = entity.getEmail();
			System.out.println("SQL ");
			String hql = "from EmpDTO  where empId='" + empid + "' and email='" + email + "'";
			System.out.println(" Query:" + hql);
			Query query = session.createQuery(hql);
			Object obj = query.uniqueResult();
			System.out.println(obj);

			if (obj != null) {
				System.out.println("check exit or not");
				String hql2 = "from MediEntity  where empId ='" + empid + "' and email='" + email + "'";
				System.out.println("HQL Query:" + hql2);
				Query query2 = session.createQuery(hql2);
				Object obj2 = query2.uniqueResult();
				System.out.println(obj2);
				if (obj2 == null) {
					System.out.println("saving data into medi entity table");
					session.save(entity);
					transaction.commit();
				} else {
					System.out.println("user alredy exit");
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			System.out.println("closing resources");
			session.close();
		}
		return true;
	}

	@Override
	public MediEntity checkExistForLogin(MediEntity entity) {

		System.out.println("saving" + entity);
		Session session = null;
		try {
			session = factory.openSession();
			System.out.println("open session");
			Transaction transaction = session.beginTransaction();
			String pwd = entity.getPassword();
			String empId = entity.getEmpId();
			String hql = "from MediEntity where password='" + pwd + "' and empId='" + empId + "'";
			System.out.println("Query:" + hql);
			Query query = session.createQuery(hql);
			MediEntity mediEntity = (MediEntity) query.uniqueResult();
			System.out.println(mediEntity);
			if (mediEntity != null) {
				System.out.println("empId and password is matching");
				return mediEntity;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			System.out.println("closing resources");
			session.close();
		}
		return null;
	}

	@Override
	public boolean forgotPasswordDAO(ForgotPwdDTO pwdDTO) {
		System.out.println("invoked forgotPasswordDao");
		Session session = null;
		try {
			session = factory.openSession();
			System.out.println("open session");
			session.beginTransaction();
			String email = pwdDTO.getEmail();
			String hql = "from MediEntity where email=:email";
			System.out.println("Query:" + hql);
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			Object obj = query.uniqueResult();
			System.out.println(obj);
			if (obj != null) {
				String hql2 = "update MediEntity set password=:pwd where email=:email";
				Query query2 = session.createQuery(hql2);
				query2.setParameter("email", email);
				query2.setParameter("pwd", pwdDTO.getPassword());
				Integer integer = query2.executeUpdate();
				System.out.println("updated row is:" + integer);
				System.out.println("email is found");
				session.getTransaction().commit();
				return true;
			} else {
				System.out.println("Please, enter correct details");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());

		} finally {
			System.out.println("closing resources");
			session.close();
		}
		return false;
	}

	@Override
	public boolean updatePasswordDAO(ForgotPwdDTO pwdDTO) {
		System.out.println("invoked forgotPasswordDao");
		Session session = null;
		try {
			session = factory.openSession();
			System.out.println("open session");
			Transaction transaction = session.beginTransaction();
			String email = pwdDTO.getEmail();
			String pwd = pwdDTO.getPassword();
			System.out.println("hello");
			String hql = "update MediEntity set password='" + pwd + "'  where email='" + email + "'";
			System.out.println("hi");
			System.out.println("Query:" + hql);
			Query query = session.createQuery(hql);
			Object obj = query.executeUpdate();
			System.out.println(obj);
			if (obj != null) {
				String email1 = pwdDTO.getEmail();
				String pwd2 = pwdDTO.getNewPassword();
				String hql2 = "update MediEntity set password='" + pwd2 + "' where email='" + email1 + "'";
				Query query2 = session.createQuery(hql2);
				Object object = query2.executeUpdate();
				System.out.println(object);
				transaction.commit();
				return true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			System.out.println("closing resources");
			session.close();
		}
		return false;
	}

	@Override
	public boolean createAppiontment(AppointmentEntity appointmentEntity) {
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			Serializable id = session.save(appointmentEntity);
			session.getTransaction().commit();
			System.out.println(" saved details is:" + id);
			return true;
		} catch (Exception e) {
			System.err.println("exception occured:" + e.getMessage());
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}
	
	@Override
	public List<AppointmentEntity> searchDAO(String empId) {
		Session session=null;
		try {
			System.out.println("Session Begin");
			session=factory.openSession();
			session.beginTransaction();
		//	String empId=entity.getEmpId();
			String hql="from AppointmentEntity where empId=:empId";
			Query query=session.createQuery(hql);
			query.setParameter("empId", empId);
			System.out.println(empId);
			List<AppointmentEntity> list=query.list();
			return list;
		} catch (Exception e) {
			System.out.println("Exception in SearchDAOImpl");
			e.printStackTrace();
		}
		return null;
	}
}