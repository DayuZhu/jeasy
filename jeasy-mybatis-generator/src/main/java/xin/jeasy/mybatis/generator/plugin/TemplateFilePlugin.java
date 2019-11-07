package xin.jeasy.mybatis.generator.plugin;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.internal.util.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.jeasy.mybatis.generator.file.GenerateByListTemplateFile;
import xin.jeasy.mybatis.generator.file.GenerateByTemplateFile;
import xin.jeasy.mybatis.generator.format.ListTemplateFormatter;
import xin.jeasy.mybatis.generator.format.TemplateFormatter;
import xin.jeasy.mybatis.generator.model.TableClass;
import xin.jeasy.mybatis.generator.model.TableColumnBuilder;
import xin.jeasy.mybatis.generator.util.CommonUtility;

import java.io.IOException;
import java.util.*;

/**
 * 功能描述:
 *
 * @className:TemplateFilePlugin
 * @projectName:jeasy01
 * @author:Dayu
 * @date:
 */

public class TemplateFilePlugin extends PluginAdapter {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(TemplateFilePlugin.class);

    /**
     * 字符TRUE
     */
    private static final String BOOLEAN_TRUE = "TRUE";

    /**
     * 默认的模板格式化类
     */
    private static final String DEFAULT_TEMPLATEFORMATTER
            = "xin.jeasy.mybatis.generator.format.FreemarkerTemplateFormatter";
    /**
     * 单个文件模式
     */
    private String singleMode;
    /**
     * 项目路径（目录需要已经存在）
     */
    private String targetProject;
    /**
     * 生成的包（路径不存在则创建）
     */
    private String targetPackage;
    /**
     * 模板路径
     */
    private String templatePath;
    /**
     * 模板内容
     */
    private String templateContent;
    /**
     * 文件名模板，通过模板方式生成文件名，包含后缀
     */
    private String fileName;
    /**
     * 模板生成器
     */
    private Object templateFormatter;
    /**
     * 指定模板生成器
     */
    private String templateFormatterClass;

    /**
     * 所有表字段存储
     */
    private Set<TableClass> cacheTables;

    @Override
    public boolean validate(List<String> warnings) {
        if (!StringUtility.stringHasValue(fileName)) {
            LOG.error("没有配置 fileName 文件名模板，因此不会生成任何额外代码!");
            warnings.add("没有配置 fileName 文件名模板，因此不会生成任何额外代码!");
            return false;
        }

        if (!StringUtility.stringHasValue(templatePath)) {
            LOG.error("没有配置 templatePath 模板路径，因此不会生成任何额外代码!");
            warnings.add("没有配置 templatePath 模板路径，因此不会生成任何额外代码!");
            return false;
        }


        if (!StringUtility.stringHasValue(targetProject)) {
            LOG.error("没有配置 targetProject 路径!");
            warnings.add("没有配置 targetProject 路径!");
            return false;
        }
        if (!StringUtility.stringHasValue(targetPackage)) {
            LOG.error("没有配置 targetPackage 路径!");
            warnings.add("没有配置 targetPackage 路径!");
            return false;
        }

        try {
            templateContent = CommonUtility.getTemplateContent(templatePath);
        } catch (IOException e) {
            LOG.error("读取模板文件出错", e);
            warnings.add("读取模板文件出错: " + e.getMessage());
            return false;
        }

        if (!StringUtility.stringHasValue(templateFormatterClass)) {
            templateFormatterClass = DEFAULT_TEMPLATEFORMATTER;
            LOG.warn("没有配置 templateFormatterClass 模板处理器，使用默认的处理器!");
            warnings.add("没有配置 templateFormatterClass 模板处理器，使用默认的处理器!");
        }

        try {
            templateFormatter = Class.forName(templateFormatterClass).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            LOG.error("初始化 templateFormatter 出错", e);
            warnings.add("初始化 templateFormatter 出错:" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> list = new ArrayList<>();
        TableClass tableClass = TableColumnBuilder.build(introspectedTable);
        if (BOOLEAN_TRUE.equalsIgnoreCase(singleMode)) {
            list.add(new GenerateByTemplateFile(tableClass,
                    (TemplateFormatter) templateFormatter,
                    properties, targetProject, targetPackage, fileName, templateContent));
        } else {
            cacheTables.add(tableClass);
        }
        return list;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        List<GeneratedJavaFile> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(cacheTables)) {
            list.add(new GenerateByListTemplateFile(
                    cacheTables,
                    (ListTemplateFormatter) templateFormatter,
                    properties,
                    targetProject,
                    targetPackage,
                    fileName,
                    templateContent));
        }
        return list;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.singleMode = properties.getProperty("singleMode", BOOLEAN_TRUE);
        if (!BOOLEAN_TRUE.equalsIgnoreCase(singleMode)) {
            this.cacheTables = new LinkedHashSet<>();
        }
        this.targetProject = properties.getProperty("targetProject");
        this.targetPackage = properties.getProperty("targetPackage");
        this.templatePath = properties.getProperty("templatePath");
        this.fileName = properties.getProperty("fileName");
        this.templateFormatterClass = properties.getProperty("templateFormatter");
    }
}
