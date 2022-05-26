package com.lagou.edu;

import com.lagou.edu.config.DataSourceConfig;
import org.springframework.context.annotation.*;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/25 11:28 AM
 */
@Configuration
@ComponentScan(basePackages = {"com.lagou.edu"})
@Import({DataSourceConfig.class})
public class SpringConfig {

}
