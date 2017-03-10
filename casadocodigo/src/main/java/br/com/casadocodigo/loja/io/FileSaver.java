package br.com.casadocodigo.loja.io;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class FileSaver {
	private final String URL_S3_NINJA = "http://localhost:9444/s3";
	
	@Autowired
	private AmazonS3Client s3;
	
	/**
	 * 
	 * @param baseFolder <code>String</code> Diretório base.
	 * @param file <code>MultipartFile</code> Conteúdo a ser salvo.
	 * @return <code>String</code> Endereço onde o conteúdo foi armazenado.
	 */
	public String write(String baseFolder, MultipartFile multipartFile){
		
		try {
			s3.putObject("casadocodigo", multipartFile.getOriginalFilename(), 
						multipartFile.getInputStream(), new ObjectMetadata());
			return URL_S3_NINJA + multipartFile.getOriginalFilename() + "?noAuth=true";
		} catch (AmazonClientException | IOException e) {
			throw new RuntimeException(e);
		}
	}//write()

}//class FileSaver
