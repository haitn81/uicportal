package vn.com.datech.uic.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * The type Thread config.
 */
@Configuration
public class ThreadConfig {

    /**
     * The constant CORE_POOL_SIZE.
     */
    public static final int CORE_POOL_SIZE = 4;

    /**
     * The constant MAX_POOL_SIZE.
     */
    public static final int MAX_POOL_SIZE = 4;

    /**
     * Thread pool task executor task executor.
     *
     * @return the task executor
     */
    @Bean
    @Primary
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setThreadNamePrefix("sku");
        executor.initialize();

        return executor;
    }
}
