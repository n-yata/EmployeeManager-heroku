package beans;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 写真のBeanクラス
 * @author yata1
 *
 */
public class Photo {
    /** 写真ID */
    private int id = 0;
    /** コンテキストタイプ */
    private String contentType = "";
    /** 写真データ */
    private byte[] photo;
    /** 最大データサイズ */
    public static final long MAX_FILE_SIZE = 2000000;

    public Photo() {
    }

    public Photo(String contentType, byte[] photo) {
        this.contentType = contentType;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    // オーバーロード
    public void setPhoto(InputStream in) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[1024];
            while (true) {
                int len = in.read(buffer);
                if (len < 0) {
                    break;
                }
                out.write(buffer, 0, len);
            }
            this.photo = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
