package imprimePDF;

import java.io.File;

















import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import wsHomologador.detalle;



public class v21readXML_nc {


	public static factura_cabecera Cabecera = new factura_cabecera();
	public static factura_detalle[] Detalle = new factura_detalle[200];
	public static factura_detalle_email[] Detalle_email = new factura_detalle_email[200];
	public static reglones[] misReglones = new reglones[10];
	public static palabras[] arregloPalabras = new palabras[200];
	public static int _lineas_de_la_factura=0;
	public static int _lineas_Descripcion=0;

	
	public static documentos_rel[] Rel = new documentos_rel[50];
	
	
	public static void main(String args[]) throws IOException {



	
		
		String _file= args[0];
		String _correo_destino = "";
		if (!isNullOrEmpty(args[1])) {
			_correo_destino = args[1];
		} else {
			_correo_destino= "nada";
			
		}
		
		
		String _ruc = _file.substring(0,11);
		
		
		
		String _file_xml = ".\\data\\"+_ruc+"\\03_xmls_con_firma\\"+_file+".xml";
		String _file_pdf =  ".\\data\\"+_ruc+"\\05_pdfs\\"+_file+".pdf";
		String _file_html = ".\\data\\"+_ruc+"\\10_formatos\\formato.htm";
		
		
		System.out.println(_file_html);
		Cabecera.set_mensaje_html(readFile(_file_html));
		
		System.out.println("Generando Reporte...");
		
		
		
		File fXmlFile = new File(_file_xml);
		try {
			
	/*		
			File file_log = new File(_file_log);

			// if file doesnt exists, then create it
			if (!file_log.exists()) {
				file_log.createNewFile();
			}



			// estas lineas con para escribir en el archivo log comentadas se va a consola
			FileOutputStream fos = new FileOutputStream(file_log);
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);

*/
			System.out.println(".......");
			String raya="----------------------------------------------------------------";
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			
			doc.getDocumentElement().normalize();
			
	//		NodeList nList = doc.getElementsByTagName("Invoice");
			
			System.out.println("DATOS DEL DOCUMENTO");
			
			System.out.println(raya);
			
			
			Cabecera.set_descripcion_documento(doc.getDocumentElement().getNodeName());	
			System.out.println("Documento _ _ _ _ _ _ : " + Cabecera.get_descripcion_documento());
			
			
			
			String _temp = _file;
			int _num = _temp.length();

			Cabecera.set_serie(_temp.substring(15,19));
			Cabecera.set_folio(_temp.substring(20,_num));


			System.out.println(raya);

			Cabecera.set_descripcion_documento(doc.getDocumentElement().getNodeName());	
			System.out.println("Documento _ _ _ _ _ _ _ _ _ _ _: " + Cabecera.get_descripcion_documento());

			Cabecera.set_tipo_doc_descripcion("NOTA DE CREDITO");
			if (Cabecera.get_descripcion_documento().equals("CreditNote")) {
				Cabecera.set_tipo_doc_descripcion("NOTA DE CREDITO");
				Cabecera.set_Ruc_Dni("    ");
			} 



			// cbc:ReferenceID
			NodeList nList_ReferenceID = doc.getElementsByTagName("cbc:ReferenceID");
			Node nNode_ReferenceID = nList_ReferenceID.item(0);
			Cabecera.set_doc_relacionado(nNode_ReferenceID.getTextContent());
			System.out.println("Documento Relacionado _ _ _ _ _: " + Cabecera.get_doc_relacionado());



			// cbc:ResponseCode 
			NodeList nList_ResponseCode = doc.getElementsByTagName("cbc:ResponseCode");
			Node nNode_ResponseCode = nList_ResponseCode.item(0);
			Cabecera.set_tipo_doc_relacionado(nNode_ResponseCode.getTextContent());
			System.out.println("Tipo de Documento Relacionado _: " + Cabecera.get_tipo_doc_relacionado());

			// cbc:Description motivo de la anulacion
			NodeList nList_Description_null = doc.getElementsByTagName("cbc:Description");
			Node nNode_Description_null = nList_Description_null.item(0);
			Cabecera.set_motivo_de_anulacion(nNode_Description_null.getTextContent());
			System.out.println("Motivo de la Anulacion _ _ _ _ : " + Cabecera.get_motivo_de_anulacion());

			
				
			Cabecera.set_Ruc_Dni("RUC:");
			
		
			System.out.println("Serie _ _ _ _ _ _ _ _ _ _ _ _ _: " + Cabecera.get_serie());
			System.out.println("Folio _ _ _ _ _ _ _ _ _ _ _ _ _: " + Cabecera.get_folio());
			
				
			Cabecera.set_Ruc_Dni("RUC:");
			
			
			
			
			// cbc:IssueDate
			NodeList nList_IssueDate = doc.getElementsByTagName("cbc:IssueDate");
			Node nNode_IssueDate = nList_IssueDate.item(0);
			
			String _fecha = nNode_IssueDate.getTextContent();
			
			String _Dia = "";
			String _Mes = "";
			String _Ano = "";
			_Dia = _fecha.substring(8, 10);  //2016.09.17  2016-11-30
			_Mes = _fecha.substring(5, 7);  //2016.09.17  0123456789
			_Ano = _fecha.substring(0, 4);             // 1234567890
			Cabecera.set_fecha( _Dia+"/"+_Mes+"/"+_Ano);
			System.out.println("Fecha del Docto _ _ _ _ _ _ _ _: " + Cabecera.get_fecha());	
		
			
			
			
			// cbc:DueDate

			NodeList nList_DueDate = doc.getElementsByTagName("cbc:DueDate");
			
			
			try {
				Node nNode_DueDate = nList_DueDate.item(0);
				String _fecha_vencimiento = nNode_DueDate.getTextContent();

				_Dia = "";
				_Mes = "";
				_Ano = "";
				_Dia = _fecha_vencimiento.substring(8, 10);  //2016.09.17  2016-11-30
				_Mes = _fecha_vencimiento.substring(5, 7);  //2016.09.17  0123456789
				_Ano = _fecha_vencimiento.substring(0, 4);             // 1234567890
				Cabecera.set_fecha_vencimiento( _Ano+"-"+_Mes+"-"+_Dia);
				

			} catch (Exception e) {
				Cabecera.set_fecha_vencimiento(Cabecera.get_fecha());
				
			}
			
			System.out.println("Fecha del Venciminento _ _ _ _ : " + Cabecera.get_fecha_vencimiento());
			
			
			
			// cbc:ProfileID
			NodeList nList_ProfileID = doc.getElementsByTagName("cbc:ProfileID");
			try {
				Node nNode_ProfileID = nList_ProfileID.item(0);
				Cabecera.set_profile(nNode_ProfileID.getTextContent());
				
			} catch (Exception h) {
				Cabecera.set_profile("0101");
			}
			
			
			
		
			
			
		
			String _profile=Cabecera.get_profile();
			
			Cabecera.set_tipo_operacion("-");

			
			System.out.println("Tipo de Transaccion_ _ _ _ _ _ : " +_profile);
				
			Cabecera.set_tipo_operacion("-");
			
			
				if (_profile.equals("0101")) {
					Cabecera.set_tipo_operacion("Venta Interna");
				}
			

			
//				if (_profile.equals("02")) {
//					Cabecera.set_tipo_operacion("Expotaci�n");
//				}
			
			
//				if (_id.equals("03")) {
//					Cabecera.set_tipo_operacion("No Domicilado");
//				}
			
				if (_profile.equals("0102")) {
					Cabecera.set_tipo_operacion("Anticipo");
				}
			
//				if (_id.equals("05")) {
//				Cabecera.set_tipo_operacion("Vta Itinerante");
//				}
			
//				if (_id.equals("06")) {
//					Cabecera.set_tipo_operacion("Factura Guia");
//				}
						
			

		
			
	
			
		
			// cbc:DocumentCurrencyCode
			NodeList nList_DocumentCurrencyCode = doc.getElementsByTagName("cbc:DocumentCurrencyCode");
			Node nNode_DocumentCurrencyCode = nList_DocumentCurrencyCode.item(0);
			Cabecera.set_moneda(nNode_DocumentCurrencyCode.getTextContent());
			System.out.println("Tipo de Moneda_ _ _ _ _ _ _ _ _: " + Cabecera.get_moneda());

			System.out.println(raya);

			
	
			
			
			
			
			
		
			
		// cbc:CustomerAssignedAccountID "RUC DEL EMISOR"
			
			//cac:AccountingSupplierParty
			NodeList nodeList_AccountingSupplierParty = doc.getElementsByTagName("cac:AccountingSupplierParty").item(0).getChildNodes();
			Node nNode_AccountingSupplierParty = nodeList_AccountingSupplierParty.item(0);
			String _RUC_EMISOR="";
			_RUC_EMISOR=nNode_AccountingSupplierParty.getTextContent().substring(0, 11);
				
			Cabecera.set_ruc_emisor(_RUC_EMISOR);
			System.out.println("RUC del Emisor_ _ _ _ _ _ _ _ _: " + Cabecera.get_ruc_emisor());


			// cac:PartyName
	//		NodeList nList_PartyName = doc.getElementsByTagName("cbc:RegistrationName");
	//		Node nNode_PartyName = nList_PartyName.item(0);
	//		Cabecera.set_razon_social_emisor(nNode_PartyName.getTextContent());
			Cabecera.set_razon_social_emisor("SUR MOTORS S.A.");
	//		System.out.println("Razon Social del Emisor_ _ _ _ : " + Cabecera.get_razon_social_emisor());
	
			System.out.println("Razon Social del Emisor_ _ _ _ : " + Cabecera.get_razon_social_emisor());
			
			
			
			
			
			String _RUC_RECEPTOR="";
			NodeList nodeList_AccountingCustomerParty = doc.getElementsByTagName("cac:AccountingCustomerParty").item(0).getChildNodes();
			Node nNode_AccountingCustomerParty = nodeList_AccountingCustomerParty.item(0);
			try {
				if (Cabecera.get_serie().substring(0, 1).equals("F")) {
					_RUC_RECEPTOR=nNode_AccountingCustomerParty.getTextContent().substring(0, 11);	
				} else {
					_RUC_RECEPTOR=nNode_AccountingCustomerParty.getTextContent().substring(0, 8);
				}
				
			}  catch (Exception h) {
				_RUC_RECEPTOR="- - - - -";
			}
			
			
						
			Cabecera.set_ruc_receptor(_RUC_RECEPTOR);
			System.out.println("RUC del Receptor_ _ _ _ _ _ _ _: " + Cabecera.get_ruc_receptor());

		
			
			// cac:PartyName
			NodeList nList_PartyName_r = doc.getElementsByTagName("cbc:RegistrationName");
			Node nNode_PartyName_r = nList_PartyName_r.item(1);
			Cabecera.set_razon_social_receptor(nNode_PartyName_r.getTextContent());
			System.out.println("Razon Social del Receptor_ _ _ : " + Cabecera.get_razon_social_receptor());
			
			
			// cbc:StreetName
			NodeList nList_StreetName_r = doc.getElementsByTagName("cbc:Line");
			Node nNode_StreetName_r = nList_StreetName_r.item(1);
			Cabecera.set_direccion_receptor(nNode_StreetName_r.getTextContent());
			System.out.println("Direccion del Receptor_ _ _ _ _: " + Cabecera.get_direccion_receptor());
			
			
			
			// cbc:ID	ubigeo
			NodeList nList_ubigeo_r = doc.getElementsByTagName("cbc:ID");
			Node nNode_ubigeo_r = nList_ubigeo_r.item(9);
			Cabecera.set_ubigeo_receptor(nNode_ubigeo_r.getTextContent());
			System.out.println("Ubigeo del Emisor _ _ _ _ _ _ _: " + Cabecera.get_ubigeo_receptor());
		
			
			// cbc:IdentificationCode
	//		NodeList nList_IdentificationCode_r = doc.getElementsByTagName("cbc:IdentificationCode");
	//		Node nNode_IdentificationCode_r = nList_IdentificationCode_r.item(1);
	//		Cabecera.set_pais_receptor(nNode_IdentificationCode_r.getTextContent());
	//		System.out.println("Pais del Receptor_ _ _ _ _ _ _ : " + Cabecera.get_pais_receptor());

			
			// cbc:numero de guia
//			NodeList nList_guia = doc.getElementsByTagName("cbc:CityName");
//			Node nNode_guia = nList_guia.item(1);
//			//Cabecera.set_pais_receptor(nNode_guia.getTextContent());
//			Cabecera.set_guia(nNode_guia.getTextContent());
//			//System.out.println("Guia_ _ _ _ _ _ _ _ __ _ _ _ _ : " + Cabecera.get_pais_receptor());
//			System.out.println("Guia_ _ _ _ _ _ _ _ __ _ _ _ _ : " + Cabecera.get_guia());

			
			// cbc:numero de guia
//			NodeList nList_cond = doc.getElementsByTagName("cbc:CountrySubentity");
//			Node nNode_cond = nList_cond.item(1);
			//Cabecera.set_pais_receptor(nNode_guia.getTextContent());
	//		Cabecera.set_condiciones(nNode_cond.getTextContent());
			//System.out.println("Guia_ _ _ _ _ _ _ _ __ _ _ _ _ : " + Cabecera.get_pais_receptor());
	//		System.out.println("Condiciones_ _ _ _ _ _ _ _ __ _ _ _ _ : " + Cabecera.get_condiciones());
			
			System.out.println(raya);
			System.out.println("Detalle Adicionales_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
		 	
			NodeList nList_AdditionalDocumentReference = doc.getElementsByTagName("cac:AdditionalDocumentReference");
			int _lista_documentos=nList_AdditionalDocumentReference.getLength();

			
			
			
			
		
		
			
			
			
			// datos adicioneales que se neesitan par ael QR

			// tipo doc aquiriente
			NodeList nList_tipo_doc_ad = doc.getElementsByTagName("cbc:ID");
			Node nNode_tipo_doc = nList_tipo_doc_ad.item(4);
			String _ident = nNode_tipo_doc.getTextContent();

			// schemeID
			
			String _tipo_doc_ad="6";
			if (Cabecera.get_ruc_receptor().length()<=8) {
				_tipo_doc_ad="1";
			}
			Cabecera.set_tipo_doc_adquiriente(_tipo_doc_ad);
			
	//		NamedNodeMap attr_tipo_doc_ad = nNode_tipo_doc.getAttributes();
	//		String _tipo_doc_ad="";
	//		if (null != attr_tipo_doc_ad) {
	//			Node p = attr_tipo_doc_ad.getNamedItem("schemeID");
	//			_tipo_doc_ad=p.getNodeValue();
	//		}




		
			Cabecera.set_fecha_qr(_fecha);
			Cabecera.set_tipo_documento(_file.substring(12,14));


			
			
			String _id="";
			String _texto="";
			 
			for (int _n = 0; _n < 50; _n++) {
				Rel[_n] = new documentos_rel();
			}

			
			
			for (int _x=0;_x<_lista_documentos;_x++) {
				Node nNode_AdditionalDocumentReference = nList_AdditionalDocumentReference.item(_x);
				_texto=nNode_AdditionalDocumentReference.getTextContent();
		//		System.out.println("Texto:"+_texto);
				 
				_texto=_texto.substring(0,_texto.length()-2);
				
				//System.out.println(_texto);
				
				Rel[_x].set_id(_texto.substring(0, 4));
				Rel[_x].set_texto(_texto.substring(5));
				
				
				System.out.println("Documento Adicional ID_ _ _ _ _ _ _ _ _ : " + Rel[_x].get_id());
				System.out.println("Documento Adicional Texto _ _ _ _ _ _ _ : " + Rel[_x].get_texto());
									
		
			}
		
			
			System.out.println(raya);
			
			//cbc:TaxableAmount   MONTO GRABADO
			NodeList nList_TaxableAmount = doc.getElementsByTagName("cbc:TaxableAmount");
			Node nNode_TaxableAmount = nList_TaxableAmount.item(0);
			Cabecera.set_total_gravado(Double.parseDouble(nNode_TaxableAmount.getTextContent()));
			System.out.println("Importe Grabado_ _ _ _ _ _ _ _ : " + Cabecera.get_total_gravado());



			//cbc:PayableAmount MONTO inafecto
			Cabecera.set_total_inafecto(0.00);
			System.out.println("Importe Inafecto _ _ _ _ _ _ _ : " + Cabecera.get_total_inafecto());




			//cbc:PayableAmount MONTO exonerado
			Cabecera.set_total_exonerado(0.00);
			System.out.println("Importe Exonerado_ _ _ _ _ _ _ : " + Cabecera.get_total_exonerado());


	//		Cabecera.set_total_gratuitas(0.00);
	//		System.out.println("Transferencia Gratuita _ _ _ _: " + Cabecera.get_total_gratuitas());


			
			//cbc:AllowanceTotalAmount
			
			NodeList nList_AllowanceTotalAmount = doc.getElementsByTagName("cbc:AllowanceTotalAmount");
			Node nNode_AllowanceTotalAmount = nList_AllowanceTotalAmount.item(0);
			Cabecera.set_total_descuentos(Double.parseDouble(nNode_AllowanceTotalAmount.getTextContent()));
			System.out.println("Importe Descuentos _ _ _ _ _ _ : " + Cabecera.get_total_descuentos());



// <cbc:TaxInclusiveAmount currencyID="PEN">16.83</cbc:TaxInclusiveAmount>
			NodeList nList_TaxInclusiveAmount = doc.getElementsByTagName("cbc:TaxInclusiveAmount");
			Node nNode_TaxInclusiveAmount = nList_TaxInclusiveAmount.item(0);


			Cabecera.set_subtotal(Cabecera.get_total_gravado());
			System.out.println("Importe Sub Total_ _ _ _ _ _ _ : " + Cabecera.get_subtotal());

			
			
			
			// prepcac:PrepaidPaymentaid 
			
			
			
			Cabecera.set_id_anticipo01("");
			Cabecera.set_monto_anticipo01(0);
			Cabecera.set_id_doc01("");
			
			Cabecera.set_id_anticipo02("");
			Cabecera.set_monto_anticipo02(0);
			Cabecera.set_id_doc02("");
			
			Cabecera.set_id_anticipo03("");
			Cabecera.set_monto_anticipo03(0);
			Cabecera.set_id_doc03("");
			
			Cabecera.set_id_anticipo04("");
			Cabecera.set_monto_anticipo04(0);
			Cabecera.set_id_doc04("");
			
			Cabecera.set_id_anticipo05("");
			Cabecera.set_monto_anticipo05(0);
			Cabecera.set_id_doc05("");
			
			
			NodeList nList_pre = doc.getElementsByTagName("cac:PrepaidPayment");
			String _id_pre = "";
			double _prepaidAmount = 0;
			String _doc_id = "";
			
			
			for (int temp = 0; temp < nList_pre.getLength(); temp++) {
				Node nNode_pre = nList_pre.item(temp);
				Element eElement_pre = (Element) nNode_pre;
				_id_pre=eElement_pre.getElementsByTagName("cbc:ID").item(0).getTextContent();
				_prepaidAmount=Double.parseDouble(eElement_pre.getElementsByTagName("cbc:PaidAmount").item(0).getTextContent());
		//		_doc_id=eElement_pre.getElementsByTagName("cbc:InstructionID").item(0).getTextContent();
		//		System.out.println("ID:"+_id+" "+"Payable:"+_PayableAmount);
				if (temp==0) {
					Cabecera.set_id_anticipo01(_id_pre);
					Cabecera.set_monto_anticipo01(_prepaidAmount);
					Cabecera.set_id_doc01(_doc_id);
/*					
					// adicional document
					//cac:AccountingSupplierParty
					NodeList nodeList_AdditionalDocRef = doc.getElementsByTagName("cac:AdditionalDocumentReference").item(0).getChildNodes();
					Node nNode_AdditionalDocRef = nodeList_AdditionalDocRef.item(0);
					String _adicional_doc="";
					try {
						_adicional_doc=nNode_AdditionalDocRef.getTextContent();
						System.out.println(_adicional_doc);
						Cabecera.set_id_anticipo01(_adicional_doc);
					}  catch (Exception h) {
					}
					// cac:IssuerParty
					NodeList nodeList_IssuerParty = doc.getElementsByTagName("cac:IssuerParty").item(0).getChildNodes();
					Node nNode_IssuerParty = nodeList_IssuerParty.item(0);
					String _id_adicional_doc="";
					try {
						_id_adicional_doc=nNode_IssuerParty.getTextContent();
						System.out.println(_id_adicional_doc);
						if(_id_adicional_doc.length()<=8) {
							Cabecera.set_id_doc01(Cabecera.get_ruc_receptor());
						} else {
							Cabecera.set_id_doc01(Cabecera.get_ruc_receptor());
						}
					}  catch (Exception h) {
					}
					Cabecera.set_monto_anticipo01(_prepaidAmount);
	
				*/
				}
				
				if (temp==1) {
					Cabecera.set_id_anticipo02(_id_pre);
					Cabecera.set_monto_anticipo02(_prepaidAmount);
					Cabecera.set_id_doc02(_doc_id);
				}
				if (temp==2) {
					Cabecera.set_id_anticipo03(_id_pre);
					Cabecera.set_monto_anticipo03(_prepaidAmount);
					Cabecera.set_id_doc03(_doc_id);
				}
	
				if (temp==3) {
					Cabecera.set_id_anticipo04(_id_pre);
					Cabecera.set_monto_anticipo04(_prepaidAmount);
					Cabecera.set_id_doc04(_doc_id);
				}
				if (temp==4) {
					Cabecera.set_id_anticipo05(_id_pre);
					Cabecera.set_monto_anticipo05(_prepaidAmount);
					Cabecera.set_id_doc05(_doc_id);
				}
			}
			

			
			NodeList nList_docs = doc.getElementsByTagName("cac:AdditionalDocumentReference");
			String _id_doc = "";
			String _id_ident="";
			
			
			
			for (int temp = 0; temp < nList_docs.getLength(); temp++) {
				Node nNode_doc = nList_docs.item(temp);
				Element eElement_doc = (Element) nNode_doc;
				_id_doc=eElement_doc.getElementsByTagName("cbc:ID").item(0).getTextContent();
				_id_ident=eElement_doc.getElementsByTagName("cbc:ID").item(0).getTextContent();
				
				if (temp==0) {
					Cabecera.set_id_anticipo01(_id_doc);
					Cabecera.set_id_doc01(Cabecera.get_ruc_receptor());
				}
				
				if (temp==1) {
					Cabecera.set_id_anticipo02(_id_doc);
					Cabecera.set_id_doc02(Cabecera.get_ruc_receptor());
				}
				if (temp==2) {
					Cabecera.set_id_anticipo03(_id_doc);
					Cabecera.set_id_doc03(Cabecera.get_ruc_receptor());
				}
				
	
				if (temp==3) {
					Cabecera.set_id_anticipo04(_id_doc);
					Cabecera.set_id_doc04(Cabecera.get_ruc_receptor());
				}
				
			}

			
			
			
			
			
			
			/*					
			// adicional document
			//cac:AccountingSupplierParty
			NodeList nodeList_AdditionalDocRef = doc.getElementsByTagName("cac:AdditionalDocumentReference").item(3).getChildNodes();
			Node nNode_AdditionalDocRef = nodeList_AdditionalDocRef.item(3);
			String _adicional_doc="";
		
			try {
				_adicional_doc=nNode_AdditionalDocRef.getTextContent();
				System.out.println("campo 2:"+_adicional_doc);
				Cabecera.set_id_anticipo02("Anticipo2"+_adicional_doc);
				
			}  catch (Exception h) {
		
			}
		
			
			// cac:IssuerParty
			NodeList nodeList_IssuerParty = doc.getElementsByTagName("cac:IssuerParty").item(1).getChildNodes();
			Node nNode_IssuerParty = nodeList_IssuerParty.item(1);
			String _id_adicional_doc="";
			try {
				_id_adicional_doc=nNode_IssuerParty.getTextContent();
				System.out.println(_id_adicional_doc);
				if(_id_adicional_doc.length()<=8) {
					Cabecera.set_id_doc02(_id_adicional_doc);
				} else {
					Cabecera.set_id_doc02(Cabecera.get_ruc_receptor());
						
				}
				
				
			}  catch (Exception h) {
		
			}
			
			
			
			
			
			Cabecera.set_monto_anticipo02(_prepaidAmount);

			
			
		*/	

			
			
			
			
			

			
			_id="";
			
			
			
			//cbc:TaxAmount
			NodeList nList_igv = doc.getElementsByTagName("cbc:TaxAmount");
			Node nNode_igv = nList_igv.item(0);
			Cabecera.set_total_igv(Double.parseDouble(nNode_igv.getTextContent()));
			System.out.println("Importe IGV_ _ _  _ _ _ _ _ _ _: " + Cabecera.get_total_igv());

			
			
			// cac:LegalMonetaryTotal
			
			NodeList nList_total = doc.getElementsByTagName("cac:LegalMonetaryTotal");
			
			double _PayableAmount_total = 0;
			
			
		//	for (int temp = 0; temp < nList_total.getLength(); temp++) {
		
				Node nNode_total = nList_total.item(0);
				
							
				Element eElement_total = (Element) nNode_total;
				
			//	_id=eElement_ids.getElementsByTagName("cbc:ID").item(0).getTextContent();
				_PayableAmount_total=Double.parseDouble(eElement_total.getElementsByTagName("cbc:PayableAmount").item(0).getTextContent());
				
			
			
			// <cbc:TaxInclusiveAmount currencyID="PEN">16.83</cbc:TaxInclusiveAmount>
			
			/////////
			
			
			//cbc:PayableAmount
		//	NodeList nList_total = doc.getElementsByTagName("cbc:PayableAmount");
		//	Node nNode_total = nList_total.item(4);
		//	Cabecera.set_total(Double.parseDouble(nNode_total.getTextContent()));
				
		//	Cabecera.set_total(_PayableAmount_total-Cabecera.get_total_descuentos());
				
				Cabecera.set_total(_PayableAmount_total);	
			System.out.println("T O T A L _ __ _  _ _ _ _ _ _ _: " + Cabecera.get_total());
	
//			Cabecera.set_total_letra(denomina.main(Cabecera.get_total()-Cabecera.get_total_descuentos()));
			Cabecera.set_total_letra(denomina.main(Cabecera.get_total()));			
			System.out.println("Importe con Letra _ _ _ _ _ _ _: " + Cabecera.get_total_letra());
			
			System.out.println(raya);

			
			// cbc:Value  importe con letra
//			NodeList nList_Value = doc.getElementsByTagName("cbc:Value");
//			Node nNode_Value = nList_Value.item(0);
//			Cabecera.set_total_letra(nNode_Value.getTextContent());
//			System.out.println("Importe con Letra _ _ _ _ _ _ : " + Cabecera.get_total_letra());
			
			

			
			
			// DigestValue
			NodeList nList_DigestValue = doc.getElementsByTagName("DigestValue");
			try {
				Node nNode_DigestValue = nList_DigestValue.item(0);
				Cabecera.set_codigo_hash(nNode_DigestValue.getTextContent());
					
			}   catch (Exception h) {
		
				Cabecera.set_codigo_hash("---");
		
				
			}
			
			
			System.out.println("Codigo Hash_ _ _ _ _ _ _ _ _ _ : " + Cabecera.get_codigo_hash());


			Cabecera.set_linea01("");
			Cabecera.set_linea02("");
			Cabecera.set_linea03("");
			Cabecera.set_linea04("");
			Cabecera.set_linea05("");
			
			
			Cabecera.set_notas01("");
			Cabecera.set_notas02("");
			Cabecera.set_notas03("");
			Cabecera.set_notas04("");
			Cabecera.set_notas05("");
			Cabecera.set_notas06("");
			Cabecera.set_notas07("");
			Cabecera.set_notas08("");
			Cabecera.set_notas09("");
			Cabecera.set_notas10("");
			
			Cabecera.set_seg_dni("");
			Cabecera.set_seg_nombre("");
			
			

			// cbc:Note notas de el xml
			
			NodeList nList_Notes = doc.getElementsByTagName("cbc:Note");
			try {
				Node nNode_Note01 = nList_Notes.item(1);
				Cabecera.set_linea01(nNode_Note01.getTextContent());
			} catch (Exception e) {
				Cabecera.set_linea01("-");
			}
			System.out.println("Linea 01:"+Cabecera.get_linea01());
			

			try {
				Node nNode_Note02 = nList_Notes.item(2);
				Cabecera.set_linea02(nNode_Note02.getTextContent());
			} catch (Exception e) {
				Cabecera.set_linea02("-");
			}
			System.out.println("Linea 02:"+Cabecera.get_linea02());

			
			try {
				Node nNode_Note03 = nList_Notes.item(3);
				Cabecera.set_linea03(nNode_Note03.getTextContent());
			} catch (Exception e) {
				Cabecera.set_linea03("-");
			}
			System.out.println("Linea 03:"+Cabecera.get_linea03());
			

			try {
				Node nNode_Note04 = nList_Notes.item(4);
				Cabecera.set_linea04(nNode_Note04.getTextContent());
			} catch (Exception e) {
				Cabecera.set_linea04("-");
			}
			System.out.println("Linea 04:"+Cabecera.get_linea04());
			

			
			try {
				Node nNode_Note05 = nList_Notes.item(5);
				Cabecera.set_linea05(nNode_Note05.getTextContent());
			} catch (Exception e) {
				Cabecera.set_linea05("-");
			}
			System.out.println("Linea 05:"+Cabecera.get_linea05());


			try {
				Node nNode_Note06 = nList_Notes.item(6);
				Cabecera.set_linea06(nNode_Note06.getTextContent());
			} catch (Exception e) {
				Cabecera.set_linea06("-");
			}
			System.out.println("Linea 06:"+Cabecera.get_linea06());



			try {
				Node nNode_Note07 = nList_Notes.item(7);
				Cabecera.set_linea07(nNode_Note07.getTextContent());
			} catch (Exception e) {
				Cabecera.set_linea07("-");
			}
			System.out.println("Linea 07:"+Cabecera.get_linea07());

			
				
			
			NodeList nList_rel = doc.getElementsByTagName("sac:AdditionalProperty");
			String _ids = "";
			_texto = "";
			String _clave = "";
			int _tam = 0;
			
			
			for (int temp = 0; temp < nList_rel.getLength(); temp++) {
		
				
				Node nNode_rel = nList_rel.item(temp);
				
							
				Element eElement_rel = (Element) nNode_rel;
				
				_id=eElement_rel.getElementsByTagName("cbc:ID").item(0).getTextContent();
				_texto=eElement_rel.getElementsByTagName("cbc:Value").item(0).getTextContent();
				
				System.out.println("ID:"+_id);
				System.out.println("TEXTO:"+_texto);
				
				_tam = _id.length();
				
				if (_id.equals("1010")) {
					
					
					int _tam_folio = _texto.length();
					
					Cabecera.set_serie(_texto.substring(0,4));
					Cabecera.set_folio(_texto.substring(5,_tam_folio));
					
					System.out.println("Serie _ _ _ _ _ _ _ _ _ _ _ _ _: " + Cabecera.get_serie());
					System.out.println("Folio _ _ _ _ _ _ _ _ _ _ _ _ _: " + Cabecera.get_folio());

					
				}

				
				
				
				
				if (_id.equals("1000")) {
					Cabecera.set_guia(_texto);
					System.out.println("1000:"+Cabecera.get_guia());
				}
				
				
				
						
				
			 }
			
			
			
			
			





			Cabecera.set_sello(Cabecera.get_ruc_emisor()+"|"+
					Cabecera.get_tipo_doc()+"|"+
					Cabecera.get_serie()+"|"+
					Cabecera.get_folio()+"|"+
					Cabecera.get_total_igv()+"|"+
					Cabecera.get_total()+"|"+
					Cabecera.get_fecha()+"|"+
					""+"|"+		
					""+"|"+
					Cabecera.get_codigo_hash()+
					Cabecera.get_signature()
					);



			System.out.println(raya);
			System.out.println("Detalle del Documento_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");


			// cbc:ID	cantidad
			NodeList nList_linea = doc.getElementsByTagName("cbc:InvoicedQuantity");

			if  (Cabecera.get_descripcion_documento().equals("CreditNote")) {
				// cac:CreditNoteLine
				nList_linea = doc.getElementsByTagName("cac:CreditNoteLine");
			} 



			System.out.println("numero de lineas : " + nList_linea.getLength());	
			int _total_linea=nList_linea.getLength();

			if  (Cabecera.get_descripcion_documento().equals("Invoice")) {
				for (int _linea = 0; _linea < nList_linea.getLength(); _linea++) {
					Detalle[_linea] = new factura_detalle();
					Node nNode_linea = nList_linea.item(_linea);
					System.out.println(_linea);
					Detalle[_linea].set_cantidad(Double.parseDouble(nNode_linea.getTextContent()));
					System.out.println(Detalle[_linea].get_cantidad());
				}
			}

			if  (Cabecera.get_descripcion_documento().equals("CreditNote")) {
				// cbc:PriceAmount  cac:Price
				NodeList nList_CreditedQuantity = doc.getElementsByTagName("cbc:CreditedQuantity");
				for (int _linea = 0; _linea < _total_linea; _linea++) {
					Detalle[_linea] = new factura_detalle();
					Node nNode_CreditedQuantity = nList_CreditedQuantity.item(_linea);
					Detalle[_linea].set_cantidad((Double.parseDouble(nNode_CreditedQuantity.getTextContent())));
					//Detalle[_linea].set_subtotal_sin_igv(Detalle[_linea].get_precio_unitario()*Detalle[_linea].get_cantidad());
				}
			}




			// cbc:ID	cbc:LineExtensionAmount
			NodeList nList_LineExtensionAmount = doc.getElementsByTagName("cbc:LineExtensionAmount");
			for (int _linea = 0; _linea < _total_linea; _linea++) {
				Node nNode_LineExtensionAmount = nList_LineExtensionAmount.item(_linea);
				Detalle[_linea].set_subtotal((Double.parseDouble(nNode_LineExtensionAmount.getTextContent())));
			}


			// cbc:PriceAmount  cac:Price
			NodeList nList_PriceAmount = doc.getElementsByTagName("cac:Price");
			for (int _linea = 0; _linea < _total_linea; _linea++) {
				Node nNode_PriceAmount = nList_PriceAmount.item(_linea);
				Detalle[_linea].set_precio_unitario((Double.parseDouble(nNode_PriceAmount.getTextContent())));
				Detalle[_linea].set_subtotal_sin_igv(Detalle[_linea].get_precio_unitario()*Detalle[_linea].get_cantidad());
			}


			// cbc:TaxableAmount
			NodeList nList_TaxableAmount2 = doc.getElementsByTagName("cbc:TaxAmount");
			for (int _linea = 0; _linea < _total_linea; _linea++) {
				Node nNode_TaxableAmount2 = nList_TaxableAmount2.item(_linea);
				try {
					Detalle[_linea].set_igv((Double.parseDouble(nNode_TaxableAmount2.getTextContent())));

				} catch (Exception e) {
					Detalle[_linea].set_igv(0);
				}
			}


			if  (Cabecera.get_descripcion_documento().equals("CreditNote")) {
				// cbc:Description
				NodeList nList_Description = doc.getElementsByTagName("cbc:Description");
				for (int _linea = 0; _linea < _total_linea; _linea++) {
					Node nNode_Description = nList_Description.item(_linea+1);
					Detalle[_linea].set_descripcion(nNode_Description.getTextContent());
				}
			}



			if  (Cabecera.get_descripcion_documento().equals("Invoice")) {
				// cbc:Description
				NodeList nList_Description = doc.getElementsByTagName("cbc:Description");
				for (int _linea = 0; _linea < _total_linea; _linea++) {
					Node nNode_Description = nList_Description.item(_linea);
					Detalle[_linea].set_descripcion(nNode_Description.getTextContent());
				}
			}


			// cac:SellersItemIdentification
			NodeList nList_SellersItemIdentification = doc.getElementsByTagName("cac:SellersItemIdentification");
			for (int _linea = 0; _linea < _total_linea; _linea++) {
				Node nNode_SellersItemIdentification = nList_SellersItemIdentification.item(_linea);
				Node nNode_codigo = nNode_SellersItemIdentification.getFirstChild();

				Detalle[_linea].set_codigo(nNode_codigo.getTextContent());
			}

			//   para sacar la unidad de medida cbc:InvoicedQuantity



			NodeList nList_InvoicedQuantity = doc.getElementsByTagName("cbc:InvoicedQuantity");

			if  (Cabecera.get_descripcion_documento().equals("CreditNote")) {
				nList_InvoicedQuantity = doc.getElementsByTagName("cbc:CreditedQuantity");

			} 
			for (int _linea = 0; _linea < _total_linea; _linea++) {


				Node nNode_InvoicedQuantity = nList_InvoicedQuantity.item(_linea);
				if (nNode_InvoicedQuantity.hasAttributes()) {
					NamedNodeMap attributes = nNode_InvoicedQuantity.getAttributes();
					Node nameAttribute = attributes.getNamedItem("unitCode");
					if (nameAttribute != null) {
						System.out.println("Name attribute: " +nameAttribute.getTextContent());
						Detalle[_linea].set_unidad(nameAttribute.getTextContent());
					}
				}


			}


			int _linea_imp2=0;
			for (int _linea_imp=0;_linea_imp<_total_linea;_linea_imp++) {
				_linea_imp2=_linea_imp+1;
				System.out.println("");
				System.out.println("Linea_ _ _ _ _ _ _ _ _ _ _: " + _linea_imp2);
				System.out.println("Codigo_ _ _ _ _ _ _ _ _ _ : " + Detalle[_linea_imp].get_codigo());
				System.out.println("Unidad de Medida_ _ _ _ _ : " + Detalle[_linea_imp].get_unidad());
				System.out.println("Descripcion _ _ _ _ _ _ _ : " + Detalle[_linea_imp].get_descripcion());
				System.out.println("Cantidad_ _ _ _ _ _ _ _ _ : " + Detalle[_linea_imp].get_cantidad());
				System.out.println("Precio Unitario _ _ _ _ _ : " + Detalle[_linea_imp].get_precio_unitario());
				System.out.println("IGV _ _ _ _ _ _ _ _ _ _ _ : " + Detalle[_linea_imp].get_igv());
				System.out.println("Monto con IGV _ _ _ _ _ _ : " + Detalle[_linea_imp].get_subtotal());
				System.out.println("Monto sin IGV _ _ _ _ _ _ : " + Detalle[_linea_imp].get_subtotal_sin_igv());
				_lineas_de_la_factura=_linea_imp2;


				//Cabecera.set_total_igv(Cabecera.get_total_igv()+Detalle[_linea_imp].get_igv());
				//Cabecera.set_total(Cabecera.get_subtotal()+Detalle[_linea_imp].get_subtotal_sin_igv());


			}


			//Cabecera.set_subtotal(Cabecera.get_total()-Cabecera.get_total_igv());
			v21printPDF_nc.imp_factura(_file_xml, Cabecera, Detalle, _lineas_de_la_factura,_file_pdf,  Rel);
			
					
			System.out.println("Reporte PDF Generado:"+_file_pdf);
			if (_correo_destino=="nada") {
				System.out.println("Correo Vacio, no se envio correo...");
			} else {
				//	System.out.println("Enviando Correo a "+_correo_destino);
				//	email.main(_correo_destino,_file_xml,_file_pdf,_file,"");
				//	System.out.println("Correo Enviado.");

				System.out.println("Enviando Correo a "+_correo_destino);
		//		email.main(_correo_destino,_file_xml,_file_pdf,_file_respuesta,_file,Cabecera, Detalle, _lineas_de_la_factura, _file_zip_respuesta);

				System.out.println("Correo Enviado.");


			}



			//factura.imp_factura(_file_xml, Cabecera, Detalle);

			//	SFSprintPDF.imp_factura(_file_xml, Cabecera, Detalle, _lineas_de_la_factura,_file_pdf);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean isNullOrEmpty(String a) {
		return a == null || a.isEmpty();
	} 


	public static String readFile(String filename) throws IOException
	{
		String content = null;
		File file = new File(filename); //for ex foo.txt
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader !=null){reader.close();}
		}
		return content;
	}

}