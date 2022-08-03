package p.zh.assembly.middleware.auth.config.db;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * mybatis 配置
 *
 * @author zhao.peng
 * @date 2022/8/3
 */
@MapperScan("p.zh.assembly.middleware.auth.mapper")
@SpringBootConfiguration
public class MybatisConfig {

    @Bean("dataSource")
    @ConfigurationProperties(prefix ="spring.datasource.druid")
    public DataSource druidDataSource(WallFilter wallFilter) {
        List<Filter> filterList = new ArrayList<>();
        filterList.add(wallFilter);
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setProxyFilters(filterList);
        return druidDataSource;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }




    @Bean
    public WallFilter wallFilter(WallConfig wallConfig) {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        return wallFilter;
    }

    @Bean
    public WallConfig wallConfig() {
        WallConfig config = new WallConfig();
        //允许一次执行多条语句
        config.setMultiStatementAllow(true);
        //允许非基本语句的其他语句
        config.setNoneBaseStatementAllow(true);
        return config;
    }

}
