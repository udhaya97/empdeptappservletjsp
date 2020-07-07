package com.empdeptappn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import com.empdeptappn.dao.DeptEmpDao;
import com.empdeptappn.dao.DeptEmpDaoImpl;
import com.empdeptappn.model.Department;
import com.empdeptappn.model.Employee;

/**
 * Servlet implementation class ListEmpFromDept
 */
@WebServlet("/listEmp")
public class ListEmpFromDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListEmpFromDept() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void service()
    {
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		System.out.println("get");
		DeptEmpDao ded = new DeptEmpDaoImpl();
		String x = request.getParameter("deptId");
		System.out.println("dept id"+x);
		int xt = Integer.parseInt(x);
		System.out.println("int val "+xt);
		List<Employee> ldept = ded.readEmpFromDept(xt);
		HttpSession sess = request.getSession();
		sess.setAttribute("emplvaldept", ldept);
		
		if(ldept.isEmpty()){
			String s ="no data available";
			System.out.println("data not available");
			request.setAttribute("val", ldept);
			request.setAttribute("mess", "no data available");
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}
		else
		{
			System.out.println("values from listemp : ");
			for (Employee employee : ldept) {
				System.out.println(employee.getEmpName());
			}
			request.setAttribute("val", ldept);
			request.setAttribute("lis", sess.getAttribute("lisvaldept"));
			//request.setAttribute("mess", "no data available");
			//request.setAttribute("deptnam", arg1);
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get"+"value is"+request.getParameter("cgh"));
		System.out.println(request.getParameter("deptId"));
	}

}
