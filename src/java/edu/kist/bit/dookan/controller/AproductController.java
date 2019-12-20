/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist.bit.dookan.controller;

import edu.kist.bit.dookan.entity.Category;
import edu.kist.bit.dookan.entity.Product;
import static edu.kist.bit.dookan.entity.Product_.userdetailid;
import edu.kist.bit.dookan.entity.Userdetail;
import edu.kist.bit.dookan.services.CategoryJpaController;
import edu.kist.bit.dookan.services.ProductJpaController;
import edu.kist.bit.dookan.utils.FileUploadDTO;
import edu.kist.bit.dookan.utils.FileUploadUtil;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shankar xtha
 */
@WebServlet(name = "AproductController", urlPatterns = {"/Aproduct","/Addproduct","/updateProduct"})
@MultipartConfig
public class AproductController extends HttpServlet {

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
          String location ;
          CategoryJpaController jpaController = new CategoryJpaController(emf);
          ProductJpaController pjc = new ProductJpaController(emf);
          Product product = new Product();
        List<Category> catg = jpaController.findCategoryEntities();
        request.setAttribute("category", catg);
        String path = request.getServletPath();
        switch (path) {

            case "/Aproduct":
                
                location = "/WEB-INF/admin/adminproduct.jsp";
                break;
            case "/Addproduct":
                int userid = 0;
                HttpSession session = request.getSession(false);
                if(session != null){
                    Userdetail userdetail = (Userdetail) session.getAttribute("loggedInUser");
                    userid = userdetail.getId();
                }
                
                
                Category cat = new Category();
                Userdetail uid=new Userdetail();
                Product products = new Product();
                
                FileUploadDTO filedTO = FileUploadUtil.fileUpload(request, response, "photoUpload");
                String name=request.getParameter("name");
                String categoryid=request.getParameter("categoryid");
                
                String price =request.getParameter("price");
                String offer=request.getParameter("offer");
                String description=request.getParameter("description");
                String quantity=request.getParameter("quantity");
                String image = filedTO.getFileLocation();
            //    if(name.isEmpty() || categoryid.isEmpty() || price.isEmpty() || quantity.isEmpty() ||offer.isEmpty() || description.isEmpty() ||image.isEmpty())
                
                products.setName(name);
                cat.setId(Integer.parseInt(categoryid));
                products.setCategoryid(cat);
                products.setPrice(Float.parseFloat(price));
                products.setQuantity(Integer.parseInt(quantity));
                products.setOffer(offer);
                uid.setId(userid);
                products.setUserdetailid(uid);
               
                products.setDescription(description);
                products.setPhoto(image);
                pjc.create(products);
            System.out.println(name + cat + offer + price);
                location = "/WEB-INF/admin/adminproductdetail.jsp";
                break;
                
            case "/updateProduct":
                String id = request.getParameter("id");
                product = pjc.findProduct(Integer.parseInt(id)); 
                request.setAttribute("products",product);
                location="/WEB-INF/admin/adminproductedit.jsp";
                break;
                
            default:
                location = "/WEB-INF/admin/adminproduct.jsp";
                break;

        }

        dispatchRequest(request, response, location);
    }

    private void dispatchRequest(HttpServletRequest request, HttpServletResponse response, String signupURL) throws ServletException, IOException {
        request.getRequestDispatcher(signupURL).forward(request, response);
    }             
    
    private Product getFormData(HttpServletRequest request){
        int userid = 0;
        
                HttpSession session = request.getSession(false);
                if(session != null){
                    Userdetail userdetail = (Userdetail) session.getAttribute("loggedInUser");
                    userid = userdetail.getId();
                }
                Category cat =new Category();
                Userdetail uid=new Userdetail();
                Product products = new Product();
                
                
                String name=request.getParameter("name");
                String categoryid=request.getParameter("categoryid");
                
                String price =request.getParameter("price");
                String offer=request.getParameter("offer");
                String description=request.getParameter("description");
                String quantity=request.getParameter("quantity");
                
            //    if(name.isEmpty() || categoryid.isEmpty() || price.isEmpty() || quantity.isEmpty() ||offer.isEmpty() || description.isEmpty() ||image.isEmpty())
                
                products.setName(name);
                cat.setId(Integer.parseInt(categoryid));
                products.setCategoryid(cat);
                products.setPrice(Float.parseFloat(price));
                products.setQuantity(Integer.parseInt(quantity));
                products.setOffer(offer);
                uid.setId(userid);
                products.setUserdetailid(uid);
               
                
                products.setDescription(description);
                return products;
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