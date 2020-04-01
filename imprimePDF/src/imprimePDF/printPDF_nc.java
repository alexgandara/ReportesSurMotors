package imprimePDF;



import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;



public class printPDF_nc {
	
	
	//private static PdfWriter writer;


	
	public static final String FONT = ".\\resources\\fonts\\Consolas.ttf";
	public static final String FONT_AN = ".\\resources\\fonts\\arial-narrow.ttf";
	public static final String FONT_BOLD = ".\\resources\\fonts\\FrankfurtGothic-Bold.ttf";
		
	public static void imp_factura(String _file_xml, factura_cabecera Cabecera, factura_detalle[] Detalle, int _lineas_de_la_factura, String _file_pdf) throws DocumentException, IOException {
		//String reportePDF = ".\\data\\20525719953\\05_pdfs\\xxx.pdf"; 
		
		
		String reportePDF = _file_pdf;
		 // 
	//	String formato_factura = ".\\data\\20536659189\\10_formatos\\formatoSFS.jpg"; // .gif and .jpg are ok too!
		
		String formato_factura = ".\\data\\20100216346\\10_formatos\\CartaCompleta.jpg"; 
        
	 	Document document = new Document();
        // step 2
       
        
       // Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
      //  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
        
        PdfWriter writer =
	            PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
        
        // step 3
        document.open();
        
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        BaseFont bf_bold = BaseFont.createFont(FONT_BOLD,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font console = new Font(bf, 9);
        

		Image img = Image.getInstance(formato_factura);
		img.scalePercent(23);
		img.setAbsolutePosition(10, 40); // horizontal , vertical
		document.add(img);
       
         
	
	        
	         
	        // ruc  emisor
	        PdfContentByte canvas = writer.getDirectContent(); //  getDirectContentUnder();
	        writer.setCompressionLevel(0);
	        
	        
	        // direccion de agenia
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(25, 742);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 11); // /F1 12 Tf
	        canvas.showText(Cabecera.get_linea06());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	  	        
	        
	        
	        
	        // NOMBRE DEL DOCUMENTO
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(410, 797);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 12); // /F1 12 Tf
	        canvas.showText(Cabecera.get_tipo_doc_descripcion());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	  
	        
	        
	        // serie
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(470, 767);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 11); // /F1 12 Tf
	        canvas.showText("20100216346");	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        
	        
	        // serie
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(470, 750);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 11); // /F1 12 Tf
	        canvas.showText(Cabecera.get_serie());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        // folio
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(470, 733);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 11); // /F1 12 Tf
	        canvas.showText(Cabecera.get_folio());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	         
	        
	      
	        // RAZON SOCIAL  del receptor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 700);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText("CLIENTE      :"+Cabecera.get_razon_social_receptor());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        
	        
	        
	        
	        
	        // ruc del receptor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 692);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText("NRO DOCUMENTO:"+Cabecera.get_ruc_receptor());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	  
	        
	        // DIRECCION
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 684);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText("DIRECCION    :"+Cabecera.get_direccion_receptor());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	       
	        
	        // CONDICION
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 676);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText(Cabecera.get_linea01());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        

	        //PLACAS
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 668);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText(Cabecera.get_linea02());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q


	        
	        //mecanico
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 660);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText(Cabecera.get_linea03());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q


	        //o reparacion
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 652);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText(Cabecera.get_linea04());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        //o reparacion
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 644);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText(Cabecera.get_linea05());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        
	       
	        

	        
	        
	        // fecha de emision del docto
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(390, 700);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText("Fecha de Emision   :"+Cabecera.get_fecha());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q


	     
	
	        // fecha de emision del docto
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(390, 684);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText("Observaciones      :"+Cabecera.get_linea07());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        
	        
	        
	        
	       
	        String _moneda="";
	        if (Cabecera.get_moneda().equals("PEN")) {
	        	_moneda="Soles";
	        } else {
	        	_moneda="Dolares";
	        }
	        	

	        
	        
	        
	        
	        
	        // NOTAS01
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 250);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText(Cabecera.get_notas01());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        // NOTAS01
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 242);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText(Cabecera.get_notas02());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        // NOTAS01
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 234);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText(Cabecera.get_notas03());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        // NOTAS01
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 226);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText(Cabecera.get_notas04());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        

	        // NOTAS01
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 218);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText(Cabecera.get_notas05());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        // NOTAS01
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 210);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText(Cabecera.get_notas06());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        

	        // NOTAS01
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(370, 250);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        if (Cabecera.get_monto_anticipo01()!=0) {
	        	canvas.showText("Anticipo 01 : "+Cabecera.get_id_anticipo01()+" "+Formato.dinero(Cabecera.get_monto_anticipo01())+" "+Cabecera.get_id_doc01());	        // (Hello World)Tj
	        }
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        // NOTAS01
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(370, 242);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        if (Cabecera.get_monto_anticipo02()!=0) {
	        	canvas.showText("Anticipo 02 : "+Cabecera.get_id_anticipo02()+" "+Formato.dinero(Cabecera.get_monto_anticipo02())+" "+Cabecera.get_id_doc02());	        // (Hello World)Tj
	        }
	        
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        // NOTAS01
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(370, 234);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        if (Cabecera.get_monto_anticipo03()!=0) {
	        	canvas.showText("Anticipo 03 : "+Cabecera.get_id_anticipo03()+" "+Formato.dinero(Cabecera.get_monto_anticipo03())+" "+Cabecera.get_id_doc03());	        // (Hello World)Tj
	        }
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        // NOTAS01
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(400, 226);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        if (Cabecera.get_monto_anticipo04()!=0) {
	        	canvas.showText("Anticipo 04 : "+Cabecera.get_id_anticipo04()+" "+Formato.dinero(Cabecera.get_monto_anticipo04())+" "+Cabecera.get_id_doc04());	        // (Hello World)Tj
	        }
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        

	        // NOTAS01
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(400, 218);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        if (Cabecera.get_monto_anticipo05()!=0) {	        
	        	canvas.showText("Anticipo 05 : "+Cabecera.get_id_anticipo05()+" "+Formato.dinero(Cabecera.get_monto_anticipo05())+" "+Cabecera.get_id_doc05());	        // (Hello World)Tj
	        }
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        


	        
	        
	        
	        
	        // razon de la anulacion del documento.
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 208);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
	        canvas.showText("Mot. Anulación: "+Cabecera.get_motivo_de_anulacion());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        
	        
	        
	        // documento relacionado
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(400, 208);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
	        canvas.showText("Doc. Relacionados:"+Cabecera.get_doc_relacionado());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        
	        
	        // cantidad en letra
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(40, 193);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText("Cantidad en Letra:"+Cabecera.get_total_letra()+" "+_moneda);	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        
	       
	        
	        
	        
	        
	        
	        
	        
	     // resumen ha										sh
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(70, 205);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
	 //       canvas.showText("Código Hash:"+Cabecera.get_codigo_hash());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	  	    String _contenido_qr = Cabecera.get_ruc_emisor()+"|"+Cabecera.get_tipo_documento()+"|"+   
						Cabecera.get_serie()+"-"+Cabecera.get_folio()+"|"+
						Cabecera.get_total_igv()+"|"+Cabecera.get_total()+"|"+Cabecera.get_fecha_qr()+"|"+
						Cabecera.get_tipo_doc_adquiriente()+"|"+
						Cabecera.get_ruc_receptor()+"|";

	  	    	BarcodeQRCode barcodeQRCode = new BarcodeQRCode(_contenido_qr, 89, 89, null);
	  	    		Image codeQrImage = barcodeQRCode.getImage();
	  	    		codeQrImage.setAbsolutePosition(193, 92);
	  	    		document.add(codeQrImage);


	  	       
	  	       
			        //special font sizes
			        BaseFont bf_arial = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			        Font arial = new Font(bf_arial, 7);
			        	      
			  	     Paragraph _linea00 = new Paragraph();
			  	     Chunk _espacio = new Chunk(" ",arial);
			  	     _linea00.add(_espacio);
			  	     
			  	     
			  	     for (int z = 1; z<=12; z++) {
			  	    	 document.add(_linea00);
			  	     }
			  	     
			  	     
			  	     Paragraph _linea01 = new Paragraph(7);
			  //	    Cabecera.set_subtotal(0);
			  //	    Cabecera.set_total_igv(0);
			  //	    Cabecera.set_total(0);
			  	   
			  	     String _desc ="";
			  	     
			  	     for (int i=0; i<_lineas_de_la_factura; i++) {
			  	    	
			  	    	
			  	    	if (Detalle[i].get_descripcion().length()>57) {
			  	    		_desc = Detalle[i].get_descripcion().substring(0,57);
			  	    	} else {
			  	    		_desc = Detalle[i].get_descripcion();
			  	     	}
			  	    	
			  	    			
			  	    		
			  	    		 
			  	    		//Cabecera.set_subtotal(Cabecera.get_subtotal()+Detalle[i].get_subtotal_sin_igv());
				  	    	// Cabecera.set_total_igv(Cabecera.get_total_igv()+Detalle[i].get_igv());
				  	    	// Cabecera.set_total(Cabecera.get_subtotal()+Cabecera.get_total_igv());
				  	    	 
			  	    	 	
			  	    	 
			  	    		 Chunk _producto = new Chunk(Formato.padRight(Detalle[i].get_codigo(),17));
			  	    		 Chunk _descripcion = new Chunk(Formato.padRight(Formato.cadena58(_desc),57));
			  	    		 Chunk _unidad_de_medida = new Chunk(Formato.cadena4(Detalle[i].get_unidad()));
			  	    		 Chunk _cantidad = new Chunk(Formato.cant(Detalle[i].get_cantidad()));
			  	    		 Chunk _precio = new Chunk(Formato.dinero(Detalle[i].get_precio_unitario()));
			  	    		 Chunk _precio_con_igv = new Chunk(Formato.dinero(Detalle[i].get_precio_unitario()*1.18));
			  	    		 Chunk _importe = new Chunk(Formato.dinero(Detalle[i].get_subtotal()));
			  	    		 Chunk _importe_con_igv = new Chunk(Formato.dinero(Detalle[i].get_subtotal()*1.18));
			  	    		 Chunk _descuento = new Chunk(Formato.dinero5(Detalle[i].get_descuento()));
			  	    		 Chunk _importe_sin_igv = new Chunk(Formato.dinero(Detalle[i].get_subtotal_sin_igv()));
			  	    		Chunk _neto = new Chunk(Formato.dinero(Detalle[i].get_subtotal_sin_igv()-Detalle[i].get_descuento()));
			  	    		Chunk _neto_con_igv = new Chunk(Formato.dinero(Detalle[i].get_subtotal()-Detalle[i].get_descuento())); 
			  	    		_espacio.setFont(arial);
			  	    		 
			  	    		 //  _lineas_de_la_factura
		  	     
			  	    	//	 _linea01.add(_espacio);
			  	    	//	 _linea01.add(_espacio);
			  	    		 _linea01.add(_espacio);
			  	    		
			  	    		 _producto.setFont(arial);
			  	    		 _descripcion.setFont(arial);
			  	    		 _unidad_de_medida.setFont(arial);
			  	    		 _cantidad.setFont(arial);
			  	    		_descuento.setFont(arial);
			  	    		 _precio.setFont(arial);
			  	    		 _precio_con_igv.setFont(arial);
			  	    		 _importe.setFont(arial);
			  	    		 _importe_sin_igv.setFont(arial);
			  	    		 _neto.setFont(arial);
			  	    		 _neto_con_igv.setFont(arial);
			  	    		 
			  	    		 if  (Detalle[i].get_codigo().equals(".") || Detalle[i].get_codigo().equals("TXT") ) {
			  	    			 	for (int _l=0;_l<34;_l++) {
			  	    			 		_linea01.add(_espacio);
			  	    			 	}
			  	    				 _linea01.add(_descripcion);
			  	    			 
			  	    		 } else {
			  	    		
			  	    		 
		  	     
			  	    		
			  	    			_linea01.add(_cantidad);
			  	    			_linea01.add(_espacio);
			  	    			_linea01.add(_espacio);
			  	    			_linea01.add(_espacio);
			  	    			_linea01.add(_unidad_de_medida);
			  	    			_linea01.add(_espacio);
			  	    			 _linea01.add(_producto);
			  	    			_linea01.add(_espacio);
			  	    			 
			  	    			 _linea01.add(_descripcion);
			  //	    			 if (Cabecera.get_tipo_doc().equals("03")) {
			  //	    				_linea01.add(_precio_con_igv);
			  //	    			 } else {
			  	    				 _linea01.add(_precio);
			  //	    		 	 }
		  	   
			  //	    			 if (Cabecera.get_tipo_doc().equals("03")) {
			  //	    				 _linea01.add(_importe);
			  //	    			 } else {
				  	    			 _linea01.add(_importe_sin_igv);
			 // 	    		 	 }
			  	    			 
			  	    			 

			  	    			_linea01.add(_descuento);
			  	    			_linea01.add(_espacio);
			  	    			
			  	//    			 if (Cabecera.get_tipo_doc().equals("03")) {
			  	 //   				 _linea01.add(_neto_con_igv);
			  	  //  			 } else {
				  	    			 _linea01.add(_neto_con_igv);
			  	   // 		 	 }

			  	    			
			  	    			
		  	    
			  	    		 } 
		  	     
		  	     
			  	    		 document.add(_linea01);
			  	    		 _linea01.removeAll(_linea01);
		
		  	     }
			  	     
			  	     
			  		
				        String _signo="";
				        
				  	     if (Cabecera.get_moneda().equals("PEN")) {
				  	    	 _signo="S/";
				  	     } else {
				  	    	_signo=" $";
				  	     }
			
			  	     
				     // TOTAL subtotal
				        canvas.saveState();                               // q
				        canvas.beginText();                               // BT
				        canvas.moveText(455, 172);                         // 36 788 Td
				        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
				        
				 //       if (Cabecera.get_tipo_doc().equals("01")) {
				        	canvas.showText("Subtotal:"+_signo+Formato.dinero(Cabecera.get_subtotal()-Cabecera.get_total_descuentos()));
				  //      }
				        
				        canvas.endText();                                 // ET
				        canvas.restoreState();                            // Q
				       
				        // TOTAL subtotal
				        canvas.saveState();                               // q
				        canvas.beginText();                               // BT
				        canvas.moveText(455, 162);                         // 36 788 Td
				        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
				   //     if (Cabecera.get_tipo_doc().equals("01")) {
				        	canvas.showText("IGV:     "+_signo+Formato.dinero(Cabecera.get_total_igv()));
				   //     }
				        canvas.endText();                                 // ET
				        canvas.restoreState();                            // Q  
				        
				        // TOTAL DE LA FACTURAS
				        canvas.saveState();                               // q
				        canvas.beginText();                               // BT
				        canvas.moveText(455, 152);                         // 36 788 Td
				        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
				     // canvas.showText("Total:      "+Cabecera.get_total());	        // (Hello World)Tj
				        canvas.showText("Total:   "+_signo+Formato.dinero(Cabecera.get_total()));
				        canvas.endText();                                 // ET
				        canvas.restoreState();                            // Q
				       
				        // descuento cabecera
				        canvas.saveState();                               // q
				        canvas.beginText();                               // BT
				        canvas.moveText(377, 110);                         // 36 788 Td
				        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		//	        	canvas.showText("Tipo de Operación:"+Cabecera.get_tipo_operacion());	        // (Hello World)Tj
				        canvas.endText();                                 // ET
				        canvas.restoreState();                            // Q
				        
				        

		  	     
	   	
			      

	        // step 5
	        document.close();		
		
		
		
	}

	
	
	
	  

}
