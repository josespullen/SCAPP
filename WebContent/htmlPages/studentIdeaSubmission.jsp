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
	float: left;
}

.rating:not (:checked ) >input {
	position: absolute;
	top: -9999px;
	clip: rect(0, 0, 0, 0);
}

.rating:not (:checked ) >label {
	float: right;
	width: 1em;
	padding: 0 .1em;
	overflow: hidden;
	white-space: nowrap;
	cursor: pointer;
	font-size: 200%;
	line-height: 1.2;
	color: #ddd;
	text-shadow: 1px 1px #bbb, 2px 2px #666, .1em .1em .2em
		rgba(0, 0, 0, .5);
}

.rating:not (:checked ) >label:before {
	content: '★ ';
}

.rating>input:checked ~ label {
	color: #f70;
	text-shadow: 1px 1px #c60, 2px 2px #940, .1em .1em .2em
		rgba(0, 0, 0, .5);
}

.rating:not (:checked ) >label:hover, .rating:not (:checked ) >label:hover 
	~ label {
	color: gold;
	text-shadow: 1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em
		rgba(0, 0, 0, .5);
}

.rating>input:checked+label:hover, .rating>input:checked+label:hover ~
	label, .rating>input:checked ~ label:hover, .rating>input:checked ~
	label:hover ~ label, .rating>label:hover ~ input:checked ~ label {
	color: #ea0;
	text-shadow: 1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em
		rgba(0, 0, 0, .5);
}

.rating>label:active {
	position: relative;
	top: 2px;
	left: 2px;
}
</style>
</head>
<body>
	<%
		Statement stmt = null;	
		DbOperations db = new DbOperations();
		IdeaPojo idea = new IdeaPojo();
		String user = (String) session.getAttribute("username");
		System.out.println(user);
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
		sql="select * from ideadetails where username=?";
		System.out.println(conn);
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setString(1,user);
		//preparedStatement.setBoolean(2,false);
		//preparedStatement.setDate(5,sqlDate);
		//preparedStatement=conn.prepareStatement(sql);
		ResultSet rs=preparedStatement.executeQuery();
	
	%>
	<div class="container"
		style="float: left; width: 1900px; margin: 0cm 0cm 0cm -8cm;">
		<div class="col-md-2"></div>
		<div class="col-md-8" style="color: White;">
			<h1>Student Ideas</h1>
			<table class="table table-bordered" style="width: 100%;">
				<thead>
					<tr>
						<td>ID No.</td>
						<td>Title</td>
						<td>Description</td>
						<td>Rating</td>
						<td>Reviewer Id</td>
						<td>Feedback</td>
						<td><button type="button" class="btn btn-info btn-sm"
								data-toggle="modal" data-target="#myModal">Submit An
								Idea</button></td>
					</tr>
				</thead>
				<%
				while(rs.next())
				{
			
				%>
				<tbody>
					<tr>
						<td><%=rs.getInt(1)%></td>
						<td><%=rs.getString(4)%></td>
						<td><%=rs.getString(5)%></td>
						<td><%=rs.getDouble(7)%></td>
						<td><%=rs.getString(8)%></td>
						<td><%=rs.getString(9)%></td>
						<td><button type="button" class="btn btn-info btn-sm"
								data-toggle="modal" data-target="#myModal">Submit An
								Idea</button></td>
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
		<div class="col-md-2"></div>
		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog"
			style="color: black;">
			<div class="modal-dialog modal-lg">
				<!-- Modal content-->
				<form method="get" action="StudentIdeaForm">
				<input type="hidden" id=UserName name="username" value=<%= session.getAttribute("username")%>>
				<input type="hidden"  name="dept" value=<%= session.getAttribute("department")%>>
				<input type="hidden"  name="firstname" value=<%= session.getAttribute("firstname")%>>
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Submit An Idea</h4>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-sm-4">
							<br />

							<div class="form-group">
								<!-- Title -->
								<label for="Title" class="control-label">Title</label> <input
									type="text" class="form-control" id="title" name="title"
									placeholder="Title">
							</div>
							<!-- 
		                    <div class="form-group"> Description
		                        <label for="DescriptionDescription" class="control-label">Description</label>
		                        <input type="text" class="form-control" id="Department" name="DescriptionDescription" placeholder="Description">
		                    </div>  -->
							<div class="col-sm-10">
								<br />
								<div class="input-group">
									<label for="" class="control-label">Description</label>
									<textarea class="form-control custom-control" rows="8"
										style="resize: none" id="Department" name="description"
										placeholder="Description"></textarea>
								</div>
							</div>


						</div>

						<div class="col-md-1"></div>
					</div>
					<div class="row">
						<button type="submit" class="btn btn-success">Submit</button>
					</div>
					<br />
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>