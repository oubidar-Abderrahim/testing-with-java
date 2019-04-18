package tesseract.testing;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TestingEnglish {
	
	public static void main(String[] args) { 
		
		String inputFileName = "resources/testsubjects/test3en.jpg";
		
		Tesseract tesseract = new Tesseract();
		
		tesseract.setDatapath("resources/tessdata/");
		
		File file = new File(inputFileName);
		
		System.out.println(file.isFile());
		System.out.println("************************************************************************************");
		
		try {
			String result = tesseract.doOCR(file);
			System.out.println(result);
		} catch (TesseractException e) {
			e.printStackTrace();
		}
		System.out.println("************************************************************************************");
		
	}

}
