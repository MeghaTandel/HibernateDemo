
package com.gecp.servlets;

import com.gecp.beans.LoginBean;
import com.hibernateUtil.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class SelectServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int i = 1;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SelectServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            
            //Hibernate Specific Code
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();            
            String HQL = "FROM LoginBean";            
            Query query = session.createQuery(HQL);
            List results = query.list();
            session.getTransaction().commit();
            
            out.println("<h2>Users Information</h2>");
            out.println("<table border='1' cellpadding='5' cellspacing='0'>");
            out.println("<tr><th>ID</th><th>User Name</th><th>Password</th><th>Age</th><th colspan='2'>Action</th></tr>");
                       
            for (Iterator iterator = results.iterator(); iterator.hasNext();) {
                
                LoginBean lb = (LoginBean) iterator.next();
                out.println("<tr>"
                        + "<td>"+ i++ +"</td>"
                        + "<td>"+ lb.getUserName() +"</td>"
                        + "<td>"+ lb.getPassword() +"</td>"
                        + "<td>"+ lb.getAge() +"</td>"
                                + "<td><a href='editScreen?id=" + lb.getId() + "'>Edit</a></td>"
                                + "<td><a href='deleteServlet?id=" + lb.getId() + "'>Delete</a></td>"
                        + "</tr>");
                
                
            }
            out.println("</table>");            
            out.println("</center>");            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
