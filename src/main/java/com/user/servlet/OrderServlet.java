package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.CheckedOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.DAO.BookOrderImpl;
import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
import com.entity.Book_Order;
import com.entity.Cart;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
	
		try {
			int id= Integer.parseInt(req.getParameter("id"));
			
			String name=req.getParameter("username");
			String email=req.getParameter("email");
			String phno=req.getParameter("phno");
			String address=req.getParameter("address");
			String landmark=req.getParameter("landmark");
			String city=req.getParameter("city");
			String state=req.getParameter("state");
			String pincode=req.getParameter("pincode");
			String paymentType=req.getParameter("payment");
			
			String fullAdd = address+","+landmark+","+city+","+state+","+pincode;
			
			//System.out.println(name+" "+email+" "+phno+" "+fullAdd+" "+paymentType);
			
			CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());
			List<Cart> blist = dao.getBookByUserId(id);
			
			if(blist.isEmpty()) {
				
				session.setAttribute("failedMsg", "Add items to cart");
				resp.sendRedirect("checkout.jsp");
				
			}else {
				BookOrderImpl dao2 = new BookOrderImpl(DBConnect.getConn());
				//int i = dao2.getOrderNo();
				
				Book_Order o = null;
				ArrayList<Book_Order> orderList = new ArrayList<Book_Order>();
				Random r = new Random();
				
				for(Cart c:blist) {
					o = new Book_Order();
					o.setOrderId("BOOK_ORD_00"+r.nextInt(1000));
					o.setUserName(name);
					o.setEmail(email);
					o.setPhno(phno);
					o.setFullAdd(fullAdd);
					o.setBookName(c.getBookName());
					o.setAuthor(c.getAuthor());
					o.setPrice(c.getPrice()+"");
					o.setPaymentType(paymentType);
					orderList.add(o);
					//i++;
				}
				
				if("noselect".equals(paymentType)) {
					session.setAttribute("failedMsg", "Select Payment Method");
					resp.sendRedirect("checkout.jsp");
				}else {
					boolean f = dao2.saveOrder(orderList);
					
					if(f) {
						resp.sendRedirect("order_success.jsp");
						
					}
					else {
						session.setAttribute("failedMsg", "Select Payment Method");
						resp.sendRedirect("checkout.jsp");
						//System.out.println("Failed Order");
					}
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
