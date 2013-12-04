package GenerateBarcode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtil {

	private String FilePath = "D:/";
	private String FileName = "test.pdf";

	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public String getFileURI() {
		return FilePath + FileName;
	}

	public void GeneratePdf() throws IOException {
		final int margin = 30;

		File file = new File(FilePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		Document document = null;
		FileOutputStream fos = null;
		PdfWriter pdf = null;
		try {
			document = new Document(PageSize.A4, margin, margin, margin, margin);
			fos = new FileOutputStream(getFileURI());
			pdf = PdfWriter.getInstance(document, fos);
			document.open();

			String[] baseFonts = new String[] { BaseFont.COURIER, BaseFont.COURIER_BOLD, BaseFont.COURIER_OBLIQUE, BaseFont.COURIER_BOLDOBLIQUE, BaseFont.HELVETICA, BaseFont.HELVETICA_BOLD, BaseFont.HELVETICA_OBLIQUE, BaseFont.HELVETICA_BOLDOBLIQUE, BaseFont.SYMBOL, BaseFont.TIMES_ROMAN, BaseFont.TIMES_BOLD, BaseFont.TIMES_ITALIC, BaseFont.TIMES_BOLDITALIC, BaseFont.ZAPFDINGBATS };

			Phrase p = new Phrase();
			for (String font : baseFonts) {
				String summary = font.toUpperCase() + "\n";
				p.add(new Paragraph(summary, new Font(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, false))));
				summary = "A fox jump over the lazy dog.\n\n";
				p.add(new Paragraph(summary, new Font(BaseFont.createFont(font, BaseFont.CP1252, false))));
			}
			document.add(p);

			Image image = Image.getInstance("D:\\barcode.png");
			document.add(image);
		} catch (Exception e) {
			return;
		} finally {
			if (document != null) {
				document.close();
			}
			if (pdf != null) {
				pdf.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
}
