package com.xworkz.medi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.medi.entity.AppointmentEntity;
import com.xworkz.medi.service.search.SearchService;

@Controller
@RequestMapping("/")
public class SearchController {
	@Autowired
	private SearchService searchService;

	public SearchController() {
		System.out.println("Created " + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "Search.nan", method = RequestMethod.POST)
	public ModelAndView onSearch(String email, HttpServletRequest request) {
		try {
			System.out.println("Invoked onSearch");
			HttpSession session = request.getSession(false);
			if (session != null) {
				String mail = request.getParameter("email");
				System.out.println(mail);
				List<AppointmentEntity> listFromDB = searchService.searchService(email);
				System.out.println("List in Controller" + listFromDB);

				for (AppointmentEntity appointmentEntity : listFromDB) {
					System.out.println(appointmentEntity);
				}

				return new ModelAndView("Search", "list", listFromDB);
			} else {
				return new ModelAndView("Home", "msg", "No Search Found");
			}
		} catch (Exception e) {
			System.out.println("Exception in SearchController");
			e.printStackTrace();
		}
		return new ModelAndView("Home", "msg", "No Search Found");

	}
}
