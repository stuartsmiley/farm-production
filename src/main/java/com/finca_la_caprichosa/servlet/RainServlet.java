package com.finca_la_caprichosa.servlet;

import com.finca_la_caprichosa.service.GraphService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Do a little dispatching.
 */
public class RainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private GraphService graphService;

    /**
     * Default constructor.
     */
    public RainServlet() {
        // empty
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/graphs/rain.jsp");
        dispatcher.forward(request, response);
    }

}
