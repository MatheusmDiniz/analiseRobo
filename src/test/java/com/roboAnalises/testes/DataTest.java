package com.roboAnalises.testes;

import com.roboAnalises.domain.AnalisePadroes;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.service.ApiVsStatsService;
import com.roboAnalises.service.InformacoesService;
import com.roboAnalises.util.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class DataTest {



    @Test
    public void verificarUltimoMinutoJogoMaiorHoraLondresRetornaTrue() {
        Data.verificarUltimoMinutoJogoMaiorHoraLondresRetornaTrue(22, 38, "2023-03-06");
    }

    @Test
    public void entradaEJogoMesmoDia() {
        Data.entradaEJogoMesmoDia("2023-03-12", "2023031104150");
    }

}