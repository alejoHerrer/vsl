package net.jaimetorres.pila.approval.pojos;

import java.time.LocalDate;
import java.time.YearMonth;

public class OutputFilenameFields {

	private LocalDate fechaPago;
	private Integer modalidadPlanilla;
	private Long nroPlanilla;
	private String tipoIdentificacion;
	private String nroIdentificacion;
	private String codigoAdministradora;
	private Integer codigoOperador;
	private String tipoArchivo;
	private YearMonth periodoPago;
	
	public LocalDate getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Integer getModalidadPlanilla() {
		return modalidadPlanilla;
	}
	public void setModalidadPlanilla(Integer modalidadPlanilla) {
		this.modalidadPlanilla = modalidadPlanilla;
	}
	public Long getNroPlanilla() {
		return nroPlanilla;
	}
	public void setNroPlanilla(Long nroPlanilla) {
		this.nroPlanilla = nroPlanilla;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getNroIdentificacion() {
		return nroIdentificacion;
	}
	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}
	public String getCodigoAdministradora() {
		return codigoAdministradora;
	}
	public void setCodigoAdministradora(String codigoAdministradora) {
		this.codigoAdministradora = codigoAdministradora;
	}
	public Integer getCodigoOperador() {
		return codigoOperador;
	}
	public void setCodigoOperador(Integer codigoOperador) {
		this.codigoOperador = codigoOperador;
	}
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	public YearMonth getPeriodoPago() {
		return periodoPago;
	}
	public void setPeriodoPago(YearMonth periodoPago) {
		this.periodoPago = periodoPago;
	}
}
