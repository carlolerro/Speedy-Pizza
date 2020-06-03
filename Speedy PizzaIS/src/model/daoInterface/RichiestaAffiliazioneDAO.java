package model.daoInterface;

import java.util.Collection;

import model.beans.RichiestaAffiliazione;

public interface RichiestaAffiliazioneDAO {
	public RichiestaAffiliazione inserisciRichiesta(RichiestaAffiliazione richiesta);
	public boolean cambiaStato(String stato,int idRichiesta);
	public Collection<RichiestaAffiliazione> getRichieste();
	public boolean rimuoviRichiesta(int id);
}
