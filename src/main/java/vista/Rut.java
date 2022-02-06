package vista;

public interface Rut {

	boolean chequearRutEnLista(String runIngresado);

	//	 Valida rut de la forma XXXXXXXX-X 
	Boolean validaRut(String rut);

	//	 Valida el dígito verificador
	String dv(String rut);

}