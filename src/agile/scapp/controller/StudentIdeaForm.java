package agile.scapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agile.scapp.dao.DbOperations;
import agile.scapp.pojo.IdeaPojo;

/**
 * Servlet implementation class StudentIdeaForm
 */
@WebServlet("/StudentIdeaForm")
public class StudentIdeaForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentIdeaForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DbOperations db=new DbOperations();
		//Date postedDate=new Date();
		IdeaPojo idea=new IdeaPojo();
		String username=request.getParameter("username");
		String firstname=request.getParameter("firstname");
		String dept=request.getParameter("dept");
		System.out.println("first"+firstname);
		System.out.println(username);
		idea.setUserId(username);
		idea.setTitle(request.getParameter("title"));
		idea.setDepartment(request.getParameter("dept"));
		idea.setDescription(request.getParameter("description"));
		idea.setRated(false);
		//idea.setPostedDate(postedDate);
		db.insertStudentIdeaDetails(idea);
		request.setAttribute("FirstName",firstname);
		request.setAttribute("Username",username);
		request.setAttribute("department",dept);
		RequestDispatcher dispatcher = request.getRequestDispatcher("studHome.jsp");
		dispatcher.forward( request,response );
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
