package java_jdbc.java_jdbc;

import java.util.List;

import org.junit.Test;

import conectionjdbc.SingleConnection;
import dao.UserDao;
import model.BeanUserPhone;
import model.Phone;
import model.User;

public class TestDBJdbc {
	
    @Test
	public void initDB() {
		SingleConnection.getConnection();
		
		UserDao userDao = new UserDao();
		User user = new User();
		
		user.setName("Henrique");
		user.setEmail("henrique@gmail.com");
		
		userDao.save(user);
	}
    @Test
    public void consultAll() {
    	UserDao userDao = new UserDao();
    	try {
			List<User> list = userDao.index();		
			
			for (User user : list) {
				System.out.println(user);
				System.out.println("--------------------------------------------");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    }
    
    @Test
    public void search() {
    	UserDao userDao = new UserDao();
    	try {
			User user = userDao.search(5L);		
			
				System.out.println(user);
				System.out.println("--------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    }
    
    @Test
    public void update() {
    	UserDao userDao = new UserDao();
    	try {
			User user = userDao.search(5L);
			user.setName("Elon");
			userDao.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    }
    
    @Test
    public void remove() {
    	UserDao userDao = new UserDao();
    	try {
			userDao.remove(5L);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    }
    
    @Test
    public void insertPhone() {
    	
    	try {
			Phone phone = new Phone();
			phone.setNumber("(87) 96666-6666");
			phone.setType("fix");
			phone.setUser_id(4L);
			
			
			UserDao userdao = new UserDao();
			userdao.savePhone(phone);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    }
    
    @Test
    public void listPhonesUsers() {
    	
    	UserDao userdao = new UserDao();
    	List<BeanUserPhone> beanUserPhones = userdao.listUserPhone(4L);
    	
    	
    	try {
			for(BeanUserPhone beanUserPhone : beanUserPhones ) {
				System.out.println(beanUserPhone);
				System.out.println("------------------------------------");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    }
    
    @Test 
    public void deleteUserPhoneCascade() {
    	UserDao userdao = new UserDao();
    	
    	userdao.removePhonesAndUsers(4L);
    }
    
    

}
