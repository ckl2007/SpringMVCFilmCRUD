package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.database.DatabaseAccessor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	DatabaseAccessor dao;

	@RequestMapping(path = "searchKeyword.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView getFilmByKeyword(String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> f = dao.findCreatedFilmsByWord(keyword);
		mv.addObject("filmList", f);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}

	// Might need to change from model object to set attribute
	@RequestMapping(path = "searchID.do", params = "IDsearch", method = RequestMethod.GET)
	public ModelAndView getFilmById(int IDsearch) {

		// User Created films start at 1k and user created films aren't fleshed out into
		// the child tables
		// To handle this we have a method specifically for searching created films
		Film f = null;
		if (IDsearch > 1000) {
			f = dao.findCreatedFilmById(IDsearch);
		} else {
			f = dao.findFilmById(IDsearch);
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}

	@RequestMapping(path = "creationForm.do", method = RequestMethod.GET)
	public ModelAndView gotoForm() {
		ModelAndView mv = new ModelAndView();
		Film f = new Film();
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/addFilm.jsp");
		return mv;
	}

	@RequestMapping(path = "viewDetails.do", method = RequestMethod.GET)
	public ModelAndView viewDetails(Film f) {
		int IDsearch = f.getId();
		if (IDsearch > 1000) {
			f = dao.findCreatedFilmById(IDsearch);
		} else {
			f = dao.findFilmById(IDsearch);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/viewDetails.jsp");
		return mv;
	}

	@RequestMapping(path = "filmAdded.do", method = RequestMethod.GET)
	public ModelAndView filmAdded(@ModelAttribute("newFilm") Film f) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;

	}

	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public String createFilm(Film f, RedirectAttributes redir) {
		redir.addFlashAttribute("newFilm", f);
		f.setId(dao.addFilm(f));
		return "redirect:filmAdded.do";
	}

	@RequestMapping(path = "filmEdited.do", method = RequestMethod.POST)
	public ModelAndView editedFilm(Film f) {
		dao.updateFilm(f);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}

	@RequestMapping(path = "editFilm.do", method = RequestMethod.GET)
	public ModelAndView editFilm(Film f) {
		ModelAndView mv = new ModelAndView();
		int IDsearch = f.getId();

		if (IDsearch > 1000) {
			f = dao.findCreatedFilmById(IDsearch);
		} else {
			f = dao.findFilmById(IDsearch);
		}
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/CRUD.jsp");
		return mv;
	}

	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST)
	public String deleteFilm(Film f, RedirectAttributes redir) {
		redir.addFlashAttribute("status", dao.deleteFilm(f.getId()));
		return "redirect:filmDeleted.do";

	}

	@RequestMapping(path = "filmDeleted.do", method = RequestMethod.GET)
	public ModelAndView filmDeleted(@ModelAttribute("status") String status) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("deletedStatus", status);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;

	}

}
