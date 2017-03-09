package br.com.casadocodigo.loja.io;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 
	 * @param baseFolder <code>String</code> Diretório base.
	 * @param file <code>MultipartFile</code> Conteúdo a ser salvo.
	 * @return <code>String</code> Endereço onde o conteúdo foi armazenado.
	 */
	public String write(String baseFolder, MultipartFile file){
		String realPath = request.getServletContext().getRealPath("/" + baseFolder);
		
		try {
			String path = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(path));
			return baseFolder + "/" + file.getOriginalFilename();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}//write()
}//class FileSaver
