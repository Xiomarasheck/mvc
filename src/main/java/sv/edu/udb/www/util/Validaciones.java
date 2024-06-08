/**
 * 
 */
package sv.edu.udb.www.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class Validaciones {

	private static int entero;
	private static double decimal;
	private static String cadena;

	public static boolean esEntero(String cadena) {
		try {
			entero = Integer.parseInt(cadena.trim());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean esEnteroPositivo(String cadena) {
		try {
			entero = Integer.parseInt(cadena.trim());
			if (entero <= 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isEmpty(String mensaje) {
		try {
			if (mensaje.trim().equals("")) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean esDecimal(String cadena) {
		try {
			decimal = Double.parseDouble(cadena.trim());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean esDecimalPositivo(String cadena) {
		try {
			decimal = Double.parseDouble(cadena.trim());
			if (decimal <= 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean esCodigoAutor(String codigoAutor) {
		// TODO Auto-generated method stub
		return true;
	}

	public static boolean esCodigoLibro(String parameter) {
		// TODO Auto-generated method stub
		return true;
	}

}
