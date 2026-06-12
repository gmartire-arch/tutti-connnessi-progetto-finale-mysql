package it.tutti.connessi.gestore.biblioteca;

import java.util.InputMismatchException;
import java.util.Scanner;

import it.tutti.connessi.gestore.biblioteca.service.impl.GestioneArticoloServiceImpl;

public class MainApplication {
	/*
	È assolutamente possibile ed è un ottimo esercizio di refactoring (ovvero ristrutturare il codice senza cambiarne il risultato finale)
	per mostrare agli studenti come trasformare un software elementare in un'applicazione professionale, flessibile e pronta per il futuro.

	Nel nostro vecchio gestionale, la classe Biblioteca gestiva direttamente e soltanto la classe concreta Libro. 
	Se domani volessimo aggiungere i DVD o le Riviste? Dovremmo riscrivere tutto.

	Usando un'Interfaccia e una Classe Astratta, creiamo una struttura a tre livelli aperta a qualsiasi evoluzione.

	🏗️ La Nuova Architettura del Progetto
	L'Interfaccia (Prestabile): Definisce le regole pure del prestito (valide per libri, DVD, videogiochi, ecc.).

	La Classe Astratta (RisorsaBiblioteca): Definisce cosa sia una risorsa generica della biblioteca (ID, titolo, stato) e implementa la logica comune.

	La Classe Concreta (Libro): Rappresenta l'oggetto specifico, ereditando lo stato e aggiungendo i suoi dettagli (es. l'autore o il numero di pagine).
	
	*/
	
	public static void main(String[] args) {
    
		GestioneArticoloServiceImpl miaBiblioteca = new GestioneArticoloServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean inEsecuzione = true;

        System.out.println("📚 Benvenuto nel Gestionale della Biblioteca! 📚");

        while (inEsecuzione) {
            System.out.println("\n--- MENU PRINCIPALE ---");
            System.out.println("1. Mostra tutti i libri (Read)");
            System.out.println("2. Aggiungi un nuovo libro (Create)");
            System.out.println("3. Modifica un libro esistente (Update)");
            System.out.println("4. Elimina articolo (Delete)");
            System.out.println("5. Registra PRESTITO articolo");
            System.out.println("6. Registra RESTITUZIONE articolo");
            System.out.println("7. Aggiungi un nuovo DVD (Create)");
            System.out.println("8. Modifica un DVD esistente (Update)");
            System.out.println("9. Esci dal programma");
            System.out.print("Scegli un'opzione (1-7): ");

            // Dichiariamo la variabile scelta fuori dal try per poterla usare nello switch
            int scelta = 0; 

            try {
                scelta = scanner.nextInt();
                scanner.nextLine(); // Pulisce il buffer dopo l'invio del numero corretto
                
            } catch (InputMismatchException e) {
                // 2. GESTIONE DELL'ERRORE: l'utente ha inserito del testo invece di un numero
                System.out.println("⚠️ ERRORE: Devi inserire un numero intero, non dei caratteri!");
                
                scanner.nextLine(); // FONDAMENTALE: Pulisce il buffer "masticando" l'input errato
                
                continue; // Ricomincia il ciclo while da capo, saltando lo switch sotto
            }

            // Se arriviamo qui, l'input era un numero valido e possiamo controllare lo switch
            switch (scelta) {
                case 1:
                    miaBiblioteca.mostraArticoli();
                    break;
                case 2:
                    System.out.print("Inserisci il titolo del libro: ");
                    String titolo = scanner.nextLine();
                    System.out.print("Inserisci l'autore del libro: ");
                    String autore = scanner.nextLine();
                    miaBiblioteca.aggiungiArticolo(titolo, autore, "LIBRO", "", 0);
                    break;
                case 3:
                    // Nota: Anche qui dentro, per essere sicuri al 100%, l'ID andrebbe protetto con un try-catch!
                    System.out.print("Inserisci l'ID del libro da modificare: ");
                    int idModifica = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci il NUOVO titolo: ");
                    String nuovoTitolo = scanner.nextLine();
                    System.out.print("Inserisci il NUOVO autore: ");
                    String nuovoAutore = scanner.nextLine();
                    miaBiblioteca.modificaArticolo(idModifica, nuovoTitolo, nuovoAutore, "", 0);
                    break;
                case 4:
                    System.out.print("Inserisci l'ID del libro da eliminare: ");
                    int idElimina = scanner.nextInt();
                    miaBiblioteca.eliminaArticolo(idElimina);
                    break;
                case 5:
                    System.out.print("Inserisci l'ID del libro da prendere in prestito: ");
                    int idPrestito = scanner.nextInt();
                    miaBiblioteca.prestaRisorsa(idPrestito);
                    break;
                case 6:
                    System.out.print("Inserisci l'ID del libro da restituire: ");
                    int idRestituzione = scanner.nextInt();
                    miaBiblioteca.restituisciRisorsa(idRestituzione);
                    break;
                case 7:
                    System.out.print("Inserisci il titolo del DVD: ");
                    String titoloDvd = scanner.nextLine();
                    System.out.print("Inserisci il regista del DVD: ");
                    String regista = scanner.nextLine();
                    System.out.print("Inserisci la DURATA : ");
                    String durata = scanner.nextLine();
                    miaBiblioteca.aggiungiArticolo(titoloDvd, "", "DVD", regista, Integer.valueOf(durata));
                    break;
                case 8:
                    // Nota: Anche qui dentro, per essere sicuri al 100%, l'ID andrebbe protetto con un try-catch!
                    System.out.print("Inserisci l'ID del DVD da modificare: ");
                    int idModificaDvd = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci il NUOVO titolo: ");
                    String nuovoTitoloDvd = scanner.nextLine();
                    System.out.print("Inserisci il NUOVO regista: ");
                    String nuovoRegista = scanner.nextLine();
                    System.out.print("Inserisci la DURATA : ");
                    String nuovaDurata = scanner.nextLine();
                    miaBiblioteca.modificaArticolo(idModificaDvd, nuovoTitoloDvd, "", nuovoRegista, Integer.valueOf(nuovaDurata));
                    break;
                case 9:
                    System.out.println("Grazie per aver usato il gestionale. Arrivederci! 👋");
                    inEsecuzione = false;
                    break;
                    
                default:
                    System.out.println("⚠️ Opzione non valida. Scegli un numero tra 1 e 7.");
            }
        }
        scanner.close();
    }
		
		
    

}
