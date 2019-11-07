package xin.jeasy.mybatis.generator.plugin;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.internal.util.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.jeasy.mybatis.generator.file.GenerateByTemplateFile;
import xin.jeasy.mybatis.generator.format.TemplateFormatter;
import xin.jeasy.mybatis.generator.model.TableClass;
import xin.jeasy.mybatis.generator.model.TableColumnBuilder;
import xin.jeasy.mybatis.generator.model.TemplateInfo;
import xin.jeasy.mybatis.generator.util.CommonUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 功能描述:
 *
 * @className:TemplateFileListPlugin
 * @projectName:jeasy01
 * @author:Dayu
 * @date: 2019/11/7 15:03
 */
public class TemplateFileListPlugin extends PluginAdapter {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(TemplateFilePlugin.class);

    /**
     * 默认的模板格式化类
     */
    private static final String DEFAULT_TEMPLATEFORMATTER
            = "xin.jeasy.mybatis.generator.format.FreemarkerTemplateFormatter";

    /**
     * 项目路径（目录需要已经存在）
     */
    private String targetProject;

    /**
     * 模板生成器
     */
    private Object templateFormatter;

    private String templateFormatterClass;

    private String templateRoot;

    private List<TemplateInfo> temInfo = new ArrayList<>();


    @Override
    public boolean validate(List<String> warnings) {

        if (CollectionUtils.isEmpty(temInfo)) {
            LOG.error("没有配置 templateInfo 信息!");
            warnings.add("没有配置 templateInfo 信息!");
            return false;
        }

        for (TemplateInfo templateInfo : temInfo) {
            if (StringUtils.isBlank(templateInfo.getFileName())
                    || StringUtils.isBlank(templateInfo.getMapperSuffix())
                    || StringUtils.isBlank(templateInfo.getTargetPackage())
                    || StringUtils.isBlank(templateInfo.getTemplatePath())) {
                LOG.error("templateInfo信息存在空值!");
                warnings.add("templateInfo信息存在空值!");
                return false;
            }
        }


        if (!StringUtility.stringHasValue(templateRoot)) {
            LOG.error("没有配置 templateRoot 路径!");
            warnings.add("没有配置 templateRoot 路径!");
            return false;
        }

        if (!StringUtility.stringHasValue(targetProject)) {
            LOG.error("没有配置 targetProject 路径!");
            warnings.add("没有配置 targetProject 路径!");
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
            warnings.add("初始化 templateFormatter 出错:" + e.getMessage());
            LOG.error("初始化 templateFormatter 出错", e);
            return false;
        }


        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> list = new ArrayList<>();
        TableClass tableClass = TableColumnBuilder.build(introspectedTable);

        for (TemplateInfo templateInfo : temInfo) {
            String tContent = null;

            try {
                tContent = CommonUtility.getTemplateContent(templateInfo.getTemplatePath());
            } catch (IOException e) {

                LOG.error("读取模板内容失败{}", templateInfo.getTemplatePath());
            }

            if (null == tContent) {
                continue;
            }

            properties.setProperty("mapperSuffix", templateInfo.getMapperSuffix());

            list.add(new GenerateByTemplateFile(tableClass,
                    (TemplateFormatter) templateFormatter,
                    properties,
                    targetProject,
                    templateInfo.getTargetPackage(),
                    templateInfo.getFileName(),
                    tContent));
        }


        return list;
    }

    /**
     * Property:templateInfoes = templatePath-mapperSuffix-fileName-targetPackage;
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        String templateInfo = properties.getProperty("templateInfo").trim();
        properties.remove("templateInfo");
        super.setProperties(properties);
        this.targetProject = properties.getProperty("targetProject");
        this.templateRoot = properties.getProperty("templateRoot");
        this.templateFormatterClass = properties.getProperty("templateFormatter");
        String[] split = StringUtils.split(templateInfo, ";");
        for (String str : split) {
            String[] info = StringUtils.split(str.trim(), "-");
            TemplateInfo tInfo = new TemplateInfo();
            tInfo.setTemplatePath(templateRoot + info[0]);
            tInfo.setMapperSuffix(info[1]);
            tInfo.setFileName(info[2]);
            tInfo.setTargetPackage(info[3]);
            this.temInfo.add(tInfo);

        }


    }

}
