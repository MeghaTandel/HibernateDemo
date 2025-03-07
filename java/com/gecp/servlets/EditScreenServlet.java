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

public class EditScreenServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int userId = Integer.parseInt(request.getParameter("id"));
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();            
        String HQL = "FROM LoginBean l WHERE l.id = ? ";
        Query query = session.createQuery(HQL);
        query.setParameter(0, userId);
        List results = query.list();
        session.getTransaction().commit();
        
        LoginBean lb = null;
        for (Iterator iterator = results.iterator(); iterator.hasNext();)
        {
            lb = (LoginBean) iterator.next();
        }

        out.println("<html><head><title>Edit User</title></head><body>");
        out.println("<center>");
        out.println("<h1>Edit User</h1>");
        out.println("<form method='post' action='updateServlet'>");
        out.println("<input type='hidden' name='id' value='" + lb.getId() + "'/><br/>");
        out.println("Name: <input type='text' name='username' value='" + lb.getUserName() + "'/><br/><br/>");
        out.println("Password: <input type='text' name='password' value='" + lb.getPassword() + "'/><br/><br/>");
        out.println("Age: <input type='text' name='age' value='" + lb.getAge() + "'/><br/><br/>");
        out.println("<input type='submit' value='Update'/>");
        out.println("</form>");
        out.println("</center>");
        out.println("</body></html>");
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
