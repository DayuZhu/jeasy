package xin.jeasy.mybatis.generator.format;

import xin.jeasy.mybatis.generator.model.TableClass;

import java.util.Properties;

/**
 * @projectName:jeasy01
 * @author:
 * @date:
 */
public interface TemplateMultiFormatter {

    /**
     * 获取根据模板生成的数据
     *
     * @param tableClass
     * @param properties
     * @param targetPackage
     * @param templateContent
     * @param mapperSuffix
     * @return
     */
    String getFormattedContent(TableClass tableClass, Properties properties, String targetPackage,
                               String templateContent, String mapperSuffix);
}
