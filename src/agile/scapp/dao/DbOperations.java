package agile.scapp.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agile.scapp.controller.UserLoginPage;
import agile.scapp.controller.UserSignUp;
import agile.scapp.pojo.IdeaPojo;
import agile.scapp.pojo.UserPojo;

public class DbOperations {

	Connection conn = null;
	Statement stmt = null;
	
	
	public void getconnection() {

		try {
			// db parameters
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:C:/Users/user/Desktop/SCAPP/SCAPP.sqlite";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");
			stmt = conn.createStatement();


		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void loginUser(String username,String password,HttpServletRequest request,HttpServletResponse response)
	{
		UserLoginPage user=new UserLoginPage();
		UserPojo userdetails= new UserPojo();
		try {
			// db parameters
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:C:/Users/user/Desktop/SCAPP/SCAPP.sqlite";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");
			stmt = conn.createStatement();
			String sql = "select * from userDetails where userName=? and password=?";
			System.out.println(conn);
			PreparedStatement preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()==false)
			{
				System.out.println("User does not exist");
				user.userError(request,response);
			}
			else
			{
				System.out.println("User Exist");
				userdetails.setUserName(rs.getString(1));
				userdetails.setPassword(rs.getString(2));
				userdetails.setFirstName(rs.getString(3));
				userdetails.setLastName(rs.getString(4));
				userdetails.setType(rs.getString(5));
				userdetails.setDepartment(rs.getString(6));
				userdetails.setEmail(rs.getString(7));
				user.userSuccess(userdetails,request,response);
			}

			
		}catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
		finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

	}
	
	public void signupUser(UserPojo user,HttpServletRequest request,HttpServletResponse response)
	{
		DbOperations db=new DbOperations();
		String sql;
		PreparedStatement preparedStatement;
		UserSignUp signup=new UserSignUp();
		int rowsaffected=0;
		try {
			// db parameters
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:C:/Users/user/Desktop/SCAPP/SCAPP.sqlite";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");
			stmt = conn.createStatement();
			sql = "select * from userDetails where username=?;";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserName());
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next())
			{
				System.out.println("error username already exist");
				db.closeConnection();
				signup.userError(request, response);
				
				
				
			}
			
			else
			{
				
				sql = "INSERT INTO userDetails(userName,password,firstname,lastname,type,department,emailid) VALUES (?,?,?,?,?,?,?);";
				System.out.println(conn);
				preparedStatement=conn.prepareStatement(sql);
				preparedStatement.setString(1, user.getUserName());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getFirstName());
				preparedStatement.setString(4, user.getLastName());
				preparedStatement.setString(5, user.getType());
				preparedStatement.setString(6, user.getDepartment());
				preparedStatement.setString(7, user.getEmail());
				rowsaffected=preparedStatement.executeUpdate();
				if(rowsaffected==1)
				{
					db.closeConnection();
					signup.userSuccess(request,response);
					
					
				}
			}
				
				
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
		finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

	}


	public void closeConnection()
	{
		
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		
	}
	public void insertStudentIdeaDetails(IdeaPojo idea)
	{
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		//Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
		int rowsaffected=0;
		Connection conn=null;
		String sql;
		PreparedStatement preparedStatement=null;

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:C:/Users/user/Desktop/SCAPP/SCAPP.sqlite";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");
			stmt = conn.createStatement();
		sql = "INSERT INTO ideaDetails(username,department,title,description,rated) VALUES (?,?,?,?,?);";
		System.out.println(conn);
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setString(1,idea.getUserId());
		preparedStatement.setString(2,idea.getDepartment());
		preparedStatement.setString(3, idea.getTitle());
		preparedStatement.setString(4, idea.getDescription());
		preparedStatement.setBoolean(5, idea.isRated());
		//preparedStatement.setDate(5,sqlDate);
		//preparedStatement=conn.prepareStatement(sql);
		rowsaffected=preparedStatement.executeUpdate();
		System.out.println(rowsaffected);
		} 
		catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
		finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

	}
	
	public IdeaPojo getStudentIdeaDetails(String dept)
	{
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		//Date date = new Date();
		System.out.println(dept);
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		IdeaPojo idea =new IdeaPojo();
		int rowsaffected=0;
		Connection conn=null;
		String sql;
		PreparedStatement preparedStatement=null;

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:C:/Users/user/Desktop/SCAPP/SCAPP.sqlite";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");
			stmt = conn.createStatement();
		//sql = "INSERT INTO ideaDetails(username,department,title,description,rated) VALUES (?,?,?,?,?);";
		sql="select * from ideadetails where department=? and rated=? limit 1";
		System.out.println(conn);
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setString(1,dept);
		preparedStatement.setBoolean(2,false);
		//preparedStatement.setDate(5,sqlDate);
		//preparedStatement=conn.prepareStatement(sql);
		ResultSet rs=preparedStatement.executeQuery();
		//System.out.println(rs);
		idea.setTitle(rs.getString(4));
		idea.setDescription(rs.getString(5));
		
		} 
		catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
		
		finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
           
        }
		 return idea;

	}
	
	public IdeaPojo getStudentIdea(String username)
	{
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		//Date date = new Date();
		//System.out.println(dept);
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		IdeaPojo idea =new IdeaPojo();
		int rowsaffected=0;
		Connection conn=null;
		String sql;
		PreparedStatement preparedStatement=null;

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:C:/Users/user/Desktop/SCAPP/SCAPP.sqlite";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");
			stmt = conn.createStatement();
		//sql = "INSERT INTO ideaDetails(username,department,title,description,rated) VALUES (?,?,?,?,?);";
		sql="select * from ideadetails where username=? limit 1";
		System.out.println(conn);
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setString(1,username);
		//preparedStatement.setBoolean(2,false);
		//preparedStatement.setDate(5,sqlDate);
		//preparedStatement=conn.prepareStatement(sql);
		ResultSet rs=preparedStatement.executeQuery();
		idea.setNextRow(rs.next());
		
		idea.setIid(rs.getInt(1));
		idea.setTitle(rs.getString(4));
		idea.setDescription(rs.getString(5));
		idea.setRating(rs.getDouble(6));
		idea.setReviewer(rs.getString(8));
		idea.setFeedback(rs.getString(9));
		
		} 
		catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
		
		finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
           
        }
		 return idea;

	}
	
	public void insertRating(IdeaPojo idea)
	{
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		//Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		//System.out.println(idea.getRating());
		int rowsaffected=0;
		Connection conn=null;
		String sql;
		PreparedStatement preparedStatement=null;

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:C:/Users/user/Desktop/SCAPP/SCAPP.sqlite";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");
			stmt = conn.createStatement();
		sql = "update  ideaDetails set rated=?,reviewer=?,rating=?,feedback=? where Iid=?;";
		System.out.println(conn);
		preparedStatement=conn.prepareStatement(sql);
		//preparedStatement.setInt(1,idea.getIid());
		preparedStatement.setBoolean(1,true);
		preparedStatement.setString(2, idea.getUserId());
		preparedStatement.setDouble(3, idea.getRating());
		preparedStatement.setString(4, idea.getFeedback());
		preparedStatement.setInt(5,idea.getIid());
		//preparedStatement.setDate(5,sqlDate);
		//preparedStatement=conn.prepareStatement(sql);
		rowsaffected=preparedStatement.executeUpdate();
		System.out.println(rowsaffected);
		} 
		catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
		finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

	}
	
	
	

}
