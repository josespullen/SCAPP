package agile.scapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agile.scapp.dao.DbOperations;
import agile.scapp.pojo.UserPojo;

/**
 * Servlet implementation class UserLoginPage
 */
@WebServlet("/UserLoginPage")
public class UserLoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */

    public UserLoginPage() {
        // TODO Auto-generated constructor stub
    }
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DbOperations con=new DbOperations();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//System.out.println("Username:"+username);
		//System.out.println("Password:"+password);
		//con.connect(userName,password);
		con.getconnection();
		con.insertDb(username, password);
		con.closeConnection();
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DbOperations con=new DbOperations();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//System.out.println("Username:"+username);
		//System.out.println("Password:"+password);
		//con.connect(userName,password);
		//con.getconnection();
		con.loginUser(username,password,request,response);
		//con.insertDb(username, password);
		
	}
	public void userError(HttpServletRequest request,HttpServletResponse response)
	{
		DbOperations con=new DbOperations();
		//System.out.println("hii");
		try
		{
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserError.html");
		dispatcher.forward( request,response );
		con.closeConnection();
		
		}
		catch(Exception e)
		{
			
		}
		//con.closeConnection();
	}
	public void userSuccess(UserPojo userdetails,HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("FirstName",userdetails.getFirstName());
		request.setAttribute("Username",userdetails.getUserName());
		request.setAttribute("department",userdetails.getDepartment());
		System.out.println(userdetails.getType());
		System.out.println(userdetails.getDepartment());
		String type=userdetails.getType();
		//boolean i=type.equals("Subject Expert");
		//System.out.println(i);
		if(type.equals("Student"))
		{
		try
		{
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("studHome.jsp");
		dispatcher.forward( request,response );
		
		}
		catch(Exception e)
		{
			
		}
		}
		else if(type.equals("Subject Expert"))
		{
			try
			{
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("expHome.jsp");
			dispatcher.forward( request,response );
			
			}
			catch(Exception e)
			{
				
			}
		}
		else if(type.equals("MNC Rep"))
		{
			try
			{
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("mncHome.jsp");
			dispatcher.forward( request,response );
			
			}
			catch(Exception e)
			{
				
			}
		}
		else
		{
			try
			{
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("adminHome.jsp");
			dispatcher.forward( request,response );
			
			}
			catch(Exception e)
			{
				
			}
		}
	}

}
