package com.ehealth.hmms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ehealth.hmms.dao.AuthenticationDao;
//import com.ehealth.hmms.dao.HibernatePersistence;
import com.ehealth.hmms.pojo.Users;

@Repository
@Transactional
public class AuthenticationDaoImpl implements AuthenticationDao {
	 @Autowired
	 private SessionFactory sessionFactory;

	public Users authenticate(Users user) throws Exception {

		
		Session session = this.sessionFactory.getCurrentSession();//HibernatePersistence.getSessionFactory().openSession();
		Users userResult=null;
	//	Transaction transaction = null;
		  try {
	//	 transaction = session.beginTransaction();
		 
//		Query query = session.createQuery("from User where loginName=:login and password=:password");
//		query.setString("login", user.getLoginName());
//		query.setString("password", user.getPassword());
//		List<User> users = query.list();

		 
		 Criteria criteria = session .createCriteria(Users.class);

         criteria.add(Restrictions.eq("loginName", user.getLoginName()));
         
         criteria.add(Restrictions.eq("password", user.getPassword()));
         
    //     criteria.setFetchMode("PayoutHeader", FetchMode.JOIN)
         List<Users> users  = criteria.list();//uniqueResult();

		
		if(users!=null && !users.isEmpty()) {
			userResult = users.get(0);
			
		}
	 } catch (HibernateException e) {
//         if (transaction!=null) { 
//        	 transaction.rollback();
//         }
        throw new HibernateException("Hibernate Exception : " +  e.getMessage() );
      } catch (Exception e) {
//    	  if (transaction!=null) { 
//         	 transaction.rollback();
//          }
         throw new Exception("Exception : " +  e.getMessage() );

      }
		  finally {
         session.close(); 
      }
		return userResult;
	}
	
//	
//	
//	public HospitalMaster getMonthlyData(Integer hospitalId) throws Exception {
//
//		
//		Session session = HibernatePersistence.getSessionFactory().openSession();
//		User userResult=null;
//		Transaction transaction = null;
//		  try {
//		 transaction = session.beginTransaction();
//		 Criteria criteria = session.createCriteria(User.class);
//       //  criteria.add(Restrictions.eq("loginName", user.getLoginName()));
//         
//        // criteria.add(Restrictions.eq("password", user.getPassword()));
//         
//    //     criteria.setFetchMode("PayoutHeader", FetchMode.JOIN)
//         List<User> users  = criteria.list();//uniqueResult();
//
//		 
////		 Criteria criteria = session.createCriteria(User.class);
////		             criteria.add(Restrictions.eq("loginName", user.getLoginName()));
////		             Employee employee = (Employee) criteria.uniqueResult();
//
//	//	List<User> users = query.list();
//		
//		if(users!=null && !users.isEmpty()) {
//			userResult = users.get(0);
//			
//		}
//	 } catch (HibernateException e) {
//         if (transaction!=null) { 
//        	 transaction.rollback();
//         }
//        throw new HibernateException("Hibernate Exception : " +  e.getMessage() );
//      } catch (Exception e) {
//    	  if (transaction!=null) { 
//         	 transaction.rollback();
//          }
//         throw new Exception("Exception : " +  e.getMessage() );
//
//      }
//		  finally {
//         session.close(); 
//      }
//		return userResult;
//	}
//
	
	
	
//	public Attendence markAttendence(Long tokenId) {
//		
//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		Attendence attendance = (Attendence) session.get(Attendence.class,tokenId);
//		if(attendance!=null) {
//			attendance.setAttendence(true);
//			session.saveOrUpdate(attendance);
//			session.getTransaction().commit();
//		}
//		return attendance;
//	}
//	public List<Attendence> getAttendence(){
//		 
//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		Query query = session.createQuery("from Attendence");// where attendence =:attendence");
//	//	query.setString("attendence", "t");
//		List<Attendence> attendanceList = query.list();
//		return attendanceList;
////	   for (Iterator iterator = attendences.iterator(); iterator.hasNext();){
////	        	 Attendence attendence = (Attendence) iterator.next(); 
////	            System.out.print("getId: " + attendence.getId()); 
////	            System.out.print(" getTokenId: " + attendence.getTokenId()); 
////	            System.out.println("  isAttendence: " + attendence.isAttendence()); 
////	         }
////		    
////		    Employee e1=new Employee();  
////		    e1.setId(115);  
////		    e1.setFirstName("sonoo");  
////		    e1.setLastName("jaiswal");  
////		      
////		    session.persist(e1);//persisting the object  
//		     // session.
//		   // t.commit();//transaction is committed  
//		    //session.close();  
//		      
//		    //System.out.println("successfully saved");  
//		    //return attendanceList;
//		      
//		}  
	}
	

