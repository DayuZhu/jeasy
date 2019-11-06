package xin.jeasy.mybatis.generator.format;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import xin.jeasy.mybatis.generator.model.TableClass;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 功能描述:基于 freemarker 的实现
 *
 * @className:FreemarkerTemplateFormatter
 * @projectName:jeasy01
 * @author:
 * @date:
 */
public class FreemarkerTemplateFormatter implements TemplateFormatter, ListTemplateFormatter {
    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_23);
    private static final StringTemplateLoader TEMPLATELOADER = new StringTemplateLoader();

    public FreemarkerTemplateFormatter() {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getFormattedContent(TableClass tableClass, Properties properties, String targetPackage,
                                      String templateContent) {
        Map<String, Object> params = new HashMap<>(16);
        for (Object o : properties.keySet()) {
            params.put(String.valueOf(o), properties.get(o));
        }
        params.put("props", properties);
        params.put("package", targetPackage);
        params.put("tableClass", tableClass);
        return process(properties.getProperty("templatePath"), templateContent, params);
    }

    @Override
    public String getFormattedContent(Set<TableClass> tableClassSet, Properties properties, String targetPackage,
                                      String templateContent) {
        Map<String, Object> params = new HashMap<>(16);
        for (Object o : properties.keySet()) {
            params.put(String.valueOf(o), properties.get(o));
        }
        params.put("props", properties);
        params.put("package", targetPackage);
        params.put("tableClassSet", tableClassSet);
        return process(properties.getProperty("templatePath"), templateContent, params);
    }
}
