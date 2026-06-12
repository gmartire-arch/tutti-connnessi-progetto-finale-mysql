package it.tutti.connessi.gestore.biblioteca.model;

public class DVD extends Articolo {

	public DVD(int id, String titolo, boolean disponibile, int durata) {
		super(id, titolo);
		this.durata = durata;
		this.disponibile = disponibile;
	}

	private String casaDiscografica;
	private int durata;

	public String getCasaDiscografica() {
		return casaDiscografica;
	}

	public void setCasaDiscografica(String casaDiscografica) {
		this.casaDiscografica = casaDiscografica;
	}
	
	

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	@Override
	public String ottieniTipo() {
		return "DVD";
	}
	
	// Sovrascriviamo il toString per personalizzare la stampa del DVD
    @Override
    public String toString() {
        String stato = disponibile ? "Disponibile" : "In Prestito";
        return "[" + id + "] (DVD) " + titolo + " - " + " [" + stato + "]";
    }

	

}
