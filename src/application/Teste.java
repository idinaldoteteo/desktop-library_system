package application;

import java.io.File;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.controller.JPAUtil;
import application.model.Book;
import application.model.Issue;
import application.model.User;
import application.view.settings.CreateAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Teste {
	
	static File file = new File("E:/@WorkSpace Programming/workspace_ProjetoCadastroComFX/X_LibraryAssistant2018/config.txt");

	public static void main(String[] args) {
    	EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select b, count(i.bookID) " + 
				"from Issue i, Book b " + 
				"where i.bookID = b.number " +
				"group by i.bookID");
		Iterator returnList= query.getResultList().iterator();
		while(returnList.hasNext()) {
			Object[] tuple = (Object[]) returnList.next();
			System.out.println(((Book) tuple[0]).getTitle());
			System.out.println(tuple[1]);
		}

		query = em.createQuery("select m, count(i.userID) " + 
				"from Issue i, User m " + 
				"where i.userID = m.userId " +
				"group by i.userID");
		returnList= query.getResultList().iterator();
		while(returnList.hasNext()) {
			Object[] tuple = (Object[]) returnList.next();
			System.out.println(((User) tuple[0]).getName());
			System.out.println(tuple[1]);
		}

		
		
		
		em.close();
    	
    	
//		Query query = em.createQuery("select count(b.id) from Book b");
//		long returnList = (long) query.getSingleResult();
//		System.out.println("book "+returnList);
//		
//		query = em.createQuery("select count(m.id) from User m");
//		returnList = (long) query.getSingleResult();
//		System.out.println("user "+returnList);
//		
//		query = em.createQuery("select count(distinct i.bookID) from Issue i");
//		returnList = (long) query.getSingleResult();
//		System.out.println("book rent "+returnList);
//		
//		query = em.createQuery("select count(distinct i.userID) from Issue i");
//		returnList = (long) query.getSingleResult();
//		System.out.println("user with book "+returnList);
//		
//		em.getTransaction().commit();
    	
    	
    	
    	
    	
    	
//    	Query query1 = em.createQuery("select X from Book X where Number='X'");
//    	List<Book> retornoLista1 = query1.getResultList();
//    	System.out.println(retornoLista1);
//    	if(retornoLista1.size() == 0) {
//    		System.out.println("entrou no laço");
//    		return;
//    	}
		
//		ObservableList<Object> list = FXCollections.observableArrayList();
//		int returnSearchBookId = 0;
//		int returnSearchIdIssue = 0;
//		String returnSearchBookStatus = "0";
//		
//    	returnSearchBookId = 0;
//     	
//    	EntityManager em = JPAUtil.getEntityManager();
//    	Query query3 = em.createQuery("select i "
//    								+ "from Issue i "
//    								+ "left join Book b on i.Book_ID = b.Number "
//    								+ "left join User m on i.User_ID = m.UserID");
//    	List<Object> returnList= query3.getResultList();
//    	for(Object s : returnList) {
//    		System.out.println(s);
//    	}
//		em.close();
//		
		
//		JPAUtil bancoDados = new JPAUtil();
		
//		String title = "Titulo livro";
//		String author = "Nome Autor";
//		String number = "Numbero do livro";
//		String publisher = "Editora";
//		
//		Book book = new Book(title, number, author, publisher, true);
//		System.out.println("Title : " + book.getTitle() + " | number:" + book.getNumber());
		
		// create okay
//		bancoDados.create(book);
		
		// read
		
		
//		EntityManager em =  bancoDados.getEntityManager();
//		Query query = em.createQuery("select b from Book b");
//		List<Book> retornaLista = query.getResultList();
//		for(Book b : retornaLista){
//			System.out.println();
//			System.out.print(b.getAuthor() +" ");
//			System.out.print(b.getNumber()+" ");
//			System.out.print(b.getPublisher()+" ");
//			System.out.print(b.getTitle());
//		}
//		
//		

		
//    			fieldBookInput
//    	EntityManager em = bancoDados.getEntityManager();
//    	Query query = em.createQuery("select X from Book X where Number='b150'");
//		List<Book> resultado = query.getResultList();
//		for (Book b : resultado){
//			System.out.println(b.getAuthor());
//		}
//    	
//		bancoDados.getDisconect();
//		System.out.println("Conexão fechada");
		
//    	EntityManager em = bancoDados.getEntityManager();
//    	Query query3 = em.createQuery("select X from Issue X where Book_ID='sm10'");
//    	List<Issue> retornoLista3 = query3.getResultList();
//    	String returnNomeUser = null;
//    	for(Issue b : retornoLista3) {
//    		returnNomeUser = b.getUserID();
//    	}
//
//    	String jpql = "select m from User m where UserID ='" + returnNomeUser +"'";
//    	List<User> resultados = em.createQuery(jpql).getResultList();
//    	for(User b : resultados){
//    		System.out.println(
//    				b.getName());
//		}
		
//		EntityManager em = bancoDados.getEntityManager();
//		em.getTransaction().begin();
//		Book book = em.find(Book.class, 2);
//		book.setAvailable(false);
//		em.getTransaction().commit();
//		em.close();

//		EntityManager em = JPAUtil.getEntityManager();
//		Query query = em.createQuery("select b from Book b");
//		List<Book> retornaLista = query.getResultList();
//		for(Book b : retornaLista){
//			System.out.println((b.getAvailable() == true)?"Availabe":"Not Available");
//			
//			System.out.println(new Book(b.getId(),
//											b.getTitle(), 
//											b.getNumber(), 
//											b.getAuthor(), 
//											b.getPublisher(), 
//											(b.getAvailable() == true)?"Availabe":"Not Available"));
//		}
		
		
	}
}
