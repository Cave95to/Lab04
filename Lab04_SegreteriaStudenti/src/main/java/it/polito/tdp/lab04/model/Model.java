package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private StudenteDAO studenteDao;
	
	private CorsoDAO corsoDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}

	public List<Corso> getTuttiICorsi() {
		return this.corsoDao.getTuttiICorsi();
	}

	public Studente getStudente(int matricola) {
		return this.studenteDao.getStudente(matricola);
	}

	public List<Studente> getIscrittiCorso(Corso c) {
		return this.corsoDao.getStudentiIscrittiAlCorso(c);
	}

	public List<Corso> getCorsiStudente(Studente s) {
		return this.studenteDao.getCorsiStudente(s);
		
	}

	public boolean isStudenteIscrittoCorso(Studente s, Corso c) {
		return this.studenteDao.isStudenteIscrittoCorso(s,c);
	}

	public boolean iscriviStudenteACorso(Studente s, Corso c) {
		return this.corsoDao.iscriviStudenteACorso(s, c);
	}
	
	
}
