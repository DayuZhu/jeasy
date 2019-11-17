package com.mybatis.generator.junit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Resources;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * @author dayu
 */
public class GeneratorCodeTests {

    static {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            // 加载logback配置文件
            configurator.doConfigure(Resources.getResource("logback.xml"));
        } catch (JoranException e) {
            e.printStackTrace();
        }
        // 加载logj配置文件
        // PropertyConfigurator.configure("/home/dayu/Workspaces/MyEclipse_data/gooddeep/src/main/java/config/log4j.properties");

    }

    private static final Logger LOG = LoggerFactory.getLogger(GeneratorCodeTests.class);

    @Test
    public void generatorCode01() {
        LOG.info("开始 generatorCode01");
        try {
            System.out.println("start generator ...");
            List<String> warnings = new ArrayList<>();
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(
                    Resources.getResource("generatorConfig.xml").openStream());
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            System.out.println("end generator!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("结束 generatorCode01");

    }

}