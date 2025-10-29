package com.droidbattle.service;

import com.droidbattle.model.Droid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BattleService {

    private static Random random = new Random();

    // Логіка бою 1 на 1
    public static void fight1v1(Droid droid1, Droid droid2, BattleLog log) {
        log.add(String.format("===== БІЙ 1 НА 1: %s vs %s =====", droid1.getName(), droid2.getName()));

        Droid attacker = droid1;
        Droid defender = droid2;

        while (droid1.isAlive() && droid2.isAlive()) {

            // --- ДОДАНО БЛОК СТАТУСУ ---
            log.addWithoutDelay("\n--- СТАН БІЙЦІВ ---");
            log.addWithoutDelay("  " + attacker.toString());
            log.addWithoutDelay("  " + defender.toString());
            log.addWithoutDelay("--------------------");

            // Атака
            log.add(attacker.attack(defender));

            // Перевірка, чи живий захисник
            if (!defender.isAlive()) {
                log.add(String.format("💥 %s переможений!", defender.getName()));
                break;
            }

            // Зміна ролей
            Droid temp = attacker;
            attacker = defender;
            defender = temp;
        }

        Droid winner = droid1.isAlive() ? droid1 : droid2;
        log.add(String.format("🏆 Переможець: %s!", winner.getName()));
    }

    // Логіка бою Команда на Команду
    public static void fightTeam(List<Droid> teamA, List<Droid> teamB, BattleLog log) {
        log.add("===== КОМАНДНИЙ БІЙ ПОЧАТО =====");
        log.add("Команда А: " + getTeamNames(teamA));
        log.add("Команда Б: " + getTeamNames(teamB));

        int round = 1;
        while (isTeamAlive(teamA) && isTeamAlive(teamB)) {
            log.add(String.format("\n--- РАУНД %d ---", round++));

            // --- ДОДАНО БЛОК СТАТУСУ ---
            // Виводимо статус миттєво
            log.addWithoutDelay("--- СТАН КОМАНД ---");
            printTeamStatus(teamA, "Команда А", log);
            printTeamStatus(teamB, "Команда Б", log);
            log.addWithoutDelay("--------------------");

            // Хід команди A
            performTeamTurn(teamA, teamB, log);

            // Перевірка, чи не перемогла команда A
            if (!isTeamAlive(teamB)) {
                break;
            }

            // Хід команди B
            performTeamTurn(teamB, teamA, log);
        }

        // Оголошення переможця
        if (isTeamAlive(teamA)) {
            log.add("\n🏆 Перемогла КОМАНДА А!");
        } else {
            log.add("\n🏆 Перемогла КОМАНДА Б!");
        }
    }

    // Допоміжний метод для ходу команди
    private static void performTeamTurn(List<Droid> attackers, List<Droid> defenders, BattleLog log) {
        // Отримуємо список живих захисників
        List<Droid> aliveDefenders = defenders.stream().filter(Droid::isAlive).collect(Collectors.toList());
        if (aliveDefenders.isEmpty()) return; // Якщо захищатись нікому

        // Кожен живий атакувальник робить хід
        for (Droid attacker : attackers) {
            if (attacker.isAlive()) {
                // HealerDroid використовує свою логіку, інші - свою
                log.add(attacker.useSpecialAbility(attackers, aliveDefenders));

                // Оновлюємо список живих захисників, раптом когось вбили
                aliveDefenders = defenders.stream().filter(Droid::isAlive).collect(Collectors.toList());
                if (aliveDefenders.isEmpty()) break;
            }
        }
    }

    // Перевірка, чи жива команда
    private static boolean isTeamAlive(List<Droid> team) {
        return team.stream().anyMatch(Droid::isAlive);
    }

    // Допоміжний метод для красивого виводу
    private static String getTeamNames(List<Droid> team) {
        return team.stream().map(Droid::getName).collect(Collectors.joining(", "));
    }

    // Допоміжний метод для виведення статусу команди без затримки.
    private static void printTeamStatus(List<Droid> team, String teamName, BattleLog log) {
        log.addWithoutDelay(teamName + ":");
        for (Droid droid : team) {
            // toString() вже містить всю потрібну інфо (HP, тощо)
            String status = droid.isAlive() ? droid.toString() : "[ПЕРЕМОЖЕНИЙ] " + droid.getName();
            log.addWithoutDelay("  " + status);
        }
    }
}