package br.edu.infnet.dashboard.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.infnet.dashboard.model.domain.Apolice;
import br.edu.infnet.dashboard.model.domain.Log;
import br.edu.infnet.dashboard.model.domain.Veiculo;
import br.edu.infnet.dashboard.model.service.ApoliceService;
import br.edu.infnet.dashboard.model.service.LogService;

@Controller
public class RelatorioController {
	
	@Autowired
	private ApoliceService apoliceService;
	@Autowired
	private LogService logService;

	@GetMapping(value = "/generate")
	public String emissaoRelatorio(Model model) {
		
		Workbook workbook = new XSSFWorkbook();
		
		Sheet abaVeiculos = workbook.createSheet("Veiculos");
				
		String[] columns = {"Data", "Descrição", "Tipo", 
							"Nome do solicitante", "E-mail do solicitante", "CPF do solicitante", "UF solicitante",
							"Descrição do veiculo", "Valor do veiculo"};

		Row headerRow = abaVeiculos.createRow(0);
		
		for(int i = 0; i < columns.length; i++) {
			headerRow.createCell(i).setCellValue(columns[i]);
		}
		
		List<Apolice> apolices = apoliceService.obterLista();
		
		int rowNum = 0;
		
		for(Apolice apolice : apolices) {
			
			for(Veiculo veiculo : apolice.getItensSegurados()) {
				Row row = abaVeiculos.createRow(++rowNum);
				
				row.createCell(0).setCellValue(
						apolice.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
					);
				row.createCell(1).setCellValue(apolice.getNumApolice());
				row.createCell(2).setCellValue(apolice.getNumApolice().equals("3") ? "web" : "loja");
				row.createCell(3).setCellValue(apolice.getSegurado().getNome());
				row.createCell(4).setCellValue(apolice.getSegurado().getEmail());
				row.createCell(5).setCellValue(apolice.getSegurado().getCpfCnpj());
				row.createCell(6).setCellValue(
						apolice.getSegurado().getEndereco() != null ? 
								apolice.getSegurado().getEndereco().getUf() : ""
							);
				row.createCell(7).setCellValue(veiculo.getPlaca());
				row.createCell(8).setCellValue(veiculo.getUf());
			}
		}
		
		LocalDateTime hoje = LocalDateTime.now(); 
		
		String arquivo = "veiculos"+hoje.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+".xlsx";		

		String mensagem = null;
		try {
			workbook.write(new FileOutputStream(arquivo));

			workbook.close();

			mensagem = "A planilha gerada está disponível no diretório padrão!!!"; 
			
		} catch (IOException e) {
			mensagem = "Problemas na geração da planilha!!!";
			
			e.printStackTrace();
		}
		
		Log log = new Log();
		log.setData(hoje);
		log.setNome(mensagem);
		
		logService.incluir(log);
		
		return "redirect:/";
	}
}
