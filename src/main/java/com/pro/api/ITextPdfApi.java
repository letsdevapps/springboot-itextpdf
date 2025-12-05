package com.pro.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@RestController
@RequestMapping("/api/itextpdf")
public class ITextPdfApi {

	@GetMapping({"", "/"})
	public String index() {
		return "Api iText PDF está acessível!";
	}

	@GetMapping("/gerar-pdf")
	public ResponseEntity<byte[]> gerarPdf() throws IOException {
		// Criando o ByteArrayOutputStream para armazenar o PDF
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Criando o PdfWriter e PdfDocument (para iText 7)
		PdfWriter writer = new PdfWriter(baos);
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc);

		// Adicionando conteúdo ao PDF
		document.add(new Paragraph("Exemplo de PDF gerado com iText 7 e Spring Boot!"));

		// Fechando o documento
		document.close();

		// Configurando os headers para retornar o PDF
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=exemplo.pdf");
//		headers.add("Content-Disposition", "attachment; filename=exemplo.pdf");
		headers.add("Content-Type", "application/pdf");

		// Retornando o PDF como um array de bytes
		return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
	}
}