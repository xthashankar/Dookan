/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist.bit.dookan.filter;


import edu.kist.bit.dookan.entity.Userdetail;
import edu.kist.bit.dookan.services.UserdetailJpaController;
import edu.kist.bit.dookan.services.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sayara
 */
@WebFilter(filterName = "Authentication_filter",
        urlPatterns = {"/*"},
        initParams = @WebInitParam(name = "avoids-urls", value = "/login.jsp,"
                + ".css,"
                + ".js,"
                + "/images/,"
                +"/font,"
                + "/index.jsp,"
                +"/product,"
                +"/register,"
                +"/signup,"
                +"/uploads,"
                +"/Aproductdetail,"
                +"/Aproductedit,"
                 +"/Addproduct,"
                +"/Aorder,"
                +"/Aseller,"
                +"/Userdetail,"
                +"/cart,"
                +"/Aproduct"))

public class AuthenticationFilter implements Filter {

    private static final boolean debug = false;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private ArrayList<String> urlList;

    public AuthenticationFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("Authentication_filter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a filter that implements setParameter() on a request
        // wrapper could set parameters on the request before passing it on
        // to the filter chain.
        /*
	String [] valsOne = {"val1a", "val1b"};
	String [] valsTwo = {"val2a", "val2b", "val2c"};
	request.setParameter("name1", valsOne);
	request.setParameter("nameTwo", valsTwo);
         */
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("Authentication_filter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<p><strong>This has been appended by an intrusive filter.</strong></p>");
	
	respOut.println("<p>Params (after the filter chain):<br>");
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
		String name = (String)en.nextElement();
		String values[] = request.getParameterValues(name);
		int n = values.length;
		StringBuffer buf = new StringBuffer();
		buf.append(name);
		buf.append("=");
		for(int i=0; i < n; i++) {
		    buf.append(values[i]);
		    if (i < n-1)
			buf.append(",");
		}
		log(buf.toString());
		respOut.println(buf.toString() + "<br>");
	}
        respOut.println("</p>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("Authentication_filter:doFilter()");
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getServletPath();
        boolean allowedRequest = false;
        for(String s:urlList)
        {
            if(url.contains(s)){
                allowedRequest=true;
                break;
            }
        }

        Throwable problem = null;

        if (!allowedRequest) {
            HttpSession session = req.getSession(false);
            if ((null == session|| session.getAttribute("loggedInUser")==null) && url.equalsIgnoreCase("/dashboard")) {
                if (req.getMethod().equalsIgnoreCase("POST")) {
                    if (checkLogin(req, res)) {
                        HttpSession currentSession =req.getSession(false);
                        Userdetail u=(Userdetail) currentSession.getAttribute("loggedInUser");
                        if("admin".equalsIgnoreCase(u.getRole()))
                        {
                            req.getRequestDispatcher("/WEB-INF/admin/admindashboard.jsp").forward(request,response);
                        }else if("seller".equalsIgnoreCase(u.getRole())){
                            req.getRequestDispatcher("/WEB-INF/admin/admindashboard.jsp").forward(request,response);
                        }
                        else{
                             chain.doFilter(request, response);
                        }
                       
                    } else {
                        //TODO send error msg Invalid Username or Password
                        req.setAttribute("errorMsg","Invalid email or password");
                        req.getRequestDispatcher("login.jsp").forward(request, response);
//                        res.sendRedirect("login.jsp");
                        return;
                    }
                } else {
                    res.sendRedirect("login.jsp");
                    return;
                }
            } else if (null == session|| session.getAttribute("loggedInUser")==null) {
                res.sendRedirect("index.jsp");
                return;
            }else {
                HttpSession currentSession = req.getSession(false);
                Userdetail u = (Userdetail) currentSession.getAttribute("loggedInUser");
                if ("admin".equalsIgnoreCase(u.getRole())) {
                    req.getRequestDispatcher("/WEB-INF/admin/admindashboard.jsp").forward(request, response);
                }else
                    req.getRequestDispatcher("/WEB-INF/admin/admindashboard.jsp").forward(request, response);
            }

        }

        chain.doFilter(request, response);
        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.

        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        String urls = filterConfig.getInitParameter("avoids-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");
        urlList = new ArrayList<>();

        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());
        }
        if (filterConfig != null) {
            if (debug) {
                log("Authentication_filter: Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("Authentication_filter()");
        }
        StringBuffer sb = new StringBuffer("Authentication_filter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());

    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    private boolean checkLogin(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        EntityManagerFactory emf = (EntityManagerFactory) req.getServletContext().getAttribute("Dookanemf");
        boolean isUserLoggedIn = false;
        Userdetail user = null;

        UserdetailJpaController userJpaController = new UserdetailJpaController(emf);
        try {
            user = userJpaController.checkLogin(req.getParameter("email"));
        } catch (NonexistentEntityException  ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user != null) {
           // if (BCrypt.checkpw(req.getParameter("password"), user.getPassword())) {
             
           if(req.getParameter("password").equals(user.getPassword())){
                isUserLoggedIn = true;
                HttpSession session = req.getSession();
                session.setAttribute("loggedInUser", user);
            }
        }
        return isUserLoggedIn;
    }
}
