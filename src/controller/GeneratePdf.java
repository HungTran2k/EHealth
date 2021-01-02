package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class GeneratePdf
 */
@WebServlet("/GeneratePdf")
public class GeneratePdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneratePdf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get user from database with username and password
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUserFromDB(username, password);
		
//		String firstName = request.getParameter("firstName");
//		String lastName = request.getParameter("lastName");
//		String bth_day = request.getParameter("day");
//		String bth_month = request.getParameter("month");
//		String bth_year = request.getParameter("year");
//		String date_of_birth = bth_day + "/" + bth_month + "/" + bth_year;
//		String insuranceName = request.getParameter("insuranceName");
//		String insuranceType = request.getParameter("insuranceType");
//		String healthProblem = request.getParameter("healthProblem");
//		String healthInformation = request.getParameter("healthInformation");
	
		String submitType = request.getParameter("submit");
 
		
		
		if(submitType.equals("generatePdf")) {
			 Document document = new Document();
			 File file = new File("D://HealthInformation.pdf");
		      try
		      {
		         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		         document.open();
		         document.add(new Paragraph("First name: " + user.getFirstName()));
		         document.add(new Paragraph("Last name: " + user.getLastName()));
		         document.add(new Paragraph("Birthday: " + user.getDate_of_birth()));
		         document.add(new Paragraph("Insurance name: " + user.getInsuranceName()));
		         document.add(new Paragraph("Insurance type: " + user.getInsuranceType()));
		         document.add(new Paragraph("Health problem: " + user.getHealth_problem()));
		         document.add(new Paragraph("Health information: " + user.getHealth_information()));
		         document.close();
		         writer.close();
		      } catch (DocumentException e)
		      {
		         e.printStackTrace();
		      } catch (FileNotFoundException e)
		      {
		         e.printStackTrace();
		      }
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
