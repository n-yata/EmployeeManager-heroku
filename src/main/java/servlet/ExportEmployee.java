package servlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Employee;
import dao.EmployeeDAO;

/**
 * Servlet implementation class ExportEmployee
 */
@WebServlet("/ExportEmployee")
public class ExportEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** フォワード先URL */
    private final String FW_URL = "EmployeeList.jsp";
    /** CSVヘッダ名称 */
    private final String CSV_HEADER = "従業員ID,名前,年齢,性別,写真ID,郵便番号,都道府県,住所,部署ID,入社日,退社日";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportEmployee() {
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

        // 保存先の指定
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"employeeList.csv\"");

        try (BufferedWriter writer = new BufferedWriter(response.getWriter());) {
            // ヘッダーを書き込み
            writer.write(CSV_HEADER);
            writer.newLine();
            // 社員一覧を取得し書き込み
            ArrayList<Employee> employees = new EmployeeDAO().selectAll();
            for (Employee employee : employees) {
                writer.write(employee.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            request.setAttribute("writeSucceed", "Error");
            request.getRequestDispatcher(FW_URL).forward(request, response);
        }
    }
}