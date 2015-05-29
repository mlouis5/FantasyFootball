/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball;

import com.design.perpetual.fantasyfootball.app.dataminer.concrete.PlayerRetriever;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author MacDerson
 */
@Configuration
@ComponentScan({"com.design.perpetual.fantasyfootball.app"})
@Import({ DataSourceConfiguration.class})
public class Application {
    
    public static void main(String[] args) throws IOException{
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        PlayerRetriever pr = ctx.getBean(PlayerRetriever.class);
        pr.retrieve();
    }
}
