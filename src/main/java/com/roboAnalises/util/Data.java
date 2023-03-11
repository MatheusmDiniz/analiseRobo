package com.roboAnalises.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<Integer> getUltimas8HorasLondres(){
        LocalTime lt = LocalTime.now(ZoneId.of("Europe/London"));

        List<Integer> ultimasHoras = new ArrayList<>();
//        ultimasHoras.add(lt.getHour());
        ultimasHoras.add(lt.minusHours(1).getHour());
        ultimasHoras.add(lt.minusHours(2).getHour());
        ultimasHoras.add(lt.minusHours(3).getHour());
        ultimasHoras.add(lt.minusHours(4).getHour());
        ultimasHoras.add(lt.minusHours(5).getHour());
        ultimasHoras.add(lt.minusHours(6).getHour());
        ultimasHoras.add(lt.minusHours(7).getHour());
        ultimasHoras.add(lt.minusHours(8).getHour());

        return ultimasHoras;
    }

    public static Integer getHoraAtualLondon(){
        return LocalTime.now(ZoneId.of("Europe/London")).getHour();
    }

    public static int getMinutoAtualLondon() {
        return LocalTime.now(ZoneId.of("Europe/London")).getMinute();
    }

    public static LocalTime getLtLocalTimeLondon(){
        return LocalTime.now(ZoneId.of("Europe/London"));
    }

    public static List<Integer> getUltimas10HorasLondres(){
        LocalTime lt = LocalTime.now(ZoneId.of("Europe/London"));

        List<Integer> ultimasHoras = new ArrayList<>();
//        ultimasHoras.add(lt.getHour());
        ultimasHoras.add(lt.minusHours(1).getHour());
        ultimasHoras.add(lt.minusHours(2).getHour());
        ultimasHoras.add(lt.minusHours(3).getHour());
        ultimasHoras.add(lt.minusHours(4).getHour());
        ultimasHoras.add(lt.minusHours(5).getHour());
        ultimasHoras.add(lt.minusHours(6).getHour());
        ultimasHoras.add(lt.minusHours(7).getHour());
        ultimasHoras.add(lt.minusHours(8).getHour());
        ultimasHoras.add(lt.minusHours(9).getHour());
        ultimasHoras.add(lt.minusHours(10).getHour());

        return ultimasHoras;
    }

    public static List<Integer> getUltimas3HorasLondres(){
        LocalTime lt = LocalTime.now(ZoneId.of("Europe/London"));

        List<Integer> ultimasHoras = new ArrayList<>();
//        ultimasHoras.add(lt.getHour());
        ultimasHoras.add(lt.minusHours(1).getHour());
        ultimasHoras.add(lt.minusHours(2).getHour());
        ultimasHoras.add(lt.minusHours(3).getHour());
        return ultimasHoras;
    }

    public static Boolean verificarDiaHoraJogoComLondresCasoDiferenteRetornaTrue(String idString) {
        int dataJogo  = Integer.parseInt(idString.substring(6, 8));
        int horaJogo = Integer.parseInt(idString.substring(8, 10));

        int horaLondres = Data.getHoraAtualLondon();

        if(horaJogo != horaLondres && horaJogo != horaLondres-1){
            return true;
        }

        LocalDate ld =  LocalDate.now(ZoneId.of("Europe/London"));

        if(ld.getDayOfMonth() != dataJogo){
            return true;
        }
        return false;
    }

    public static Boolean verificarDiaJogoComLondresCasoDiferenteRetornaTrue(String idString) {
        int anoJogo  = Integer.parseInt(idString.substring(0, 4));
        int mesJogo  = Integer.parseInt(idString.substring(4, 6));
        int diaJogo  = Integer.parseInt(idString.substring(6, 8));
        int horaJogo = Integer.parseInt(idString.substring(8, 10));
        int minutoJogo = Integer.parseInt(idString.substring(10, 12));
        LocalDateTime ldJogo = LocalDateTime.of(anoJogo, mesJogo, diaJogo,horaJogo,minutoJogo);


        LocalDateTime ld =  LocalDateTime.now(ZoneId.of("Europe/London")).minusHours(10);

        if(ldJogo.isBefore(ld)){
            return true;
        }
        return false;
    }

    public static Boolean verificarDiaHoraJogoComLondresMenos3CasoDiferenteRetornaTrue(String idString) {
        int anoJogo  = Integer.parseInt(idString.substring(0, 4));
        int mesJogo  = Integer.parseInt(idString.substring(4, 6));
        int diaJogo  = Integer.parseInt(idString.substring(6, 8));
        int horaJogo = Integer.parseInt(idString.substring(8, 10));
        int minutoJogo = Integer.parseInt(idString.substring(10, 12));
        LocalDateTime ldJogo = LocalDateTime.of(anoJogo, mesJogo, diaJogo,horaJogo,minutoJogo);


        LocalDateTime ld =  LocalDateTime.now(ZoneId.of("Europe/London")).minusHours(3);

        if(ldJogo.isBefore(ld)){
            return true;
        }
        return false;
    }

    public static boolean verificarTempoDoPadraoQuintaEntrada(String idString) {
        int horaJogo = Integer.parseInt(idString.substring(8, 10));
        int minutoJogo = Integer.parseInt(idString.substring(10, 12));
        LocalTime ltJogo = LocalTime.of(horaJogo, minutoJogo);

        LocalTime ltLondon = Data.getLtLocalTimeLondon().minusMinutes(7);

        if(ltJogo.isAfter(ltLondon)){
            return true;
        }

        return false;
    }


    public static boolean verificarUltimoMinutoJogoMaiorHoraLondresRetornaTrue(int hora, int minutoJogo, String data) {
        int anoJogo  = Integer.parseInt(data.substring(0, 4));
        int mesJogo  = Integer.parseInt(data.substring(5, 7));
        int diaJogo  = Integer.parseInt(data.substring(8));
        LocalDateTime ldJogo = LocalDateTime.of(anoJogo, mesJogo, diaJogo,hora,minutoJogo);
        ldJogo = ldJogo.plusMinutes(5);

        LocalDateTime ld =  LocalDateTime.now(ZoneId.of("Europe/London"));

        if(ldJogo.isBefore(ld)){
            return true;
        }
        return false;

    }

    public static boolean filtraApenasJogosUltimaHora(String idString) {
        int anoJogo  = Integer.parseInt(idString.substring(0, 4));
        int mesJogo  = Integer.parseInt(idString.substring(4, 6));
        int diaJogo  = Integer.parseInt(idString.substring(6, 8));
        int horaJogo = Integer.parseInt(idString.substring(8, 10));
        int minutoJogo = Integer.parseInt(idString.substring(10, 12));
        LocalDateTime ldJogo = LocalDateTime.of(anoJogo, mesJogo, diaJogo,horaJogo,minutoJogo);


        LocalDateTime ld =  LocalDateTime.now(ZoneId.of("Europe/London")).minusHours(1);

        if(ldJogo.isBefore(ld)){
            return true;
        }
        return false;
    }

    public static boolean filtroJogosHoraAtual(String idString) {
        int anoJogo  = Integer.parseInt(idString.substring(0, 4));
        int mesJogo  = Integer.parseInt(idString.substring(4, 6));
        int diaJogo  = Integer.parseInt(idString.substring(6, 8));
        int horaJogo = Integer.parseInt(idString.substring(8, 10));
        LocalDateTime ldJogo = LocalDateTime.of(anoJogo, mesJogo, diaJogo,horaJogo, 0).minusMinutes(1);


        LocalDateTime ld =  LocalDateTime.now(ZoneId.of("Europe/London")).minusHours(1);

        if(ldJogo.isBefore(ld)){
            return true;
        }
        return false;
    }

    public static boolean filtroJogosDiaAtual(String idString) {
        int diaJogo  = Integer.parseInt(idString.substring(6, 8));


        LocalDate ld =  LocalDate.now(ZoneId.of("Europe/London"));

        if(diaJogo != ld.getDayOfMonth()){
            return true;
        }
        return false;
    }

    public static boolean entradaEJogoMesmoDia(String data, String idString) {
        int diaJogo  = Integer.parseInt(idString.substring(6, 8));
        int mesJogo  = Integer.parseInt(idString.substring(4, 6));
        int diaEntrada = Integer.parseInt(data.substring(8,10));
        int mesEntrada  = Integer.parseInt(data.substring(5, 7));
        if(diaEntrada == diaJogo && mesJogo == mesEntrada){
            return true;
        }

        return false;
    }
}
