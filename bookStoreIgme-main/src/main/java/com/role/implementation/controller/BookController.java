package com.role.implementation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.role.implementation.model.Book;
import com.role.implementation.model.MyBookList;
import com.role.implementation.service.BookService;
import com.role.implementation.service.MyBookListService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;
	
	/*
	@GetMapping("/adminScreen")
	public String home() {
		return "dashboard";
	}
	*/
	
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}
	
	@GetMapping("/listado_libros")
	public String listBooks(Model model) {
		List<Book>list=service.getAllBook();
		model.addAttribute("bookTable",list);
		return "listadoLibros";
	}
	
	//prueba
	
	/*
	@GetMapping("/prueba")
	public String prueba() {
		return "prueba";
	}
	*/
	
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book>list=service.getAllBook();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
		return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b, @RequestParam("file") MultipartFile imagen, Model model) {
		service.save(b);
		// Guardar la imagen
	    if (!imagen.isEmpty()) {
	    	
	    	try {
	            // Obtener los bytes de la imagen
	            byte[] imagenBytes = imagen.getBytes();

	            // Ejemplo de guardado en el sistema de archivos
	            String nombreImagen = imagen.getOriginalFilename();
	            String rutaImagen = "/images/" + nombreImagen; // Ruta relativa de la imagen
	            Path rutaCompleta = Paths.get("src//main//resources//static" + rutaImagen); // Ruta completa en el sistema de archivos
	            Files.write(rutaCompleta, imagenBytes);

	            // Asignar la ruta de la imagen al libro guardado
	            b.setImage(rutaImagen);

	            // Actualizar el libro con la ruta de la imagen
	            service.save(b);
	            
	         // Agregar el atributo "imagenCargada" al modelo
	            model.addAttribute("imagenCargada", true);

	            // Puedes realizar cualquier otra operación necesaria con la imagen aquí

	        } catch (IOException e) {
	            // Manejar la excepción en caso de error al procesar la imagen
	            e.printStackTrace();
	        }
	        
	    }
		return "redirect:/available_books";
	}
	@GetMapping("/my_books")
	public String getMyBooks(Model model)
	{
		List<MyBookList>list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	//test
	@GetMapping("/testMenu")
	public String test() {
		return "testMenu";
	}
	
	@GetMapping("/cartShopping")
	public String cart() {
		return "cartShopping";
	}
	
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice(),b.getImage());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b=service.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}
	
}
