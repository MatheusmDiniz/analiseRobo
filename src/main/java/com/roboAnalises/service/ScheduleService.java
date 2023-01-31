package com.roboAnalises.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.roboAnalises.domain.enums.LigasEnum;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
public class ScheduleService {

    private static final Integer TRINTA_SEGUNDOS = 30000;
    private static final Integer UM_MINUTO = 60000;
    private static final Integer DOIS_MINUTOS = 120000;
    private static final Integer TRES_MINUTOS = 180000;
    private static final Integer CINCO_MINUTOS = 300000;
    private static final Integer OITO_MINUTOS = 480000;
    private static final Integer DEZ_MINUTOS = 600000;
    private static final Integer VINTE_MINUTOS = 1200000;

    @Autowired
    private final VerificarPadroesService verificarPadroesService;

    @Autowired
    private final RoboService roboService;

    @Autowired
    private final TelegramService telegramService;

    @Autowired
    private final ClassificacaoService classificacaoService;

    @Autowired
    private final InformacoesService informacoesService;

    @Scheduled(fixedDelay = 600000)
    public void verificarPadroes(){
        System.out.println("Rodando Verificar Padroes");
        verificarPadroesService.verificarPadroes();
    }

     @Scheduled(fixedDelay = 60000)
    public void principal(){
        System.out.println("Rodando O robo");
        roboService.iniciarRobo();
    }



//    @Scheduled(fixedDelay = 10000)
//    public void testeTelegram() throws UnirestException {
////        new TesteTelegram().receberMensagens();
//
//        telegramService.sendMessage("TESTE");
//    }

//    @Scheduled(fixedDelay = 10000)
//    public void criarAtualizacaoClassificacao()  {
//        classificacaoService.getTimesOver25AndAmbasMarcam(LigasEnum.COPA);
//    }

//    @Scheduled(fixedDelay = 10000)
//    public void teste()  {
////        informacoesService.getMediaOverUltimas8Linhas(LigasEnum.getLiga("EURO"));
//        informacoesService.getMelhoresLigas();
//
//    }
}
