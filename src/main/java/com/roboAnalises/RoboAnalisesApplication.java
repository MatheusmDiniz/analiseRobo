package com.roboAnalises;

import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.repository.ClassificacaoRepository;
import com.roboAnalises.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@AllArgsConstructor
@EnableScheduling
public class RoboAnalisesApplication {

	private final VerificarPadroesService verificarPadroesService;
//	ClassificacaoRepository classificacaoRepository;
//
//	static VerificarEntradasService verificarEntradasService;
//
//	static AnalisePadroesService analisePadroesService;
//
//	static PadroesService padroesService;
//
//	@Autowired
//	public RoboAnalisesApplication(ClassificacaoRepository classificacaoRepository,
//								   VerificarEntradasService verificarEntradasService,
//								   AnalisePadroesService analisePadroesService,
//								   PadroesService padroesService) {
//		this.classificacaoRepository = classificacaoRepository;
//		this.verificarEntradasService = verificarEntradasService;
//		this.analisePadroesService = analisePadroesService;
//		this.padroesService = padroesService;
//	}


	public static void main(String[] args) {
		SpringApplication.run(RoboAnalisesApplication.class, args);

//		new AnalisarTendenciasLinhasService().analisarTendenciaLinhas();

//		new ClassificacaoService(classificacaoRepository).atualizarClassificacao(LigasEnum.EURO);
//		new ClassificacaoService(classificacaoRepository).atualizarClassificacao(LigasEnum.COPA);
//		verificarPadroesService.verificarPadroes();

//
	}

}
