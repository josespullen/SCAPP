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
 * Servlet implementation class UserSignUp
 */
@WebServlet("/UserSignUp")
public class UserSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		DbOperations db=new DbOperations();
		UserPojo user=new UserPojo();
		/*System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		System.out.println(request.getParameter("firstname"));
		System.out.println(request.getParameter("lastname"));
		System.out.println(request.getParameter("dept"));
		System.out.println(request.getParameter("type"));
		System.out.println(request.getParameter("email"));
		*/
		user.setUserName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		String first=request.getParameter("firstname");
		first=first.substring(0, 1).toUpperCase()+first.substring(1);
		user.setFirstName(first);
		user.setLastName(request.getParameter("lastname"));
		user.setDepartment(request.getParameter("dept"));
		user.setType(request.getParameter("type"));
		user.setEmail(request.getParameter("email"));
		//db.getconnection();
		db.signupUser(user,request,response);
		
		
	}
	public void userSuccess(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward( request,response );
		
		}
		catch(Exception e)
		{
			
		}
	}
	public void userError(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
		RequestDispatcher dispatcher = request.getRequestDispatcher("userIdError.html");
		dispatcher.forward( request,response );
		
		}
		catch(Exception e)
		{
			
		}
	}

}
