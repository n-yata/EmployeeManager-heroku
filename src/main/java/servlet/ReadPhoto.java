package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Photo;
import dao.PhotoDAO;

/**
 * Servlet implementation class ReadPhoto
 */
@WebServlet("/ReadPhoto")
public class ReadPhoto extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadPhoto() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // データベースの中から写真を読み出して、レスポンスにデータを書き出す
        int id = Integer.parseInt(request.getParameter("id"));
        Photo photo = new PhotoDAO().selectById(id);
        response.setContentType(photo.getContentType());

        // response.outputStreamを使って書き出す
        try (ServletOutputStream out = response.getOutputStream();) {
            out.write(photo.getPhoto());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
