package net.jaimetorres.pila.approval.pojos.output.minps;

public class MinpsTotalesRegistroTipo6RiesgosOut extends MinpsTotalesOut{

	private String nroAutorizacionPagoIncapacidades;
	private String valorTotalIncapacidadesPagadas;
	private String aportesPagadosOtrosSistemas;
	private String valorNeto;
	private String nroFormularioUnico;
	private String saldoFavor;
	private String fondo;
	private String subtotal;
	
	public String getNroAutorizacionPagoIncapacidades() {
		return nroAutorizacionPagoIncapacidades;
	}
	public void setNroAutorizacionPagoIncapacidades(String nroAutorizacionPagoIncapacidades) {
		this.nroAutorizacionPagoIncapacidades = nroAutorizacionPagoIncapacidades;
	}
	public String getValorTotalIncapacidadesPagadas() {
		return valorTotalIncapacidadesPagadas;
	}
	public void setValorTotalIncapacidadesPagadas(String valorTotalIncapacidadesPagadas) {
		this.valorTotalIncapacidadesPagadas = valorTotalIncapacidadesPagadas;
	}
	public String getAportesPagadosOtrosSistemas() {
		return aportesPagadosOtrosSistemas;
	}
	public void setAportesPagadosOtrosSistemas(String aportesPagadosOtrosSistemas) {
		this.aportesPagadosOtrosSistemas = aportesPagadosOtrosSistemas;
	}
	public String getValorNeto() {
		return valorNeto;
	}
	public void setValorNeto(String valorNeto) {
		this.valorNeto = valorNeto;
	}
	public String getNroFormularioUnico() {
		return nroFormularioUnico;
	}
	public void setNroFormularioUnico(String nroFormularioUnico) {
		this.nroFormularioUnico = nroFormularioUnico;
	}
	public String getSaldoFavor() {
		return saldoFavor;
	}
	public void setSaldoFavor(String saldoFavor) {
		this.saldoFavor = saldoFavor;
	}
	public String getFondo() {
		return fondo;
	}
	public void setFondo(String fondo) {
		this.fondo = fondo;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
}
