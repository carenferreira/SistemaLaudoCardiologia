package cfs.laudos.cardiologia.modelo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class PdfCreatorExemplo {
	
    public static void geraPdf(String exame, String nomeArquivo) {
        Document document = new Document(PageSize.A4);
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream("caminho-pasta" + nomeArquivo));
            document.open();

            //Selecionando uma imagem aleatória de acorodo com o exame
            String imagem="", imagePath = "";
            if(exame.equals("eletrocardiograma")) {
            	 imagem = generateRandomFileName("eletro");
                 imagePath = "caminho-pasta-imagem" + imagem;
            }else if(exame.equals("ecocardiograma")) {
            	 imagem = generateRandomFileName("eco");
                 imagePath = "caminho-pasta-imagem" + imagem;
            }
            
            Image image = Image.getInstance(imagePath);
            image.scaleToFit(400, 400); 
            document.add(image);
            document.close();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    
    //Seleciona aleatoriamente um numero para completar o nome da imagem;
    public static String generateRandomFileName(String exame) {
    	String fileName = "";
    	if(exame.equals("eletro")) {
    		 Random random = new Random();
    	     int randomNumber = random.nextInt(5) + 2;
    	     fileName = exame + randomNumber + ".jpg";
    	}else if(exame.equals("eco")) {
    		Random random = new Random();
   	     	int randomNumber = random.nextInt(8) + 1;
   	     	fileName = exame + randomNumber + ".jpeg";
    	}
       
        return fileName;
    }
}
