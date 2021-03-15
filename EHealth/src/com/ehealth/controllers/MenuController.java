package com.ehealth.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ehealth.models.Appointment;
import com.ehealth.models.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/menu")
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String submitType = request.getParameter("menu");
		if (submitType.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
		} else if (submitType.equals("export")) {
			//get user from session
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			//create file
			Document document = new Document();
			File file = new File("/Users/TuanAnhNguyen/Documents/HealthInformation.pdf");
			
			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
				document.open();
				PdfPTable table = new PdfPTable(2);
				table.addCell(new PdfPCell(new Paragraph("First name")));
				table.addCell(new PdfPCell(new Paragraph(user.getFirstName())));
				table.addCell(new PdfPCell(new Paragraph("Last name")));
				table.addCell(new PdfPCell(new Paragraph(user.getLastName())));
				table.addCell(new PdfPCell(new Paragraph("Birthday")));
				table.addCell(new PdfPCell(new Paragraph(user.getDate_of_birth())));
				table.addCell(new PdfPCell(new Paragraph("Insurance type")));
				table.addCell(new PdfPCell(new Paragraph(user.getInsuranceType())));
				table.addCell(new PdfPCell(new Paragraph("Insurance name")));
				table.addCell(new PdfPCell(new Paragraph(user.getInsuranceName())));
				//table.addCell(new PdfPCell(new Paragraph("Health problem")));
				//table.addCell(new PdfPCell(new Paragraph(user.getHealth_problem().substring(0,1).toUpperCase() + user.getHealth_problem().substring(1))));
				table.addCell(new PdfPCell(new Paragraph("Health information")));
				table.addCell(new PdfPCell(new Paragraph(user.getHealth_information())));
				document.add(table);
				document.close();
				writer.close();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e)  {
				e.printStackTrace();
			}
			request.getRequestDispatcher("display-information.jsp").forward(request, response);
		} else if (submitType.equals("information")) {
			request.getRequestDispatcher("display-information.jsp").forward(request, response);
		} else if (submitType.equals("search")) {
			HttpSession session = request.getSession();
			Appointment appointment = (Appointment) session.getAttribute("appointment");
			if (appointment!=null && appointment.getTime()!=null) {
				request.getRequestDispatcher("appointment.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("search.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("update.jsp").forward(request, response);
		}
	}

}
