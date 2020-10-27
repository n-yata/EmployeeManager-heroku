package beans;

import java.sql.Date;

import util.Gender;

/**
 * 社員のBeanクラス
 * @author yata1
 *
 */
public class Employee {
    /** 識別ID */
    private int id = 0;
    /** 社員ID */
    private String empId = "";
    /** 社員名 */
    private String name = "";
    /** 年齢 */
    private int age = 0;
    /** 性別 */
    private String gender = Gender.OTHER.getCode();
    /** 写真ID */
    private int photoId;
    /** 都道府県 */
    private String zip = "";
    /** 郵便番号 */
    private int pref = 0;
    /** 住所 */
    private String address = "";
    /** 部署ID */
    private int postId = 0;
    /** 入社日 */
    private Date entDate = null;
    /** 退社日 */
    private Date retDate = null;

    public static final int EMPID_MAX_SIZE = 10;
    public static final int NAME_MAX_SIZE = 40;
    public static final int GENDER_MAX_SIZE = 8;
    public static final int ZIP_MAX_SIZE = 10;
    public static final int ADDRESS_MAX_SIZE = 100;

    public Employee() {
    }

    public Employee(String empId, String name, int age, String gender, int photoId, String zip, int pref,
            String address, int postId, Date entDate, Date retDate) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.photoId = photoId;
        this.zip = zip;
        this.pref = pref;
        this.address = address;
        this.postId = postId;
        this.entDate = entDate;
        this.retDate = retDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getPref() {
        return pref;
    }

    public void setPref(int pref) {
        this.pref = pref;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Date getEntDate() {
        return entDate;
    }

    public void setEntDate(Date entDate) {
        this.entDate = entDate;
    }

    public Date getRetDate() {
        return retDate;
    }

    public void setRetDate(Date retDate) {
        this.retDate = retDate;
    }

    @Override
    public String toString() {
        return empId + "," + name + "," + age + "," + gender + "," + photoId + "," + zip + "," + pref + "," + address
                + "," + postId + "," + entDate + "," + retDate;
    }

}
