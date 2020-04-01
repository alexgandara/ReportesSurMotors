package imprimePDF;




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Node;

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



public class v21printPDF {


	//private static PdfWriter writer;



	public static final String FONT = ".\\resources\\fonts\\Consolas.ttf";
	public static final String FONT_AN = ".\\resources\\fonts\\arial-narrow.ttf";
	public static final String FONT_BOLD = ".\\resources\\fonts\\FrankfurtGothic-Bold.ttf";


	// private static String FILE = "c:/temp/FirstPdf.pdf";

	public static void imp_factura(String _file_xml, factura_cabecera Cabecera, factura_detalle[] Detalle, int _lineas_de_la_factura, String _file_pdf, documentos_rel[] Rel) throws DocumentException, IOException {
		//String reportePDF = ".\\data\\20525719953\\05_pdfs\\xxx.pdf"; 



		String reportePDF = _file_pdf;
		// 
		String formato_factura = ".\\data\\20100216346\\10_formatos\\CartaCompleta.jpg"; // .gif and .jpg are ok too!


		File af = new File(reportePDF);


		af.delete();


		if (!af.exists()) { 




			/*

			for (int _x=0; _x<50;_x++) {


				String _id = Rel[_x].get_id();
				String _texto = Rel[_x].get_texto();
				try {

					_texto=_texto.replace("_", " ");



					System.out.println(_texto);

					if (_id.equals("1101")) {
						Cabecera.set_linea01(_texto);
					}


					if (_id.equals("1102")) {
						Cabecera.set_linea02(_texto);
					}

					if (_id.equals("1103")) {
						Cabecera.set_linea03(_texto);
					}


					if (_id.equals("1104")) {
						Cabecera.set_linea04(_texto);
					}


					if (_id.equals("1105")) {
						Cabecera.set_linea05(_texto);
					}

					if (_id.equals("1106")) {
						Cabecera.set_linea06(_texto);
					}


					if (_id.equals("1107")) {
						Cabecera.set_linea07(_texto);
					}



					if (_id.equals("1111")) {
						Cabecera.set_notas01(_texto);
					}


					if (_id.equals("1112")) {
						Cabecera.set_notas02(_texto);
					}

					if (_id.equals("1113")) {
						Cabecera.set_notas03(_texto);
					}

					if (_id.equals("1114")) {
						Cabecera.set_notas04(_texto);
					}

					if (_id.equals("1115")) {
						Cabecera.set_notas05(_texto);
					}

					if (_id.equals("1116")) {
						Cabecera.set_notas06(_texto);
					}

					if (_id.equals("1117")) {
						Cabecera.set_notas07(_texto);
					}

					if (_id.equals("1118")) {
						Cabecera.set_notas08(_texto);
					}

					if (_id.equals("1119")) {
						Cabecera.set_notas09(_texto);
					}

					if (_id.equals("1120")) {
						Cabecera.set_notas10(_texto);
					}




				} catch (Exception e) {

				}

			}
			 */



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

			// step 4




			// ruc  emisor
			PdfContentByte canvas = writer.getDirectContent(); //  getDirectContentUnder();
			writer.setCompressionLevel(0);



			// version
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(450, 820);                         // 36 788 Td
			canvas.setFontAndSize(bf, 8); // /F1 12 Tf
			canvas.showText("Version xml UBL 2.1 rev B");	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q




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
			canvas.showText("CLIENTE      :"+Cabecera.get_razon_social_receptor()+Cabecera.get_seg_nombre());	        // (Hello World)Tj				
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q



			String _seg_dni="";
			try {
				if (!Cabecera.get_seg_dni().substring(0,1).equals("*")) {
					_seg_dni=Cabecera.get_seg_dni();
				}


			}
				catch (Exception e) {

			}


			// ruc del receptor
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(40, 692);                         // 36 788 Td
			canvas.setFontAndSize(bf, 7); // /F1 12 Tf
			if (_seg_dni.trim().length()>3) {
				canvas.showText("NRO DOCUMENTO:"+Cabecera.get_ruc_receptor()+_seg_dni);	        // (Hello World)Tj
			} else {
				canvas.showText("NRO DOCUMENTO:"+Cabecera.get_ruc_receptor());	        // (Hello World)Tj	
			}
			
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
			canvas.moveText(390, 692);                         // 36 788 Td
			canvas.setFontAndSize(bf, 7); // /F1 12 Tf
			canvas.showText("Total Descuentos   :"+Formato.dinero(Cabecera.get_total_descuentos()));	        // (Hello World)Tj
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

// 

			String _moneda="";
			if (Cabecera.get_moneda().equals("PEN")) {
				_moneda="Soles";
			} else {
				_moneda="Dolares";
			}



			// fecha de emision del docto
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(390, 676);                         // 36 788 Td
			canvas.setFontAndSize(bf, 7); // /F1 12 Tf
			canvas.showText("Tipo de Moneda     :"+_moneda);	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q











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



			/////////////////////



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



			/////////////////////













			// cantidad en letra
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(40, 193);                         // 36 788 Td
			canvas.setFontAndSize(bf, 7); // /F1 12 Tf
			canvas.showText("Cantidad en Letra:"+Cabecera.get_total_letra()+" "+_moneda);	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q

			if ((Cabecera.get_total()>=700) && (Cabecera.get_moneda().equals("PEN"))) {

				if ( (Cabecera.get_serie().equals("F001")) || (Cabecera.get_serie().equals("F200")) || (Cabecera.get_serie().equals("F301")) || (Cabecera.get_serie().equals("F401")) ) {


					// 	detarccion
					canvas.saveState();                               // q
					canvas.beginText();                               // BT
					canvas.moveText(290, 175);                         // 36 788 Td
					canvas.setFontAndSize(bf, 5); // /F1 12 Tf
					canvas.showText("Operaci�n sujeta al sistema de pago de obligaciones");	        // (Hello World)Tj
					canvas.endText();                                 // ET
					canvas.restoreState();                            // Q


					canvas.saveState();                               // q
					canvas.beginText();                               // BT
					canvas.moveText(290, 167);                         // 36 788 Td
					canvas.setFontAndSize(bf, 5); // /F1 12 Tf
					canvas.showText("tributarias  con  el  gobierno  central  Detracci�n ");	        // (Hello World)Tj
					canvas.endText();                                 // ET
					canvas.restoreState();                            // Q


					canvas.saveState();                               // q
					canvas.beginText();                               // BT
					canvas.moveText(290, 159);                         // 36 788 Td
					canvas.setFontAndSize(bf, 5); // /F1 12 Tf
					canvas.showText("(10.00%) Cta. Cte. Banco de la Naci�n 00101056856");	        // (Hello World)Tj
					canvas.endText();                                 // ET
					canvas.restoreState();                            // Q
				}


			}


			if ((Cabecera.get_total()>=214) && (Cabecera.get_moneda().equals("USD"))) {

				if ( (Cabecera.get_serie().equals("F001")) || (Cabecera.get_serie().equals("F200")) || (Cabecera.get_serie().equals("F301")) || (Cabecera.get_serie().equals("F401")) ) {


					// 	detarccion
					canvas.saveState();                               // q
					canvas.beginText();                               // BT
					canvas.moveText(290, 175);                         // 36 788 Td
					canvas.setFontAndSize(bf, 5); // /F1 12 Tf
					canvas.showText("Operaci�n sujeta al sistema de pago de obligaciones");	        // (Hello World)Tj
					canvas.endText();                                 // ET
					canvas.restoreState();                            // Q


					canvas.saveState();                               // q
					canvas.beginText();                               // BT
					canvas.moveText(290, 167);                         // 36 788 Td
					canvas.setFontAndSize(bf, 5); // /F1 12 Tf
					canvas.showText("tributarias  con  el  gobierno  central  Detracci�n ");	        // (Hello World)Tj
					canvas.endText();                                 // ET
					canvas.restoreState();                            // Q


					canvas.saveState();                               // q
					canvas.beginText();                               // BT
					canvas.moveText(290, 159);                         // 36 788 Td
					canvas.setFontAndSize(bf, 5); // /F1 12 Tf
					canvas.showText("(10.00%) Cta. Cte. Banco de la Naci�n 00101056856");	        // (Hello World)Tj
					canvas.endText();                                 // ET
					canvas.restoreState();                            // Q
				}


			}






			// resumen ha										sh
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(70, 205);                         // 36 788 Td
			canvas.setFontAndSize(bf, 8); // /F1 12 Tf
			//       canvas.showText("C�digo Hash:"+Cabecera.get_codigo_hash());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q

			String _contenido_qr = Cabecera.get_ruc_emisor()+"|"+Cabecera.get_tipo_doc()+"|"+   
					Cabecera.get_serie()+"-"+Cabecera.get_folio()+"|"+
					Cabecera.get_total_igv()+"|"+Cabecera.get_total()+"|"+Cabecera.get_fecha_qr()+"|"+
					Cabecera.get_tipo_doc_adquiriente()+"|"+
					Cabecera.get_ruc_receptor()+"|"; //+Cabecera.get_codigo_hash()+"|";

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
		//		System.out.println("linea:---- "+_lineas_de_la_factura);

		


				if (Detalle[i].get_descripcion().length()>57) {
					_desc = Detalle[i].get_descripcion().substring(0,58);
				} else {
					_desc = Detalle[i].get_descripcion();
				}





				try {
					if (_desc.substring(0, 8).equals("ANTICIPO")) {

						if (_lineas_de_la_factura>1) {

							Detalle[i].set_subtotal(Detalle[i].get_subtotal()*(-1));
						}


					}



				} catch (Exception e) {

				}

				// FRANQUICIA


				try {
					if (_desc.substring(0, 10).equals("FRANQUICIA")) {

						if (_lineas_de_la_factura>1) {

							Detalle[i].set_subtotal(Detalle[i].get_subtotal()*(-1));
						}


					}



				} catch (Exception e) {

				}



				//Cabecera.set_subtotal(Cabecera.get_subtotal()+Detalle[i].get_subtotal_sin_igv());
				// Cabecera.set_total_igv(Cabecera.get_total_igv()+Detalle[i].get_igv());
				// Cabecera.set_total(Cabecera.get_subtotal()+Cabecera.get_total_igv());





				Chunk _producto = new Chunk(Formato.padRight(Detalle[i].get_codigo(),17));
				Chunk _descripcion = new Chunk(Formato.padRight(Formato.cadena58(_desc),59));
				Chunk _unidad_de_medida = new Chunk(Formato.cadena4(Detalle[i].get_unidad()));
				Chunk _cantidad = new Chunk(Formato.cant(Detalle[i].get_cantidad()));
				//		 Chunk _precio = new Chunk(Formato.dinero(Detalle[i].get_precio_unitario()));
				//		 Chunk _precio_con_igv = new Chunk(Formato.dinero(Detalle[i].get_precio_unitario()*1.18));
				Chunk _importe = new Chunk(Formato.dinero(Detalle[i].get_subtotal()));
				Chunk _importe_con_igv = new Chunk(Formato.dinero(Detalle[i].get_precio_unitario()*1.18*Detalle[i].get_cantidad()));

				Chunk _importe_sin_igv = new Chunk(Formato.dinero(Detalle[i].get_subtotal_sin_igv()));

				//	Chunk _neto_con_igv = new Chunk(Formato.dinero(((Detalle[i].get_precio_unitario()*1.18)*Detalle[i].get_cantidad())-Detalle[i].get_descuento()));
				double _temp=Detalle[i].get_precio_unitario()*Detalle[i].get_cantidad()*1.18;
				_temp=_temp-Detalle[i].get_descuento();
				//_temp=_temp*1.18;

				//		  	    		double _neto = Detalle[i].get_subtotal_sin_igv()*1.18;
				double _descuento = Detalle[i].get_descuento();
				double _neto = Detalle[i].get_subtotal()-_descuento;
				
	//			System.out.println("Neto: "+_neto);
				


				double _subtotal = _neto+_descuento;
				
			
				double _precio_unitario = _subtotal/Detalle[i].get_cantidad();

				Chunk _neto_linea = new Chunk(Formato.dinero(_neto));
				Chunk _descuento_linea = new Chunk(Formato.dinero(_descuento));
				Chunk _precio_unitario_linea = new Chunk(Formato.dinero3(_precio_unitario));
				Chunk _subtotal_linea = new Chunk(Formato.dinero(_subtotal));



				_neto_linea.setFont(arial);
				_descuento_linea.setFont(arial);
				_precio_unitario_linea.setFont(arial);
				_subtotal_linea.setFont(arial);

				_espacio.setFont(arial);

				//  _lineas_de_la_factura

				//	 _linea01.add(_espacio);
				//	 _linea01.add(_espacio);
				_linea01.add(_espacio);

				_subtotal_linea.setFont(arial);
				_precio_unitario_linea.setFont(arial);
				_producto.setFont(arial);
				_descripcion.setFont(arial);
				_unidad_de_medida.setFont(arial);
				_cantidad.setFont(arial);

				//  		 _precio.setFont(arial);
				//  		 _precio_con_igv.setFont(arial);
				_importe.setFont(arial);
				_importe_sin_igv.setFont(arial);


				_importe_con_igv.setFont(arial);


				if  (Detalle[i].get_codigo().equals(".") || Detalle[i].get_codigo().equals("TXT") || 
						Detalle[i].get_cantidad()==0) 	    		 {
					for (int _l=0;_l<37;_l++) {
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
					//		_linea01.add(_espacio);

					_linea01.add(_descripcion);
					//			 if (Cabecera.get_tipo_doc().equals("03")) {
					_linea01.add(_precio_unitario_linea);
					//			 } else {
					//				 _linea01.add(_precio);
					//		 	 }

					//			 if (Cabecera.get_tipo_doc().equals("03")) {
					_linea01.add(_subtotal_linea);
					//			 } else {
					//   			 _linea01.add(_importe_sin_igv);
					//		 	 }



					_linea01.add(_descuento_linea);
					_linea01.add(_espacio);

					//  			 if (Cabecera.get_tipo_doc().equals("03")) {
					_linea01.add(_neto_linea);
					// 			 } else {
					//    			 _linea01.add(_neto);
					//		 	 }




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
			canvas.moveText(455, 177);                         // 36 788 Td
			canvas.setFontAndSize(bf, 8); // /F1 12 Tf

			if (Cabecera.get_tipo_doc().equals("01")) {
				if (Cabecera.get_total()==0) {
					canvas.showText("Subtotal:"+_signo+Formato.dinero(0));			        		
				} else {
					canvas.showText("Subtotal:"+_signo+Formato.dinero(Cabecera.get_total()-Cabecera.get_total_igv()));	
				}

			}

			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q

			// TOTAL subtotal
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(455, 167);                         // 36 788 Td
			canvas.setFontAndSize(bf, 8); // /F1 12 Tf
			if (Cabecera.get_tipo_doc().equals("01")) {
				if (Cabecera.get_total()==0) {
					canvas.showText("IGV 18% :"+_signo+Formato.dinero(0));
				} else {
					canvas.showText("IGV 18% :"+_signo+Formato.dinero(Cabecera.get_total_igv()-Cabecera.get_total_icbper()));	
				}

			}
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q  

			
			
			
			// TOTAL subtotal
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(455, 157);                         // 36 788 Td
			canvas.setFontAndSize(bf, 8); // /F1 12 Tf
	//		canvas.showText("icbper  :"+_signo+Formato.dinero(Cabecera.get_total_icbper()));
			
			
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q  


			
			
			// TOTAL DE LA FACTURAS
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(455, 147);                         // 36 788 Td
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
			canvas.showText("Tipo de Operacion:"+Cabecera.get_tipo_operacion());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q







			// step 5
			document.close();		

		}	

	}


}
