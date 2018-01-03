package com.finca_la_caprichosa.servlet;

import com.finca_la_caprichosa.rest.ListsService;
import com.finca_la_caprichosa.service.GraphService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ssmiley on 11/29/17.
 */
public class GraphServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private GraphService graphService;

    /**
     * Default constructor.
     */
    public GraphServlet() {
        // empty
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path =  request.getPathInfo();
        request.setAttribute("goats", graphService.fetchGoats());

        // we should be able to determine which graph/jsp from the path.
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/graphs/samples.jsp");
        dispatcher.forward(request, response);
    }
}
