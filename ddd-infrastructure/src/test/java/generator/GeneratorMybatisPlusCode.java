package generator;


import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.SupperMapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;

/**
 * <p>
 * 生成 mybatis-plus 代码
 * </p>
 *
 * @author chen
 * @date 2017/12/18
 */
// @SpringBootTest(classes = DataSource.class)
// @ImportAutoConfiguration(classes = DataSourceAutoConfiguration.class)
public class GeneratorMybatisPlusCode {
    private static final String parentPackageName = SupperMapper.class.getPackage().getName();
    private static final String generatorMybatisPlusCodeDir = System.getProperty("user.dir") + "/target/main/java";

    private static final String[] tableNames = {};

    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Autowired
    private DataSource dataSource;
    
    
    // @Test
    public void generatorMybatisPlusCode() throws Exception {
        String url;
        String username;
        String password;
        String driverName;


        driverName = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://127.0.0.1:3306/ddd?characterEncoding=utf8&useSSL=false";
        username = "root";
        password = "root";


        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(url, username, password)
                .build();

        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .addInclude(tableNames)
                .enableCapitalMode()
                .enableSkipView()
                .addTablePrefix("ddd")
                .addFieldPrefix("")

                .mapperBuilder()
                .superClass(SupperMapper.class)
                .enableBaseResultMap()
                .enableBaseColumnList()

                .entityBuilder()
                .formatFileName("%sDO")
                .naming(NamingStrategy.underline_to_camel)
                .addSuperEntityColumns("")
                // .superClass(Object.class)
                .enableLombok()
                .enableTableFieldAnnotation()
                // .enableRemoveIsPrefix()
                .enableActiveRecord()
                .idType(IdType.INPUT)
                .versionColumnName("version")
                .versionPropertyName("version")
                .logicDeleteColumnName("is_deleted")
                .logicDeletePropertyName("isDeleted")

                .controllerBuilder()
                .enableRestStyle()

                .build();

        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent(parentPackageName)
                .moduleName("")
                .entity("dataobject")
                .mapper("mybatismapper")
                .xml("mybatismapper.xml")
                .service(null)
                .serviceImpl(null)
                .controller(null)
                .build();

        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .outputDir(generatorMybatisPlusCodeDir)
                .openDir(true)
                .fileOverride()
                .author("chen")
                .enableSwagger()
                .dateType(DateType.TIME_PACK)
                .build();

        new AutoGenerator(dataSourceConfig)
                .strategy(strategyConfig)
                .packageInfo(packageConfig)
                .global(globalConfig)
                .execute();
    }
}
