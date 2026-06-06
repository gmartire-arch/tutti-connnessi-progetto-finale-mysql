package it.tutti.connessi.gestore.biblioteca.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.tutti.connessi.gestore.biblioteca.dao.ArticoloDAO;
import it.tutti.connessi.gestore.biblioteca.dao.ConnessioneDB;
import it.tutti.connessi.gestore.biblioteca.dao.DvdDAO;
import it.tutti.connessi.gestore.biblioteca.dao.LibroDAO;
import it.tutti.connessi.gestore.biblioteca.model.Articolo;
import it.tutti.connessi.gestore.biblioteca.model.DVD;
import it.tutti.connessi.gestore.biblioteca.model.Libro;

public class GestioneArticoloServiceImpl {

// [CREATE] Aggiungere un libro
public void aggiungiArticolo(String titolo, String autore, String tipo, String casaDisc) {
	
	switch (tipo) {
	case "LIBRO": {
		LibroDAO libroDao = new LibroDAO();
		libroDao.aggiungiLibro(titolo, autore);
        break;
	}
	case "DVD": {
		DvdDAO dvdDAO = new DvdDAO();
		dvdDAO.aggiungiDvd(titolo, 0);
        break;
	}
	default:
		throw new IllegalArgumentException("Unexpected value: " + tipo);
	}
	
    
}


public void mostraArticoli() {
	ArticoloDAO articoloDAO = new LibroDAO();
	articoloDAO.visualizzaCatalogoCompleto();
}


// [UPDATE] Modificare i dati di un libro esistente tramite ID
public void modificaArticolo(int id, String nuovoTitolo, String nuovoAutore, String casaDisc) {
    Articolo risorsa = trovaArticoloPerId(id);

    if (risorsa == null) {
        System.out.println("❌ ID " + id + " non trovato. Impossibile modificare.");
        return;
    }

    
    // 1. Modifichiamo il titolo (valido per qualsiasi risorsa)
    risorsa.setTitolo(nuovoTitolo);

    // 2. Modifichiamo l'autore, MA solo se la risorsa è effettivamente un Libro!
    if (risorsa instanceof Libro) {
        Libro libro = (Libro) risorsa;
        LibroDAO libroDAO = new LibroDAO();
        libroDAO.modificaRisorsa(libro.getId(), nuovoTitolo, nuovoAutore, "");
        
        System.out.println("📝 Libro ID " + id + " modificato con successo!");
    } else if (risorsa instanceof DVD) {
    	DVD libro = (DVD) risorsa; // Cast: trasformiamo la risorsa generica in Libro

        DvdDAO dvdDAO = new DvdDAO();
        dvdDAO.modificaRisorsa(libro.getId(), nuovoTitolo, nuovoAutore, casaDisc);
        System.out.println("📝 DVD ID " + id + " modificato con successo!");
    }
    else {
        System.out.println("⚠️ La risorsa trovata non è un Libro. Modificato solo il titolo.");
    }
}

// Gestisce il prestito sfruttando l'interfaccia/classe astratta
public void prestaRisorsa(int id) {
    Articolo r = trovaArticoloPerId(id);
    if (r != null) {
       ArticoloDAO articoloDAO = new LibroDAO();
       articoloDAO.aggiornaDisponibilita(id, false);
    } else {
        System.out.println("❌ ID " + id + " non trovato nell'inventario.");
    }
}

// Gestisce la restituzione
public void restituisciRisorsa(int id) {
    Articolo r = trovaArticoloPerId(id);
    if (r != null) {
        ArticoloDAO articoloDAO = new LibroDAO();
        articoloDAO.aggiornaDisponibilita(id, true);
    } else {
        System.out.println("❌ ID " + id + " non trovato nell'inventario.");
    }
}

// [DELETE] Eliminare un libro tramite ID
public void eliminaArticolo(int id) {
	Articolo articolo = trovaArticoloPerId(id);
    if (articolo != null) {
        
        System.out.println("🗑️ Articolo rimosso dall'inventario.");
    } else {
        System.out.println("❌ Errore: Impossibile eliminare. ID non trovato.");
    }
}

// Metodo di utilità interno per cercare un libro nell'ArrayList
private Articolo trovaArticoloPerId(int id) {
	ArticoloDAO articoloDao = new LibroDAO();
	return articoloDao.trovaRisorsaPerId(id);
}

}
