package com.dwr.validadorHTRANS.utiles;

/**
 * @author Des14 Enum que almacena los mensajes de error que se pueden generar para el validador CCF-SENA-ICBF
 */
public enum MensajesError {

	CCF_SENA_ICBF001("La longitud del registro 1 no corresponde"), CCF_SENA_ICBF002(
			"El tipo de dato no corresponde, debe ser num\u00e9rico"), CCF_SENA_ICBF003(
			"Error validando el registro tipo 1"), CCF_SENA_ICBF004(
			"El tipo de dato no corresponde, debe ser fecha (aaaa-mm-dd)"), CCF_SENA_ICBF005(
			"El tipo de dato no corresponde, debe ser alfanum\u00e9rico"), CCF_SENA_ICBF006(
			"La longitud del registro no corresponde"), CCF_SENA_ICBF007(
			"Para tipos de planilla diferentes a N no debe venir valor en esta posici\u00f3n"), CCF_SENA_ICBF008(
			"El IBC tiene un valor entre 1 y 10"), CCF_SENA_ICBF009(
			"Se esperaba: aporte > 0 por tener dias trabajados y no presentar novedades"), CCF_SENA_ICBF010(
			"El tipo de dato no corresponde, debe ser decimal"), CCF_SENA_ICBF011(
			"El tipo de dato no corresponde, debe ser decimal"), CCF_SENA_ICBF012(
			"El tipo de dato no corresponde, debe ser decimal"), CCF_SENA_ICBF013(
			"El numero de planilla asociada es obligatorio. Debe ser un numero valido"), CCF_SENA_ICBF014(
			"La fecha de pago de la planilla asociada no puede ser mayor que la actual"), CCF_SENA_ICBF015(
			"Cuando el aportante no es D, el campo de fecha de matricula mercantil y departamento deben venir vacios"), CCF_SENA_ICBF016(
			"Cuando el aportante es D, el campo de fecha de matricula mercantil debe venir con fecha valida"), CCF_SENA_ICBF017(
			"Cuando el aportante es D, el campo departamento de matricula mercantil debe ser un departamento correcto"), CCF_SENA_ICBF018(
			"Los valores permitidos para este campo son S o N"), CCF_SENA_ICBF019(
			"");
	private final String nombre;

	MensajesError(String nombre) {
		this.nombre = nombre;
	}
}
