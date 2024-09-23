package com.JJH.homebroker.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling // Habilita o agendamento de tarefas
public class DateController{

    public static void main(String[] args) {
        SpringApplication.run(DateController.class, args);

        // Instanciação manual da classe ScheduledTasks
        ScheduledTasks tasks = new ScheduledTasks();
        tasks.executeTaskOnce(); // Método para executar manualmente uma vez, se necessário
    }
}

@Component
class ScheduledTasks {

    @Scheduled(fixedRate = 5000) // Executa a cada 5 segundos
    public void executeScheduledTask() {
        System.out.println("Executando tarefa agendada a cada 5 segundos");
    }

    // Método para executar manualmente
    public void executeTaskOnce() {
        System.out.println("Executando tarefa manualmente.");
    }
}
