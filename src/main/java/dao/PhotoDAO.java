package dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Photo;
import util.Database;

/**
 * 写真テーブルのDAOクラス
 * @author yata1
 */
public class PhotoDAO {

    /**
     * IDから該当の写真を検索
     * @param id
     * @return
     */
    public Photo selectById(int id) {
        String sql = "SELECT * FROM PHOTO WHERE ID = " + id;
        Photo photo = new Photo();

        try (Connection connection = Database.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                photo.setId(rs.getInt("ID"));
                photo.setContentType(rs.getString("CONTENTTYPE"));
                photo.setPhoto(rs.getBinaryStream("PHOTO"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return photo;
    }

    /**
     * 写真の新規登録
     * @param photo
     * @return
     * @throws SQLException
     */
    public int insert(Photo photo) throws SQLException {
        String sql = "INSERT INTO PHOTO(CONTENTTYPE, PHOTO) VALUES(?, ?)";

        Connection connection = Database.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, photo.getContentType());
        pstmt.setBinaryStream(2, new ByteArrayInputStream(photo.getPhoto()));
        int count = pstmt.executeUpdate();
        if (count == 1) {
            ResultSet rs = pstmt.getGeneratedKeys();
            while (rs.next()) {
                int photoId = rs.getInt(1);
                return rs.getInt(1);
            }
        }
        throw new SQLException();
    }

    /**
     * 写真の削除
     * @param id
     * @return
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM PHOTO WHERE ID = ?";

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
     * 写真の更新
     * @param photo
     * @return
     */
    public boolean update(Photo photo) {
        String sql = "UPDATE PHOTO SET CONTENTTYPE = ?, PHOTO = ? WHERE ID = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, photo.getContentType());
            pstmt.setBinaryStream(2, new ByteArrayInputStream(photo.getPhoto()));
            pstmt.setInt(3, photo.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
