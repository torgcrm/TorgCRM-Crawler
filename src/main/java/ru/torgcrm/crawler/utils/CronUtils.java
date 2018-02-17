package ru.torgcrm.crawler.utils;

import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class CronUtils {

    /**
     * CRON format is * * * * *
     */
    private static CronDefinition getCronDefinition() {
        CronDefinition cronDefinition =
                CronDefinitionBuilder.defineCron()
                        .withMinutes().and()
                        .withHours().and()
                        .withDayOfMonth().and()
                        .withMonth().and()
                        .withDayOfWeek().and().instance();
        CronParser cronParser = new CronParser(cronDefinition);
        return cronDefinition;
    }

    public static ZonedDateTime lastExecution(String cron) {
        CronParser cronParser = new CronParser(getCronDefinition());
        Cron c = cronParser.parse(cron);
        ZonedDateTime now = ZonedDateTime.now();
        ExecutionTime executionTime = ExecutionTime.forCron(c);
        return executionTime.lastExecution(now).get();
    }
}
