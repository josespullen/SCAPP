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
		sql="select * from ideadetails where department=? and rated=? limit 1";
		System.out.println(conn);
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setString(1,dept);
		preparedStatement.setBoolean(2,false);
		//preparedStatement.setDate(5,sqlDate);
		//preparedStatement=conn.prepareStatement(sql);
		ResultSet rs=preparedStatement.executeQuery();
		idea.setIid(rs.getInt(1));
		idea.setTitle(rs.getString(4));
		idea.setDescription(rs.getString(5));
	
	%>
	<div class="container"
		style="float: left; width: 1900px; margin: 0cm 0cm 0cm -8cm;">
		<div class="col-md-2"></div>
		<div class="col-md-8" style="color: White;">
			<h1>Expert Idea Review</h1>
			<table class="table table-bordered" style="width: 100%;">
				<thead>
					<tr>
						<td>ID No.</td>
						<td>Title</td>
						<td>Description</td>
						<th></th>
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

						<td><button type="button" class="btn btn-info btn-sm"
								data-toggle="modal" data-target="#myModal">Rate An
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
		<form action="ExpertIdeaSubmission" method="get">
				<input type="hidden" id=UserName name="username" value=<%= session.getAttribute("username")%>>
				<input type="hidden"  name="dept" value=<%= session.getAttribute("department")%>>
				<input type="hidden"  name="firstname" value=<%= session.getAttribute("firstname")%>>
		
				<div class="modal fade" id="myModal" role="dialog" style="color: black;">
			<div class="modal-dialog modal-lg">
			<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Send Feedback</h4>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-sm-4"><br/>
							<div class="form-group"> <!-- ID No. -->
		                        <label for="id" class="control-label">ID No.</label>
		                        <input type="text" class="form-control" id="id" name="id" placeholder="ID No." value="<%=idea.getIid()%>" readonly>
		                    </div>  
		                    <div class="form-group"> <!-- Title -->
		                        <label for="Title" class="control-label">Title</label>
		                        <input type="text" class="form-control" id="Title" name="title" placeholder="Title" value="<%=idea.getTitle()%>" readonly>
		                    </div>                  
		                   
		                    <div class="form-group"> <!-- Description -->
		                        <label for="DescriptionDescription" class="control-label">Description</label>
		                        <input type="text" class="form-control" id="Department" name="description" placeholder="Description" value="<%=idea.getDescription()%>" readonly>
		                    </div>
		                   <!--  <fieldset class="rating"><h3>Rating</h3>
							    <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="Rocks!">5 stars</label>
							    <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="Pretty good">4 stars</label>
							    <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="Meh">3 stars</label>
							    <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="Kinda bad">2 stars</label>
							    <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="Sucks big time">1 star</label>
							</fieldset>
   -->
   					 <div class="form-group"> <!-- Description -->
		                        <label for="DescriptionDescription" class="control-label">Rating</label>
		                        <input type="text" class="form-control" name="rating" placeholder="Rating">
		                    </div>
						
						</div>
	                    <div class="col-sm-5"><br/>
							<div class="input-group">
								<label for="" class="control-label">Feedback</label>
							    <textarea name="feedback" class="form-control custom-control" rows="8" style="resize:none" ></textarea>
							</div>
						</div>
						<div class="col-md-1"></div>
					</div>
					<div class="row">
						<button type="submit" class="btn btn-success">Submit</button>
					</div><br/>
				</div>
			</div>
		</div>
		</form>
	</div>
</body>
</html>