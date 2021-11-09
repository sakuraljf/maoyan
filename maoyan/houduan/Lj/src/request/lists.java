package request;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class list
 */
@WebServlet("/lists")
public class lists extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lists() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json; charset=utf-8");	
		 response.setContentType("text/json; charset=utf-8");
		 PrintWriter out = response.getWriter();
	     response.setHeader("Access-Control-Allow-Origin", "*");
	      response.setHeader("Access-Control-Allow-Methods", "GET,POST");  
	      String username1 = request.getParameter("username");
	      System.out.println(username1);
		          try { 
		        	  Class.forName("com.mysql.jdbc.Driver");
		        		
		        		  Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user","root","123456");
		
		                   Statement stmt = con.createStatement(); //创建Statement对象
		                   String sql;
	                       sql = "select * from dingdan where username =?";
                           PreparedStatement pst=(PreparedStatement)con.prepareStatement(sql);
       				       pst.setObject(1,username1);//取代第一个问好
	                       ResultSet rs=null;;
		                    JSONArray jsonarray = new JSONArray();  
		                    JSONObject jsonobj = new JSONObject(); 
		                    rs=pst.executeQuery();
		                    // 展开结果集数据库
		                    while(rs.next()){
		                        // 通过字段检索
		                        jsonobj.put("filmName", rs.getString("filmName"));    //这个是返回的内容1
	                            jsonobj.put("time", rs.getString("time"));    //这个是返回的内容2
		                        jsonobj.put("yuanName", rs.getString("yuanName"));           //这个是返回的内容3                 
		                        jsonobj.put("locations", rs.getString("locations"));    //这个是返回的内容4
		                        jsonobj.put("id", rs.getString("id"));
		                        jsonobj.put("filmId", rs.getString("filmId"));
		                        jsonarray.add(jsonobj);             
		                     }
		                    // 输出数据
		                   out = response.getWriter();
		                   
		                   out.println(jsonarray);
		                   // 完成后关闭
		                   rs.close();   
		                    stmt.close();   
		                    con.close();                  
		                }catch (Exception e) { 
		                       out.print("get data error!"); 
		                      e.printStackTrace(); }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
