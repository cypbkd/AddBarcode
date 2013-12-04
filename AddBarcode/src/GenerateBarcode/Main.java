package GenerateBarcode;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		PDFUtil pdf = new PDFUtil();
		pdf.setFileName("test.pdf");
		pdf.setFilePath("D:/");
		try {
			GenerateBarcode.GenerateImage("TXNSYDNEFOSDUNE", "D:\\barcode.png");
			pdf.GeneratePdf();
			Runtime.getRuntime().exec("cmd /c start " + pdf.getFileURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
