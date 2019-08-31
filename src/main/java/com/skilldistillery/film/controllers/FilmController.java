package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.database.DatabaseAccessor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	DatabaseAccessor dao;
	
	//Might need to change from model object to set attribute
	@RequestMapping(path="searchKeyword.do", params="keyword", method=RequestMethod.GET)
	  public ModelAndView getFilmByKeyword(String keyword) {
	    ModelAndView mv = new ModelAndView();
	    List<Film> f = dao.findFilmsByWord(keyword);
	    mv.addObject("filmList", f);
	    mv.setViewName("WEB-INF/result.jsp");
	    return mv;
	  }
	
	//Might need to change from model object to set attribute
	@RequestMapping(path="searchID.do", params="IDsearch", method=RequestMethod.GET)
	public ModelAndView getFilmById(int IDsearch) {
		ModelAndView mv = new ModelAndView();
		Film f = dao.findFilmById(IDsearch);
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}
	
	@RequestMapping(path="creationForm.do", method=RequestMethod.GET)
	public ModelAndView gotoForm() {
		ModelAndView mv = new ModelAndView();
		Film f= new Film();
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/addFilm.jsp");
		return mv;
	}
	
	
	@RequestMapping(path="createFilm.do", method=RequestMethod.POST)
	public ModelAndView createFilm(Film f) {
		dao.addFilm(f);
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}
	
	//initial index will have form to search by film id or keyword 

	//User story 1 Call DAO  view film details  and display error message if film not found
	//Controller method- controller needs to implement DAO  
	
	//User story 2 User chooses to add new film. JSP shows form to input film details     --
	// In DAO do insert method to save the created film. Failure will show err message to user
	
	//User Story 3 user retrieves a film, they have the option of deleting it. --
	//If they delete the film, it is removed from the database.
	//If the delete fails (such as, due to child records), the user is informed of this.
	
	//User Story 4
	//When a user retrieves a film, they have the option of editing it. If they choose this, all the film's current properties are displayed in a form
	//, allowing them to change any property except the film's ID. When they submit the form, that film's record is updated in the database. 
	//If the update fails, the user is informed of this.
	
	
	//User Story 5
	//A user can search for films by keyword/pattern in title or description. From the resulting list of films, the user can choose to update or delete a record.
	
	//User Story 6
	//When a film's details are displayed, its actors and categories are also listed.


	
	
	
	
	
}
