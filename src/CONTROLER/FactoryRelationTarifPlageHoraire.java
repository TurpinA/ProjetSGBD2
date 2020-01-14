package CONTROLER;

import MODEL.PlageHoraire;
import MODEL.RelationTarifPlageHoraire;
import MODEL.Tarif;

import javafx.util.Pair;
import java.time.LocalTime;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

public class FactoryRelationTarifPlageHoraire {

    public static List<RelationTarifPlageHoraire> CreateFromPlageHoraire(PlageHoraire plageHoraire, Tarif tarifCreux, Tarif tarifPlein){

        List<RelationTarifPlageHoraire> result = new ArrayList<RelationTarifPlageHoraire>();
        Double powerPerMinute = plageHoraire.getPuissanceConsommee()/ MINUTES.between(plageHoraire.getHeureDebut(),plageHoraire.getHeureFin());

        ArrayList<Pair<Tarif,ArrayList<LocalTime>>> ListHoraires = new ArrayList<>();
        ArrayList<LocalTime> horaire1 = new ArrayList<>();
        ArrayList<LocalTime> horaire2 = new ArrayList<>();
        ArrayList<LocalTime> horaire3 = new ArrayList<>();
        ArrayList<LocalTime> horaire4 = new ArrayList<>();
        horaire1.add(LocalTime.of(0,0));
        horaire1.add(LocalTime.of(7,0));
        horaire2.add(LocalTime.of(7,0));
        horaire2.add(LocalTime.of(12,0));
        horaire3.add(LocalTime.of(12,0));
        horaire3.add(LocalTime.of(15,0));
        horaire4.add(LocalTime.of(15,0));
        horaire4.add(LocalTime.of(23,59));
        Pair<Tarif, ArrayList<LocalTime>> tarif1 = new Pair<>(tarifCreux,horaire1);
        Pair<Tarif, ArrayList<LocalTime>> tarif2 = new Pair<>(tarifPlein,horaire2);
        Pair<Tarif, ArrayList<LocalTime>> tarif3 = new Pair<>(tarifCreux,horaire3);
        Pair<Tarif, ArrayList<LocalTime>> tarif4 = new Pair<>(tarifPlein,horaire4);
        ListHoraires.add(tarif1);
        ListHoraires.add(tarif2);
        ListHoraires.add(tarif3);
        ListHoraires.add(tarif4);

        LocalTime parcours = plageHoraire.getHeureDebut();
        while(parcours.isBefore(plageHoraire.getHeureFin())){
            for (Pair<Tarif, ArrayList<LocalTime>> pair :
                    ListHoraires) {
                LocalTime heureDebut = pair.getValue().get(0);
                LocalTime heureFin = pair.getValue().get(1);
                if((parcours.isAfter(heureDebut) || parcours.equals(heureDebut)) && parcours.isBefore(heureFin)){
                    if(plageHoraire.getHeureFin().isBefore(heureFin)){
                        result.add(new RelationTarifPlageHoraire(plageHoraire,pair.getKey(),powerPerMinute*MINUTES.between(parcours,plageHoraire.getHeureFin())));
                        parcours = plageHoraire.getHeureFin();
                        break;
                    }else{
                        result.add(new RelationTarifPlageHoraire(plageHoraire,pair.getKey(),powerPerMinute*MINUTES.between(parcours,heureFin)));
                        parcours = heureFin;
                    }
                }
            }
        }

        return result;
    }

}
