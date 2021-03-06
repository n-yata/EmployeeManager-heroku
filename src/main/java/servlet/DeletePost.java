package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;

/**
 * Servlet implementation class DeletePost
 */
@WebServlet("/DeletePost")
public class DeletePost extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** フォワード先URL */
    private final String FW_URL = "PostList.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePost() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        // 部署の削除処理を実行
        if (new PostDAO().delete(id)) {
            request.setAttribute("deleteSucceed", "Success");
        } else {
            request.setAttribute("deleteSucceed", "Error");
        }
        request.getRequestDispatcher(FW_URL).forward(request, response);
    }

}
