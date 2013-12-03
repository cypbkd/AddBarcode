package GenerateBarcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class GenerateBarcode {
	public static void main(String[] args) {
		try {
			// Create the barcode bean
			Code128Bean bean = new Code128Bean();
			final int dpi = 150;
			// Configure the barcode generator
			bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); // makes the narrow bar width exactly one pixel
			bean.doQuietZone(false);

			// Open output file
			File outputFile = new File("d:\\out.png");
			OutputStream out = new FileOutputStream(outputFile);
			try {
				// Set up the canvas provider for monochrome JPEG output
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

				// Generate the barcode
				bean.generateBarcode(canvas, "YDNEWHJTB");

				// Signal end of generation
				canvas.finish();
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
