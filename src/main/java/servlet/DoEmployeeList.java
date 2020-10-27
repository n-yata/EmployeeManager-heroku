package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoEmployeeList
 */
@WebServlet("/DoEmployeeList")
public class DoEmployeeList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** リダイレクト先 */
    private final String RD_URL = "EmployeeList.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoEmployeeList() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リダイレクト
        response.sendRedirect(RD_URL);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
