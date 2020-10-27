package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Post;
import dao.PostDAO;
import util.Validator;

/**
 * Servlet implementation class CommitPost
 */
@WebServlet("/CommitPost")
public class CommitPost extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** DB更新処理が完了した際のフォワード先 */
    private final String SUCCESS_FW_URL = "Result.jsp";
    /** 入力チェックに引っかかった場合のフォワード先 */
    private final String FAIL_FW_URL = "PostEditor.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommitPost() {
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

        // バリデーションに引っかからなければ、DBに登録
        if (inputIsValidated(request, response)) {
            String postName = request.getParameter("postName");

            // Postにデータをセット
            Post post = new Post();
            post.setName(postName);

            // DBへの反映結果フラグ
            boolean result = false;

            // idが0の場合、Insert
            if (request.getParameter("id").equals("0")) {
                result = new PostDAO().insert(post);

            // それ以外の場合、Update
            } else {
                post.setId(Integer.parseInt(request.getParameter("id")));
                result = new PostDAO().update(post);
            }

            // 反映結果をJSPに渡す
            if (result) {
                request.setAttribute("succeed", "Success");
            } else {
                request.setAttribute("succeed", "Error");
            }
            request.setAttribute("action", "post");
            request.getRequestDispatcher(SUCCESS_FW_URL).forward(request, response);

        // バリデーションに引っかかったら画面変わらずエラーを表示
        } else {
            request.getRequestDispatcher(FAIL_FW_URL).forward(request, response);
        }
    }

    /**
     * inputの制限事項に引っかかった場合のエラーメッセージ
     * @param request
     * @param response
     * @return
     */
    private boolean inputIsValidated(HttpServletRequest request, HttpServletResponse response) {
        boolean success = true;

        String errorName = validateName(request.getParameter("postName"));
        if (errorName != null) {
            request.setAttribute("errorName", errorName);
            success = false;
        }
        return success;
    }

    /**
     * 名前の制限事項
     * @param input
     * @return
     */
    private String validateName(String input) {
        ArrayList<String> msg = new ArrayList<>();
        if (!Validator.validateRequired(input)) {
            msg.add("部署名は入力必須事項です。");
        }
        if (!Validator.validateStringSize(input, Post.NAME_MAX_SIZE)) {
            msg.add("部署名は" + Post.NAME_MAX_SIZE + "バイト以内で入力してください。");
        }
        if (msg.isEmpty()) {
            return null;
        } else {
            return String.join("<br/>", msg);
        }
    }
}
