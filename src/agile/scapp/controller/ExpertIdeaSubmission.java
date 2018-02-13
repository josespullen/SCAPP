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
 * Servlet implementation class ExpertIdeaSubmission
 */
@WebServlet("/ExpertIdeaSubmission")
public class ExpertIdeaSubmission extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpertIdeaSubmission() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("hello");
		//String title=request.getParameter("title");
		IdeaPojo idea=new IdeaPojo();
		DbOperations db=new DbOperations();
		String username=request.getParameter("username");
		String firstname=request.getParameter("firstname");
		String dept=request.getParameter("dept");
		idea.setIid(Integer.parseInt(request.getParameter("id")));
		System.out.println(request.getParameter("title"));
		String des=request.getParameter("description");
		System.out.println(des);
		String rat=request.getParameter("rating");
		idea.setRating(Double.parseDouble(rat));
		System.out.println(rat);
		System.out.println(request.getParameter("feedback"));
		idea.setFeedback(request.getParameter("feedback"));
		idea.setUserId(request.getParameter("username"));
		db.insertRating(idea);
		request.setAttribute("FirstName",firstname);
		request.setAttribute("Username",username);
		request.setAttribute("department",dept);
		RequestDispatcher dispatcher = request.getRequestDispatcher("expHome.jsp");
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
