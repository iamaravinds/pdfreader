package com.avin.pdfReader;

import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.simple.JSONObject;
import org.springframework.util.ResourceUtils;

import org.apache.commons.lang.StringUtils;

public class PdfRead {

	public void Stripper() {

		int colCount = 0;
		String[] heading = null;
		try {

			String resourceLocation = "classpath:table2.pdf";
			File file = ResourceUtils.getFile(resourceLocation);
			PDDocument document = PDDocument.load(file);

			PDFTextStripper stripper = new PDFTextStripper();
			String rawText = stripper.getText(document);
			if (rawText.indexOf("\r\n \r\n") != -1) {
				rawText = rawText.substring(0, rawText.indexOf("\r\n \r\n"));
			}

			String[] records = rawText.split("\r\n");

			for (int index = 0; index < records.length; index++) {

				String rec = records[index];
				rec = StringUtils.stripEnd(rec, " ");
				if (index == 0) {
					heading = rec.split(" ");
				}
			}
			for (String item : heading) {
				colCount++;
			}
			for (int i = 0; i < records.length; i++) {
				String n = records[i];
				n = StringUtils.stripEnd(n, " ");
				if (i == 0) {
					heading = n.split(" ");
				}

				else {
					String val[] = n.split(" ");
					// Write the method here to insert to DB
					PdfRead pdf = new PdfRead();
					JSONObject jObj = pdf.createJSON(colCount, heading, val);
					String jsonText = jObj.toString();
					System.out.print(jsonText);
					DAO pp = new DAO();
					pp.insertToDB(jsonText);

				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {

		PdfRead pdf = new PdfRead();
		pdf.Stripper();

	}

	public JSONObject createJSON(int colCount2, String[] heading2, String val[]) {

		JSONObject obj = new JSONObject();

		for (int i = 0; i < colCount2; i++) {

			obj.put(heading2[i], val[i]);
		}

		return obj;
	}

}
