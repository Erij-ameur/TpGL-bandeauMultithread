package bandeau;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExerciceAvecThreads {

    public static void main(String[] args) {
        ExerciceAvecThreads instance = new ExerciceAvecThreads();
        instance.exemple();
    }

    public void exemple() {

        // Création des bandeaux
        var b1 = new Bandeau();
        var b2 = new Bandeau();
        var b3 = new Bandeau();

        // Création des scénarios avec effets aléatoires pour chaque bandeau
        Scenario s1 = makeRandomScenario();
        Scenario s2 = makeRandomScenario();
        Scenario s3 = makeRandomScenario();

        // On joue les scénarios en parallèle sur les bandeaux respectifs
        System.out.println("CTRL-C pour terminer le programme");

        // On crée un thread pour chaque scénario et bandeau
        Thread t1 = new Thread(new Scenario.ScenarioRunnable(s1, b1));
        Thread t2 = new Thread(new Scenario.ScenarioRunnable(s2, b2));
        Thread t3 = new Thread(new Scenario.ScenarioRunnable(s3, b3));

        // Lancer les threads
        t1.start();
        t2.start();
        t3.start();
    }

    // Méthode pour générer un scénario avec des effets aléatoires
    private Scenario makeRandomScenario() {
        Scenario s = new Scenario();
        Random rand = new Random();

        // Liste des effets disponibles
        List<Effect> availableEffects = new ArrayList<>();
        availableEffects.add(new TeleType(" Affichage caractère par caractère", 100));
        availableEffects.add(new Blink(" Clignotement", 100));
        availableEffects.add(new Zoom(" Zoom", 50));
        availableEffects.add(new FontEnumerator(10));
        availableEffects.add(new Rainbow("Arc-en-ciel", 30));
        availableEffects.add(new Rotate("Rotation", 180, 4000, true));

        // Nombre d'effets aléatoires à appliquer
        int numberOfEffects = rand.nextInt(3) + 3;
        for (int i = 0; i < numberOfEffects; i++) {
            // Choisir un effet aléatoire parmi ceux disponibles
            Effect randomEffect = availableEffects.get(rand.nextInt(availableEffects.size()));
            // Ajouter l'effet avec un temps d'affichage aléatoire
            s.addEffect(randomEffect, rand.nextInt(5) + 1);
        }

        return s;
    }
}