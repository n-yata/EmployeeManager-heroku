package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Post;
import util.Database;

/**
 * 部署テーブルのDAOクラス
 * @author yata1
 */
public class PostDAO {

    /**
     * 部署一覧の取得
     * @return
     */
    public ArrayList<Post> selectAll() {
        String sql = "SELECT * FROM POST";
        ArrayList<Post> posts = new ArrayList<>();

        try (Connection connection = Database.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("ID"));
                p.setName(rs.getString("NAME"));
                posts.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return posts;
    }

    /**
     * IDから該当の部署を検索
     * @param id
     * @return
     */
    public Post selectById(int id) {
        String sql = "SELECT * FROM POST WHERE ID = " + id + "";
        Post post = new Post();

        try (Connection connection = Database.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                post.setId(rs.getInt("ID"));
                post.setName(rs.getString("NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return post;
    }

    /**
     * 部署の新規登録
     * @param post
     * @return
     */
    public boolean insert(Post post) {
        String sql = "INSERT INTO POST(NAME) VALUES(?)";

        try (Connection connection = Database.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, post.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 部署の削除
     * @param id
     * @return
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM POST WHERE ID = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 部署の更新
     * @param post
     * @return
     */
    public boolean update(Post post) {
        String sql = "UPDATE POST SET NAME = ? WHERE ID = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, post.getName());
            pstmt.setInt(2, post.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
