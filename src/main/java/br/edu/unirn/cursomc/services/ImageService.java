package br.edu.unirn.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.unirn.cursomc.services.exceptions.FileException;

@Service
public class ImageService {

	//conversão da imagem para JPG
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		//coletando a extensão do arquivo
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		//verificando se a img enviada tem uma extensão válida
		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Somente imagens PNG e JPG são permitidas.");
		}
		//Tentativa de BufferedImage a partir do MultipartFile
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			//checando se a img enviada está em formato png
			if ("png".equals(ext)) {
				//conversão da img png > jpg
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo.");
		}
		
	}

	//metodo que converte img para jpg
	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		//color WHITE aplica fundo branco caso a imagem seja transparente
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	//retornar um InputStream (encapsulamento de leitura) atraves de um BufferedImage
	//necessario um InputStream para realizar upload da imagem
	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo.");
		}
	}
	
}
