package net.javaguides.venuemanagement.web;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.javaguides.venuemanagement.dao.VenueDAO;
import net.javaguides.venuemanagement.model.Venue;

@WebServlet("/")
public class VenueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VenueDAO venueDAO;
	
	public void init() {
		venueDAO = new VenueDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertVenue(request, response);
				break;
			case "/delete":
				deleteVenue(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateVenue(request, response);
				break;
			default:
				listVenue(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listVenue(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Venue> listVenue = venueDAO.selectAllVenue();
		request.setAttribute("listVenue", listVenue);
		RequestDispatcher dispatcher = request.getRequestDispatcher("venue-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("venue-from.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Venue existingVenue = venueDAO.selectVenue(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("venue-from.jsp");
		request.setAttribute("venue", existingVenue);
		dispatcher.forward(request, response);

	}

	private void insertVenue(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String capacity = request.getParameter("capacity");
		String charge = request.getParameter("charge");
		
		Venue newVenue = new Venue(name, address, phone, capacity, charge);
		venueDAO.insertVenue(newVenue);
		response.sendRedirect("list");
	}

	private void updateVenue(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String capacity = request.getParameter("capacity");
		String charge = request.getParameter("charge");
		

		Venue book = new Venue(id, name, address, phone, capacity, charge);
		venueDAO.updateVenue(book);
		response.sendRedirect("list");
	}

	private void deleteVenue(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		venueDAO.deleteVenue(id);
		response.sendRedirect("list");

	}

}
