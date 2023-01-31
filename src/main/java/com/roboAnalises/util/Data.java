package com.roboAnalises.util;

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
}
