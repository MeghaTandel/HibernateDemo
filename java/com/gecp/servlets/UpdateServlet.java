package com.gecp.servlets;

import com.gecp.beans.LoginBean;
import com.hibernateUtil.HibernateUtil;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UpdateServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            int age = Integer.parseInt(request.getParameter("age"));
            int id = Integer.parseInt(request.getParameter("id"));
            LoginBean lb = new LoginBean(id, username, password, age);
            
            
            //Option 1 By HQL Start
            String HQL = "UPDATE LoginBean l "
                    + " SET l.userName = ? , l.password = ? , l.age = ? "
                    + " WHERE l.id = ?";
            
            Session session = HibernateUtil.getSessionFactory().openSession();         
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery(HQL);
            query.setParameter(0, lb.getUserName());
            query.setParameter(1, lb.getPassword());
            query.setParameter(2, lb.getAge());
            query.setParameter(3, lb.getId());
            int rowCount = query.executeUpdate();
            tx.commit();
            session.close();
            if(rowCount > 0){
                out.println("Give user details updated successfully");
            }
            //option 1 By HQL End
            
            
            /*            
            //Option 2 By Object Start
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();            
            session.update(lb);
            tx.commit();
            session.close();
            //Option 2 By Object End
            */
            
            RequestDispatcher rd = request.getRequestDispatcher("select");
            rd.include(request, response);
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
