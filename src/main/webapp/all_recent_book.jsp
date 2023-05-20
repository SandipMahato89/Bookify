<%@page import="com.entity.User"%>
<%@page import="com.entity.BookDtls"%>
<%@page import="java.util.List"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Recent Book</title>
<%@include file="all_component/allCss.jsp"%>
<style type="text/css">
.crd-ho:hover {
	background-color: #fdf2f8;
}
</style>
</head>
<body>
	<%@include file="all_component/navbar.jsp"%>
	
	<%
	User u = (User) session.getAttribute("userobj");
	%>
	
	<div class="container-fluid">
		<div class="row p-3">
			
				<%
				BookDAOImpl dao2 = new BookDAOImpl(DBConnect.getConn());
				List<BookDtls> list = dao2.getAllRecentBook();
				for (BookDtls b : list) {
				%>
				<div class="col-md-3">
					<div class="card crd-ho mt-2">
						<div class="card-body text-center">
							<img alt="" src="book/<%=b.getPhotoName()%>"
								style="width: 100px; height: 150px">
							<p><%=b.getBookName()%></p>
							<p><%=b.getAuthor()%></p>
							<p>
								<%
								if (b.getBookCategory().equals("Old")) {
								%>
								Category:
								<%=b.getBookCategory()%></p>
							<div class="row">
								<a href="view_books.jsp?bid=<%=b.getBookId()%>" class="btn btn-success btn-sm ml-5">View details</a><a
									href="" class="btn btn-danger btn-sm ml-1"><i
									class="fa-solid fa-indian-rupee-sign"></i><%=b.getPrice()%></a>
							</div>
							<%
							} else {
							%>
							Category:
							<%=b.getBookCategory()%></p>
							<div class="row">
							
							
								<%
							if (u == null) {
							%>
							<a href="login.jsp" class="btn btn-danger btn-sm ml-1"><i
								class="fa-solid fa-cart-plus"></i> Add cart</a>

							<%
							} else {
							%>
							<a href="cart?bid=<%=b.getBookId()%>&&uid=<%=u.getId()%>"
								class="btn btn-danger btn-sm ml-1"><i
								class="fa-solid fa-cart-plus"></i> Add cart</a>
							<%
							}
							%>
									
									
									
									<a href="view_books.jsp?bid=<%=b.getBookId()%>"
									class="btn btn-success btn-sm ml-1">View details</a><a href=""
									class="btn btn-danger btn-sm ml-1"><i
									class="fa-solid fa-indian-rupee-sign"></i><%=b.getPrice()%></a>
							</div>
							<%
							}
							%>
						</div>
					</div>
				</div>
				<%
				}
				%>
			
		</div>
	</div>


</body>
</html>