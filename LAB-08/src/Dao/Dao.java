package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Dto.Dto;

public class Dao {
	DataSource dataSource;
	
	public Dao(){
		try {
			Context context = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Dto> list(){
		ArrayList<Dto> dtos = new ArrayList<Dto>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "codelab10", "oracle_11g");
			
			String query = "SELECT * FROM LAB08 ORDER BY bGROUP DESC, bSTEP ASC";
			
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			//DB에 있는 내용을 ResultSet에 저장
			while(resultSet.next()){
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				Dto dto = new Dto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return dtos;
	}
	public Dto contentView(String strId){
		upHit(strId);
		
		Dto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "codelab10", "oracle_11g");
			
			String query = "SELECT * FROM LAB08 WHERE bId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strId));
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new Dto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	private void upHit(String bId){
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "codelab10", "oracle_11g");
			
			String query = "UPDATE lab08 SET bHit=bHit+1 WHERE bId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bId);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public void delete(String bId){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "codelab10", "oracle_11g");
			String query = "DELETE FROM lab08 WHERE bId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(bId));
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "codelab10","oracle_11g");
			String query="UPDATE LAB08 SET bName=?, bTitle=?, bContent=? WHERE bId=?";
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setString(4, bId);
			
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent){
		
		System.out.println("bGroup: " + bGroup);
		System.out.println("bStep: " + bStep);
		replyShape(bGroup, bStep);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "codelab10","oracle_11g");
			
			String query = "INSERT INTO lab08(bId, bName, bTitle, bContent, bGroup, bStep, bIndent) VALUES(lab08_seq.nextval, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bGroup));
			preparedStatement.setInt(5, Integer.parseInt(bStep)+1);
			preparedStatement.setInt(6, Integer.parseInt(bIndent)+1);
			
			preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void replyShape(String bGroup, String bStep){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "codelab10","oracle_11g");
			String query = "UPDATE lab08 SET bStep = bStep +1 WHERE bGroup = ? and bStep > ?";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(bGroup));
			preparedStatement.setInt(2, Integer.parseInt(bStep));
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public Dto reply_view(String strId){
			Dto dto = null;
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "codelab10", "oracle_11g");
				String query = "SELECT * FROM LAB08 WHERE bId=?";
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(strId));
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()){
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString("bName");
					String bTitle = resultSet.getString("bTitle");
					String bContent = resultSet.getString("bContent");
					Timestamp bDate = resultSet.getTimestamp("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					
					System.out.println("bHit: " + bHit);
					System.out.println("bGroup: " + bGroup);
					System.out.println("bStep: " + bStep);
					System.out.println("bIndent: " + bIndent);
					
					dto = new Dto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(connection != null) connection.close();
					if(preparedStatement != null) preparedStatement.close();
					if(resultSet != null) resultSet.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return dto;
		}
	public void write(String bName, String bTitle, String bContent){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "codelab10", "oracle_11g");
				String query = "INSERT INTO LAB08(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) VALUES(lab08_seq.nextval, ?, ?, ?, 0, lab08_seq.currval, 0, 0)";
				
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				
				preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}

