package net.jaimetorres.pila.approval.pojos.output.pensionados.commons;

import net.jaimetorres.pila.approval.pojos.output.commons.CommonOutputRecord;

public class CommonPensionadosTotalesMora 
extends CommonOutputRecord{

	private String registroTotalesRenglon36;
	private String tipoRegistro;
	private String diasMora;
	private String moraTotalCotizacionObligatoria;
	
	public String getRegistroTotalesRenglon36() {
		return registroTotalesRenglon36;
	}
	public void setRegistroTotalesRenglon36(String registroTotalesRenglon36) {
		this.registroTotalesRenglon36 = registroTotalesRenglon36;
	}
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getDiasMora() {
		return diasMora;
	}
	public void setDiasMora(String diasMora) {
		this.diasMora = diasMora;
	}
	public String getMoraTotalCotizacionObligatoria() {
		return moraTotalCotizacionObligatoria;
	}
	public void setMoraTotalCotizacionObligatoria(String moraTotalCotizacionObligatoria) {
		this.moraTotalCotizacionObligatoria = moraTotalCotizacionObligatoria;
	}
}
