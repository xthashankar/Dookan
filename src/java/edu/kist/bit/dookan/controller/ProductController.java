/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist.bit.dookan.controller;

import edu.kist.bit.dookan.entity.Category;
import edu.kist.bit.dookan.entity.Product;
import edu.kist.bit.dookan.entity.Sellingitem;
import edu.kist.bit.dookan.services.CategoryJpaController;
import edu.kist.bit.dookan.services.ProductJpaController;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shankar xtha
 */
@WebServlet(name = "ProductController", urlPatterns = {"/product"})
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("Dookanemf");
         String id=request.getParameter("id");
         
        ProductJpaController productJpaController = new ProductJpaController(emf);
        Product product= productJpaController.findProduct(Integer.parseInt(id));
        
        
        request.setAttribute("product", product);
       
        
         
         request.getRequestDispatcher("/WEB-INF/dashboardproduct.jsp").forward(request, response);
                 
                 
//         CategoryJpaController CategoryService =new CategoryJpaController(emf);
//         List<Category> categories=CategoryService.findCategoryEntities();
//         request.setAttribute("categories", categories);
       
         
         
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
private void dispatchRequest(HttpServletRequest request, HttpServletResponse response, String dashboardURL) throws ServletException, IOException {
        request.getRequestDispatcher(dashboardURL).forward(request, response);
    }

}

  
 