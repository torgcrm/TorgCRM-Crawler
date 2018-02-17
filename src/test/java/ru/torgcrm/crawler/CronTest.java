package ru.torgcrm.crawler;

import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import org.junit.Test;

import java.time.ZonedDateTime;

public class CronTest {
    @Test
    public void cronTest() {
        String cron = "11 22 * * *";
        CronDefinition cronDefinition =
                CronDefinitionBuilder.defineCron()
                        .withMinutes().and()
                        .withHours().and()
                        .withDayOfMonth().and()
                        .withMonth().and()
                        .withDayOfWeek().and().instance();
        CronParser cronParser = new CronParser(cronDefinition);
        Cron c = cronParser.parse(cron);
        ZonedDateTime now = ZonedDateTime.now();
        ExecutionTime executionTime = ExecutionTime.forCron(c);
        ZonedDateTime nextExecution = executionTime.nextExecution(now).get();
        ZonedDateTime lastExecution = executionTime.lastExecution(now).get();

        System.out.println(nextExecution.compareTo(now));
        System.out.println(lastExecution);
    }
}
