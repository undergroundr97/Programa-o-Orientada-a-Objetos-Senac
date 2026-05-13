package Utilitario;

public class ConversorTempo {
    public static void formatarMinutos(Integer minutos){
        Integer horas = minutos / 60;
        Integer min = minutos % 60;
        System.out.printf("A midia tem: %d Horas e %d minutos", horas, min);
    }
}
