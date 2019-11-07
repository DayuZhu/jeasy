package xin.jeasy.mybatis.generator.format;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import xin.jeasy.mybatis.generator.model.TableClass;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

/**
 * 功能描述:基于 freemarker 的实现
 *
 * @className:FreemarkerMultiTemplateFormatter
 * @projectName:jeasy01
 * @author:
 * @date:
 */
public class FreemarkerMultiTemplateFormatter implements TemplateMultiFormatter {
    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_23);
    private static final StringTemplateLoader TEMPLATELOADER = new StringTemplateLoader();

    public FreemarkerMultiTemplateFormatter() {
        CONFIGURATION.setLocale(Locale.CHINA);
        CONFIGURATION.setDefaultEncoding("UTF-8");
        CONFIGURATION.setTemplateLoader(TEMPLATELOADER);
        CONFIGURATION.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
    }

    /**
     * 根据模板处理
     *
     * @param templateName
     * @param templateSource
     * @param params
     * @return
     */
    public String process(String templateName, String templateSource, Map<String, Object> params) {
        try {
            Template template = new Template(templateName, templateSource, CONFIGURATION);
            Writer writer = new StringWriter();
            template.process(params, writer);
            return writer.toString();
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getFormattedContent(TableClass tableClass, Properties properties, String targetPackage,
                                      String templateContent, String mapperSuffix) {
        Map<String, Object> params = new HashMap<>(16);
        for (Object o : properties.keySet()) {
            params.put(String.valueOf(o), properties.get(o));
        }
        params.put("mapperSuffix", mapperSuffix);
        params.put("props", properties);
        params.put("package", targetPackage);
        params.put("tableClass", tableClass);
        return process(properties.getProperty("templatePath"), templateContent, params);
    }


}
