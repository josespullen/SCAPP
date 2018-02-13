<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="agile.scapp.dao.DbOperations"%>
<%@ page import="agile.scapp.pojo.IdeaPojo"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="includes/style.css">
<style>
		.rating {
		    float:left;
		}

		.rating:not(:checked) > input {
		    position:absolute;
		    top:-9999px;
		    clip:rect(0,0,0,0);
		}

		.rating:not(:checked) > label {
		    float:right;
		    width:1em;
		    padding:0 .1em;
		    overflow:hidden;
		    white-space:nowrap;
		    cursor:pointer;
		    font-size:200%;
		    line-height:1.2;
		    color:#ddd;
		    text-shadow:1px 1px #bbb, 2px 2px #666, .1em .1em .2em rgba(0,0,0,.5);
		}

		.rating:not(:checked) > label:before {
		    content: '★ ';
		}

		.rating > input:checked ~ label {
		    color: #f70;
		    text-shadow:1px 1px #c60, 2px 2px #940, .1em .1em .2em rgba(0,0,0,.5);
		}

		.rating:not(:checked) > label:hover,
		.rating:not(:checked) > label:hover ~ label {
		    color: gold;
		    text-shadow:1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
		}

		.rating > input:checked + label:hover,
		.rating > input:checked + label:hover ~ label,
		.rating > input:checked ~ label:hover,
		.rating > input:checked ~ label:hover ~ label,
		.rating > label:hover ~ input:checked ~ label {
		    color: #ea0;
		    text-shadow:1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
		}

		.rating > label:active {
		    position:relative;
		    top:2px;
		    left:2px;
		}
	</style>
</head>
<body>
	<%
		Statement stmt = null;	
		DbOperations db = new DbOperations();
		IdeaPojo idea = new IdeaPojo();
		String dept = (String) session.getAttribute("department");
		String username = (String) session.getAttribute("username");
		System.out.println(dept);
		//idea = db.getStudentIdea(user);
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
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
		sql="select i.iid,i.title,i.description,i.rating,i.feedback,i.reviewer,i.username,u.emailid from ideadetails i,userdetails u where i.department=? and i.rated=? and i.username=u.username and i.reviewer=?";
		System.out.println(conn);
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setString(1,dept);
		preparedStatement.setBoolean(2,true);
		preparedStatement.setString(3,username);
		//preparedStatement.setDate(5,sqlDate);
		//preparedStatement=conn.prepareStatement(sql);
		ResultSet rs=preparedStatement.executeQuery();
		idea.setIid(rs.getInt(1));
		idea.setTitle(rs.getString(2));
		idea.setDescription(rs.getString(3));
	
	%>
	<div class="container"
		style="float: left; width: 1900px; margin: 0cm 0cm 0cm -8cm;">
		<div class="col-md-2"></div>
		<div class="col-md-8" style="color: White;">
			<h1>Expert Rated Ideas</h1>
			<table class="table table-bordered" style="width: 100%;">
				<thead>
					<tr>
						<td>ID No.</td>
						<td>Title</td>
						<td>Description</td>
						<td>Rating</td>
						<td>Feedback</td>
						<td>Reviewer Id</td>
						<td>Student Id</td>
						<td>Student Email_Id</td>
					</tr>
				</thead>
				<%
				while(rs.next())
				{
			
				%>
				<tbody>
					<tr>
						<td><%=rs.getInt(1)%></td>
						<td><%=rs.getString(2)%></td>
						<td><%=rs.getString(3)%></td>
						<td><%=rs.getDouble(4)%></td>
						<td><%=rs.getString(5)%></td>
						<td><%=rs.getString(6)%></td>
						<td><%=rs.getString(7)%></td>
						<td><%=rs.getString(8)%></td>
					</tr>
					<%}
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
		%>
				</tbody>
			</table>
		</div>
		
	</div>
</body>
</html>