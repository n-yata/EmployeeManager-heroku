package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Employee;
import dao.EmployeeDAO;
import util.Validator;

/**
 * Servlet implementation class SearchEmployee
 */
@WebServlet("/SearchEmployee")
public class SearchEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** DB検索処理が完了した際のフォワード先 */
    private final String SUCCESS_FW_URL = "EmployeeList.jsp";
    /** 入力チェックに引っかかった場合のフォワード先 */
    private final String FAIL_FW_URL = "EmployeeSearch.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployee() {
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

        // バリデーションに引っかからなければ、DBから検索
        if (inputIsValidated(request, response)) {
            int postId = Integer.parseInt(request.getParameter("postId"));
            String empId = request.getParameter("empId");
            String name = request.getParameter("name");

            ArrayList<Employee> employees = new ArrayList<>();
            employees = new EmployeeDAO().searchEmp(postId, empId, name);

            request.setAttribute("searchedEmployees", employees);
            request.getRequestDispatcher(SUCCESS_FW_URL).forward(request, response);

        // バリデーションに引っかかったら画面変わらずエラーを表示
        } else {
            request.getRequestDispatcher(FAIL_FW_URL).forward(request, response);
        }
    }

    /**
     * inputの制限事項に引っかかった場合のエラーメッセージ
     * @param request
     * @param responce
     * @return
     */
    private boolean inputIsValidated(HttpServletRequest request, HttpServletResponse responce) {
        boolean success = true;

        String errorEmpId = validateEmpId(request.getParameter("empId"));
        if (errorEmpId != null) {
            request.setAttribute("errorEmpId", errorEmpId);
            success = false;
        }
        String errorName = validateName(request.getParameter("name"));
        if (errorName != null) {
            request.setAttribute("errorName", errorName);
            success = false;
        }

        String errorAllEmpty = validateAllEmpty(Integer.parseInt(request.getParameter("postId")),
                request.getParameter("empId"), request.getParameter("name"));
        if (errorAllEmpty != null) {
            request.setAttribute("errorAllEmpty", errorAllEmpty);
            success = false;
        }
        return success;
    }

    /**
     * 従業員IDの制限事項
     * @param input
     * @return
     */
    private String validateEmpId(String input) {
        ArrayList<String> msg = new ArrayList<>();
        if (!Validator.validateRegexAllowsBlank(input, "^emp[0-9]{4}$")) {
            msg.add("従業員IDは「emp0000」の形式で入力してください。");
        }
        if (msg.isEmpty()) {
            return null;
        } else {
            return String.join("</br>", msg);
        }
    }

    /**
     * 名前の制限事項
     * @param input
     * @return
     */
    private String validateName(String input) {
        ArrayList<String> msg = new ArrayList<>();
        if (!Validator.validateStringSize(input, Employee.NAME_MAX_SIZE)) {
            msg.add("名前は" + Employee.NAME_MAX_SIZE + "バイト以内で入力してください。");
        }
        if (msg.isEmpty()) {
            return null;
        } else {
            return String.join("</br>", msg);
        }
    }

    /**
     * 空白の場合の制限
     * @param input1
     * @param input2
     * @param input3
     * @return
     */
    private String validateAllEmpty(int input1, String input2, String input3) {
        ArrayList<String> msg = new ArrayList<>();
        if (input1 == 0 && input2.equals("") && input3.equals("")) {
            msg.add("最低でも１つの項目を入力してください。");
        }
        if (msg.isEmpty()) {
            return null;
        } else {
            return String.join("</br>", msg);
        }
    }
}
