package org.xjd.utils;
 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.InputStream; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map; 
import java.util.Properties; 
   
/*** 
 * Date:2013-01-21  
 *      driver = com.mysql.jdbc.Driver 
 *      url = jdbc:mysql://localhost:3306/AmazonProducts?useUnicode=true&characterEncoding=gbk 
 *      username = root 
 *      password =lyl123  
 * */
   
public final class DatabaseUtils { 
    private static String driver; 
    private static String url; 
    private static String username; 
    private static String password; 
   
    public static final void getPropertise() { 
        Properties pro = new Properties(); 
        File f = new File("conf/dbconfig.properties");
        InputStream in=null;
        try
        {
            in = new FileInputStream(f);
        }
        catch (FileNotFoundException e1)
        {
            // TODO Auto-generated catch block
        }  

        try { 
            if(in == null)
                return;
            pro.load(in); 
            driver = pro.getProperty("driver"); 
            url = pro.getProperty("url"); 
            username = pro.getProperty("username"); 
            password = pro.getProperty("password"); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
//        driver = "com.mysql.jdbc.Driver";
//       url = "jdbc:mysql://127.0.0.1:3306/PostData";
//        username = "root";
//         password = "";
    } 
   
    // �������� 
    public static final Connection openConnection() { 
        Connection con = null; 
        try { 
            Class.forName(driver); 
            con = DriverManager.getConnection(url, username, password); 
        } catch (ClassNotFoundException e) { 
            e.printStackTrace(); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        return con; 
    } 
   
    // �ر���ݿ� 
    public static final void closeDatabase(Connection con, Statement stmt, 
            ResultSet rs) { 
        try { 
            if (rs != null) { 
                rs.close(); 
            } 
            if (stmt != null) { 
                stmt.close(); 
            } 
            if (con != null) { 
                con.close(); 
            } 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
    } 
   
    // ��ݿ��ѯ 
    public static final List sqlQuery(String sqlquery) { 
        List list = new ArrayList(); 
        getPropertise(); 
        Connection con = null; 
        Statement stmt = null; 
        ResultSet rs = null; 
        try { 
            con = openConnection(); 
            stmt = con.createStatement(); 
            rs = stmt.executeQuery(sqlquery); 
   
            while (rs.next()) { 
                HashMap map = new HashMap(); 
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) { 
                    map.put(i, rs.getString(i + 1)); 
                } 
                list.add(map); 
            } 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } finally { 
            closeDatabase(con, stmt, rs); 
        } 
        return list; 
    } 
   
    // ��ݿ���ɾ�� 
    public static final void sqlUpdate(String sqlupdate) { 
        Connection con = null; 
        Statement stmt = null; 
        try { 
            getPropertise(); 
            con = openConnection(); 
            // ���������ֶ��ύ 
            con.setAutoCommit(false); 
            stmt = con.createStatement(); 
            stmt.execute(sqlupdate); 
            con.commit(); 
        } catch (SQLException e) { 
            try { 
                //��������ع�����
                con.rollback(); 
            } catch (SQLException e1) { 
                e1.printStackTrace(); 
            } 
            e.printStackTrace(); 
        } finally { 
            closeDatabase(con, stmt, null); 
        } 
    } 
   
    // ��ݿ����(����ɾ���顢��) 
    public static final List sqlDatabase(String sql) { 
        if (sql.toLowerCase().indexOf("select") != -1) {// ��ѯ���� 
            return sqlQuery(sql); 
        } else {// ����ɾ���� 
            sqlUpdate(sql); 
            return null; 
        } 
    } 
   
    public static void main(String[] agrs) { 
        // ��ѯ��� 
        String sqlquery = "select a.STUDENTID,b.CLASSNAME,a.STUDENTNAME,a.SEX,a.AGE "
                + "from x_student a, x_class b "
                + "where a.CLASSID = b.CLASSID"; 
        List datalist = DatabaseUtils.sqlDatabase(sqlquery); 
        Map map; 
        for (Object obj : datalist) { 
            map = (HashMap) obj; 
            for (int i = 0; i < map.size(); i++) { 
                System.out.print(map.get(i) + "\t"); 
            } 
            System.out.println(); 
        } 
    } 
}