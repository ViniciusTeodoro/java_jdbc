package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conectionjdbc.SingleConnection;
import model.BeanUserPhone;
import model.Phone;
import model.User;

public class UserDao {
	
	private Connection connection;
	
	
	public UserDao() {
		connection = SingleConnection.getConnection();
	}
	
	public void save (User user) {
		try {
			String sql = "insert into users (name,email) values (?,?)";
			
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, user.getName());
			insert.setString(2, user.getEmail());
			insert.execute();			
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<User> index () throws Exception{
		List<User> users = new ArrayList<User>();
		try {
			String sql = "select * from users";
			
			PreparedStatement statement = connection.prepareStatement(sql);		
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				User user= new User();

				user.setId(result.getLong("id"));
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
				
				
				
				users.add(user);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return users;
	}
	
	public User search (Long id) throws Exception{
		User user = new User();
		try {
			String sql = "select * from users where id = " + id;
			
			PreparedStatement statement = connection.prepareStatement(sql);		
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
			
				user.setId(result.getLong("id"));
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return user;
	}
	
	public void update (User user) {
		try {
		

		String sql = "update users set name = ? where id =" + user.getId();
		
		PreparedStatement statement = connection.prepareStatement(sql);	
		statement.setString(1, user.getName());
		statement.execute();
		connection.commit();
		
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			e.printStackTrace();
		// TODO: handle exception
		}
	}
	
	public void remove (Long id) {
		try {
	
		String sql = "delete from users where id =" + id;
		
		PreparedStatement statement = connection.prepareStatement(sql);	
		statement.execute();
		connection.commit();
		
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			e.printStackTrace();
		// TODO: handle exception
		}
	}
	
	public void savePhone (Phone phone) {
		try {
			String sql = "insert into phones (number, type, user_id) values (?,?,?);";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, phone.getNumber());
			statement.setString(2, phone.getType());
			statement.setLong(3, phone.getUser_id());
			statement.execute();			
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	public List<BeanUserPhone> listUserPhone (Long user_id) {
	
			

		List<BeanUserPhone> beanUserPhone = new ArrayList<BeanUserPhone>();
		
		String sql = " select name, number, email from phones as phone ";
		sql += " inner join users as user1 ";
		sql += " on phone.user_id = user1.id ";
		sql += " where user1.id = " + user_id;
		try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		while (result.next()) {
			BeanUserPhone userPhone = new BeanUserPhone();
			
			userPhone.setEmail(result.getString("email"));
			userPhone.setName(result.getString("name"));
			userPhone.setNumber(result.getString("number"));
			
			beanUserPhone.add(userPhone);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		return beanUserPhone;
	}
	
	public void removePhonesAndUsers(Long user_id) {
		String sqlPhone = "delete from phones where user_id=" + user_id;
		String sqlUser = "delete from users where id = " + user_id;
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlPhone);
			statement.executeUpdate();
			connection.commit();
			
			statement = connection.prepareStatement(sqlUser);
			statement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			// TODO: handle exception
		}
	}
	
}
