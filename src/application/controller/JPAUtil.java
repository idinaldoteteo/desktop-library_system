package application.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAssistant2018");
	private static JPAUtil jpaUtil = null;

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public static JPAUtil getConnect() {
			jpaUtil = new JPAUtil();
			return jpaUtil;
	}

	public static void getDisconect() {
		if (emf != null)
			emf.close();
	}

	// CRUD
	public static void create(Object obj) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();

	}

	public static void delete(Object obj, Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Object object = em.find(obj.getClass(), id);
		em.remove(object);
		em.getTransaction().commit();

	}

	public static ObservableList<PieChart.Data> getStatisticsQtfyTotalBook() {
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select count(b.id) from Book b");
		long returnList = (long) query.getSingleResult();
		data.add(new PieChart.Data("Total of Books (" + returnList + ")", returnList));
		query = em.createQuery("select count(distinct i.bookID) from Issue i");
		returnList = (long) query.getSingleResult();
		data.add(new PieChart.Data("Total of rented Books (" + returnList + ")", returnList));
		em.getTransaction().commit();
		return data;
	}

	public static ObservableList<PieChart.Data> getStatisticsQtfyTotalUser() {
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select count(m.id) from User m");
		long returnList = (long) query.getSingleResult();
		data.add(new PieChart.Data("Total of Users (" + returnList + ")", returnList));
		query = em.createQuery("select count(distinct i.userID) from Issue i");
		returnList = (long) query.getSingleResult();
		data.add(new PieChart.Data("Total Users with book (" + returnList + ")", returnList));
		em.getTransaction().commit();
		return data;
	}
		
	
	

}
