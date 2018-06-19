package com.charvikent.springboot.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charvikent.springboot.dao.BookDao;
import com.charvikent.springboot.model.Book;

@Controller
public class BookController {
	@Autowired
	BookDao bdao;
	@RequestMapping("/booktest")	
	public String showHomePage(Model model)
	{
	  model.addAttribute("book",new Book());
	  List<Book> blist=bdao.getBooksList();
	  model.addAttribute("blist",blist);
		return "book";
	}
	@RequestMapping(value = "/booktest", method = RequestMethod.POST)
	public String saveBook(@Valid @ModelAttribute  Book book,Model model,RedirectAttributes redir) throws IOException {
		//System.out.println("entering into post....");
		Boolean result=bdao.checkRecordExistsOrNot(book);
		if(result==false)
		{
	    bdao.SaveOrUpdate(book);
	    redir.addFlashAttribute("msg", "Record inserted");
		redir.addFlashAttribute("cssMsg", "success");
		return "redirect:/booktest";
		}
		else
		{
			System.out.println("Record already exists");
			redir.addFlashAttribute("msg", "Record already exists");
			redir.addFlashAttribute("cssMsg", "warning");
		}
		return "redirect:booktest";
		} 
	@SuppressWarnings("unused")
	@RequestMapping("/booklist")	
	public void showBooksList(Model model)
	{
	    List<Book> elist=bdao.getBooksList(); 
	}
	
	@RequestMapping(value = "/deletebook")
	public String  deleteBook(@RequestParam(value="id", required=true) String id,Model model,RedirectAttributes redir)
	{
			bdao.deleteBookRecordByid(id);
			redir.addFlashAttribute("msg", "Record deleted");
			redir.addFlashAttribute("cssMsg", "danger");
			return "redirect:booktest";
	}
}