package com.roboAnalises.service;

import com.roboAnalises.domain.AnalisePadroes;
import com.roboAnalises.domain.Score;
import com.roboAnalises.domain.enums.Apostas;
import com.roboAnalises.domain.enums.Padrao;
import com.roboAnalises.domain.enums.TipoEntrada;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.util.Data;
import com.roboAnalises.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Service
@AllArgsConstructor
public class RoboService {

    @Autowired
    EntradasService entradasService;

    @Autowired
    AnalisePadroesService analisePadroesService;

    @Autowired
    TelegramService telegramService;

    public void iniciarRobo(){

        //logica para so mandar uma entrada de cada vez
//        Long entradasNaoFinalizadas = entradasService.findEntradasNaoFinalizadas();
//        if(entradasNaoFinalizadas >= 1){
//            return;
//        }

        List<AnalisePadroes> analisePadroes = analisePadroesService.getAnalisePadroes();

        if(analisePadroes.isEmpty()) {
            return;
        }

        verificarPadrao(analisePadroes);
    }

    private Map<String, BiConsumer<Jogos, String>> createMapPadroesAposta() {
        Map<String, BiConsumer<Jogos, String>> padroesAposta = new HashMap<>();
        padroesAposta.put(Padrao.OVER25_0x0_INTERVALO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 0, 0, Apostas.OVER25, TipoEntrada.QUINA, false));
        padroesAposta.put(Padrao.OVER25_2x2_JOGOTODO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 2, 2, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, true));
        padroesAposta.put(Padrao.OVER25_3x1_JOGOTODO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 3, 1, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, true));
        padroesAposta.put(Padrao.OVER25_1x3_JOGOTODO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 1, 3, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, true));
        padroesAposta.put(Padrao.AMBAS_1x1_INTERVALO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 1, 1, Apostas.AMBASMARCAM, TipoEntrada.QUINA, false));
        padroesAposta.put(Padrao.AMBAS_0x0_INTERVALO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 0, 0, Apostas.AMBASMARCAM, TipoEntrada.QUINA, false));
        padroesAposta.put(Padrao.AMBAS_2x0CASA_JOGOTODO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 2, 0, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true));
        padroesAposta.put(Padrao.AMBAS_0x2FORA_JOGOTODO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 0, 2, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true));
        padroesAposta.put(Padrao.AMBAS_0x0_JOGOTODO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 0, 0, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true));
        padroesAposta.put(Padrao.AMBAS_3x1_JOGOTODO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 3, 1, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, true));
        padroesAposta.put(Padrao.AMBAS_2x2_JOGOTODO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 2, 2, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, true));
        padroesAposta.put(Padrao.AMBAS_1x3_JOGOTODO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 1, 3, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, true));
        padroesAposta.put(Padrao.OVER25_2x0_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 2, 0, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.Over25_0x2_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 0, 2, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.Over25_1x2_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 1, 2, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.Over25_2x3_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 2, 3, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.AMBAS_2x0_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 2, 0, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.AMBAS_3x0_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 3, 0, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.AMBAS_0x2_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 0, 2, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.AMBAS_0x1_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 0, 1, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.AMBAS_1x2_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 1, 2, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.AMBAS_1x3_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 1, 3, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.AMBAS_2x3_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 2, 3, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.AMBAS_1x0_INTERVALO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 1, 0, Apostas.AMBASMARCAM, TipoEntrada.QUINA, false));
        padroesAposta.put(Padrao.AMBAS_2x0_INTERVALO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 2, 0, Apostas.AMBASMARCAM, TipoEntrada.QUINA, false));
        padroesAposta.put(Padrao.AMBAS_1x2_JOGOTODO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 1, 2, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true));
        padroesAposta.put(Padrao.AMBAS_2x2_JOGOTODO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 2, 2, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true));
        padroesAposta.put(Padrao.AMBAS_2x1_JOGOTODO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 2, 1, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true));
        padroesAposta.put(Padrao.AMBAS_0x2_INTERVALO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 0, 2, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, false));
        padroesAposta.put(Padrao.AMBAS_1x2_JOGOTODO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 1, 2, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, true));
        padroesAposta.put(Padrao.AMBAS_3x0_JOGOTODO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 3, 0, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, true));
        padroesAposta.put(Padrao.AMBAS_0x1_INTERVALO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 0, 1, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, false));
        padroesAposta.put(Padrao.AMBAS_0x2_INTERVALO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 0, 2, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, false));
        padroesAposta.put(Padrao.AMBAS_3x1_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 3, 1, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.AMBAS_2x0_INTERVALO_QUINTO_HORA_ANTERIOR, (jogo, liga) -> verificarAposta(jogo, liga, 2, 0, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR, false));
        padroesAposta.put(Padrao.AMBAS_0x0_JOGOTODO_QUINTO_HORA_ANTERIOR, (jogo, liga) -> verificarAposta(jogo, liga, 0, 0, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR, true));
        padroesAposta.put(Padrao.AMBAS_0x2_JOGOTODO_QUINTO_HORA_ANTERIOR, (jogo, liga) -> verificarAposta(jogo, liga, 0, 2, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR, true));
        padroesAposta.put(Padrao.AMBAS_3x1_JOGOTODO_QUINTO_HORA_ANTERIOR, (jogo, liga) -> verificarAposta(jogo, liga, 3, 1, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR, true));
        padroesAposta.put(Padrao.OVER25_2x1_JOGOTODO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 2, 1, Apostas.OVER25, TipoEntrada.QUINA, true));
        padroesAposta.put(Padrao.OVER25_2x2_JOGOTODO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 2, 2, Apostas.OVER25, TipoEntrada.QUINA, true));
        padroesAposta.put(Padrao.OVER25_1x1_INTERVALO_QUINA, (jogo, liga) -> verificarAposta(jogo, liga, 1, 1, Apostas.OVER25, TipoEntrada.QUINA, false));
        padroesAposta.put(Padrao.OVER25_0x2_INTERVALO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 0, 2, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, false));
        padroesAposta.put(Padrao.OVER25_2x1_JOGOTODO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 2, 1, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, true));
        padroesAposta.put(Padrao.OVER25_0x3_JOGOTODO_CIMA, (jogo, liga) -> verificarAposta(jogo, liga, 0, 3, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, true));
        padroesAposta.put(Padrao.Over25_0x3_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 0, 3, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.Over25_2x2_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 2, 2, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true));
        padroesAposta.put(Padrao.Over25_3x1_JOGOTODO_QUINTO, (jogo, liga) -> verificarAposta(jogo, liga, 3, 1, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true));

        padroesAposta.put(Padrao.OVER25_0x0INTERVALO_0x1JOGOTODO_QUINA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 0, 0, 1, Apostas.OVER25, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.OVER25_0x0INTERVALO_1x0JOGOTODO_QUINA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 0, 0, Apostas.OVER25, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.OVER25_0x0INTERVALO_2x0JOGOTODO_QUINA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 2, 0, 0, Apostas.OVER25, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.OVER25_0x1INTERVALO_0x1JOGOTODO_QUINA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 0, 1, Apostas.OVER25, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.OVER25_0x1INTERVALO_0x2JOGOTODO_QUINA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 0, 2, Apostas.OVER25, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.OVER25_1x0INTERVALO_2x0JOGOTODO_QUINA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 1, 0, 2, 0, Apostas.OVER25, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.OVER25_1x1INTERVALO_1x1JOGOTODO_QUINA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 1, 1, 1, 1, Apostas.OVER25, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.OVER25_0x0INTERVALO_1x0JOGOTODO_QUINTO, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 0, 1, 0, Apostas.OVER25, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.OVER25_0x2INTERVALO_0x2JOGOTODO_QUINTO, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 2, 0, 2, Apostas.OVER25, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.OVER25_1x0INTERVALO_1x1JOGOTODO_QUINTO, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 1, 0, 1, 1, Apostas.OVER25, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.OVER25_1x0INTERVALO_2x0JOGOTODO_QUINTO, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 1, 0, 2, 0, Apostas.OVER25, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.AMBAS_0x0INTERVALO_0x1JOGOTODO_QUINA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 0, 0, 1, Apostas.AMBASMARCAM, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.AMBAS_0x1INTERVALO_1x2JOGOTODO_QUINA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 1, 2, Apostas.AMBASMARCAM, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.AMBAS_1x1INTERVALO_1x1JOGOTODO_QUINA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 1, 1, 1, 1, Apostas.AMBASMARCAM, TipoEntrada.QUINA));
        padroesAposta.put(Padrao.AMBAS_1x1INTERVALO_1x1JOGOTODO_CIMA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 1, 1, 1, 1, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM));
        padroesAposta.put(Padrao.AMBAS_0x0INTERVALO_0x1JOGOTODO_CIMA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 0, 0, 1, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM));
        padroesAposta.put(Padrao.AMBAS_0x1INTERVALO_0x1JOGOTODO_CIMA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 0, 1, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM));
        padroesAposta.put(Padrao.AMBAS_0x1INTERVALO_0x2JOGOTODO_CIMA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 0, 2, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM));
        padroesAposta.put(Padrao.AMBAS_0x1INTERVALO_1x2JOGOTODO_CIMA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 1, 2, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM));
        padroesAposta.put(Padrao.AMBAS_0x0INTERVALO_1x1JOGOTODO_QUINTO, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 0, 1, 1, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO));
        padroesAposta.put(Padrao.AMBAS_1x0INTERVALO_2x0JOGOTODO_QUINTO, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 1, 0, 2, 0, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO));
        padroesAposta.put(Padrao.AMBAS_0x1INTERVALO_0x2JOGOTODO_QUINTO, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 0, 2, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO));
        padroesAposta.put(Padrao.AMBAS_0x0INTERVALO_1x1JOGOTODO_QUINTO_HORA_ANTERIOR, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 0, 1, 1, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR));
        padroesAposta.put(Padrao.AMBAS_0x1INTERVALO_0x2JOGOTODO_QUINTO_HORA_ANTERIOR, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 0, 2, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR));
        padroesAposta.put(Padrao.AMBAS_0x1INTERVALO_1x1JOGOTODO_QUINTO_HORA_ANTERIOR, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 1, 1, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR));
        padroesAposta.put(Padrao.AMBAS_2x0INTERVALO_2x0JOGOTODO_QUINTO_HORA_ANTERIOR, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 2, 0, 2, 0, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR));
        padroesAposta.put(Padrao.OVER25_0x0INTERVALO_0x1JOGOTODO_CIMA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 0, 0, 1, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM));
        padroesAposta.put(Padrao.OVER25_1x0INTERVALO_1x1JOGOTODO_CIMA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 1, 0, 1, 1, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM));
        padroesAposta.put(Padrao.OVER25_1x1INTERVALO_1x1JOGOTODO_CIMA, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 1, 1, 1, 1, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM));
        padroesAposta.put(Padrao.OVER25_0x1INTERVALO_0x1JOGOTODO_QUINTO, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 0, 1, Apostas.OVER25, TipoEntrada.QUINTO_JOGO));
        padroesAposta.put(Padrao.OVER25_1x1INTERVALO_1x1JOGOTODO_QUINTO, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 1, 1, 1, 1, Apostas.OVER25, TipoEntrada.QUINTO_JOGO));
        padroesAposta.put(Padrao.OVER25_0x1INTERVALO_1x1JOGOTODO_QUINTO_HORA_ANTERIOR, (jogo, liga) -> verificarApostaIntervaloEFullTime(jogo, liga, 0, 1, 1, 1, Apostas.OVER25, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR));


        return padroesAposta;
    }

        public void verificarPadrao(List<AnalisePadroes> analisePadroes){

        List<Jogos> ultimosjogos = new ApiVsStatsService().getUltimosJogosTodasLigas();

        if(ultimosjogos.isEmpty()){
            System.out.println("Token Expirou");
        }

        ultimosjogos.removeIf(j -> {
            return Data.verificarDiaHoraJogoComLondresCasoDiferenteRetornaTrue(j.getIdString());
        });

        Collections.shuffle(ultimosjogos);

        Map<String, BiConsumer<Jogos, String>> padroesAposta = createMapPadroesAposta();

            for (int i = ultimosjogos.size() - 1; i >= 0; i--) {
                try {
                    Jogos jogo = ultimosjogos.get(i);
                    for (AnalisePadroes analise : analisePadroes) {
                        if(Util.isPadraoQuinto(analise)){
                            if (Data.verificarTempoDoPadraoQuintaEntrada(jogo.getIdString())) {
                                String padrao = analise.getPadrao();
                                String liga = analise.getLiga();
                                BiConsumer<Jogos, String> biConsumer = padroesAposta.get(padrao);
                                if (biConsumer != null) {
                                    biConsumer.accept(jogo, liga);
                                }
                            }
                        }else{
                            if (jogo.getMinute() > Data.getMinutoAtualLondon() + 4 && jogo.getMinute() < Data.getMinutoAtualLondon() + 8) {
                                String padrao = analise.getPadrao();
                                String liga = analise.getLiga();
                                BiConsumer<Jogos, String> biConsumer = padroesAposta.get(padrao);
                                if (biConsumer != null) {
                                    biConsumer.accept(jogo, liga);
                                }
                                if(analise.getPadrao().equals(Padrao.OVER25_RESULTADOSIGUAIS_JOGOTODO_QUINA)){
                                    if(ultimosjogos.get(i).getLiga().equals(ultimosjogos.get(i+1).getLiga())){
                                        verificarOver25ResultadosIguais(ultimosjogos.get(i), ultimosjogos.get(i+1), analise.getLiga());
                                    }
                                }

                            }
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }

    private void verificarAposta(Jogos jogo, String liga, int homeScore, int awayScore, String aposta, String tipoEntrada, boolean isFullTime) {
        Score score = isFullTime ? jogo.getScoreFullTime() : jogo.getScoreHalfTime();
        if (jogo.getLiga().equals(liga) && score.getHome() == homeScore && score.getAway() == awayScore) {
            telegramService.montarMensagem(jogo, aposta, tipoEntrada);
        }
    }

    private void verificarApostaIntervaloEFullTime(Jogos jogos, String liga, int homeScoreHalf, int awayScoreHalf, int homeScoreFull, int awayScoreFull, String aposta, String tipoEntrada) {
        if (jogos.getLiga().equals(liga) &&
                jogos.getScoreHalfTime().getHome() == homeScoreHalf &&
                jogos.getScoreHalfTime().getAway() == awayScoreHalf &&
                jogos.getScoreFullTime().getHome() == homeScoreFull &&
                jogos.getScoreFullTime().getAway() == awayScoreFull) {
            telegramService.montarMensagem(jogos, aposta, tipoEntrada);
        }
    }

    private void verificarOver25ResultadosIguais(Jogos primeiroJogo, Jogos segundoJogo, String ligaAnalise) {
        if(primeiroJogo.getLiga().equals(ligaAnalise)){
            if(primeiroJogo.getScoreFullTime().getHome() == segundoJogo.getScoreFullTime().getHome() && primeiroJogo.getScoreFullTime().getAway() == segundoJogo.getScoreFullTime().getAway()) {
                telegramService.montarMensagem(primeiroJogo, Apostas.OVER25, TipoEntrada.QUINA);
            }
        }
    }
}
