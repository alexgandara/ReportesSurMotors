package imprimePDF;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Formato {
    public  static DecimalFormat df;
    public  static DecimalFormatSymbols simb;
 
    public static String dinero(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("###,##0.00", simb);
        _regreso=padLeft(df.format(_numero),10);
        return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
    

    
    public static String dinero3(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("###,##0.000", simb);
        _regreso=padLeft(df.format(_numero),11);
        return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
    
    
    public static String dinero5(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("##0.00", simb);
        _regreso=padLeft(df.format(_numero),10);
        return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
    
    
  
    
    
    public static String cant(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("#,##0.00", simb);
        _regreso=padLeft(df.format(_numero),8);
        return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
    
    public static String dinero6(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("##0.000000", simb);
        _regreso=padLeft(df.format(_numero),10);
        return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
    
 
    public static String cadena20(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"20"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
 
    public static String cadena10(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"10"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
    
    
    public static String cadena4(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"4"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
    
    
    
    public static String cadena40(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"40"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
    
    public static String cadena14(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"14"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
    
    
    public static String cadena58(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"58"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
 
    public static String cadena35(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"35"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado ser�a el siguiente: 94.751.890,37
    }
    
   public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);  
   }

   public static String padLeft(String s, int n) {
       return String.format("%1$" + n + "s", s);  
   }
   
 
   
}