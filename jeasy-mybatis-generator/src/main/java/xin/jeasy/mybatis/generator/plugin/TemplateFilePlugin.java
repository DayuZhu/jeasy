package xin.jeasy.mybatis.generator.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.StringUtility;

import xin.jeasy.mybatis.generator.file.GenerateByListTemplateFile;
import xin.jeasy.mybatis.generator.file.GenerateByTemplateFile;
import xin.jeasy.mybatis.generator.format.ListTemplateFormatter;
import xin.jeasy.mybatis.generator.format.TemplateFormatter;
import xin.jeasy.mybatis.generator.model.TableClass;
import xin.jeasy.mybatis.generator.model.TableColumnBuilder;

/**
 * 每一个模板都需要配置一个插件，可以配置多个
 * <p>
 * <pre>
 * &lt;plugin type="xxx.TemplateFilePlugin"&gt;
 *      &lt;property name="targetProject"     value="src/main/java"/&gt;
 *      &lt;property name="targetPackage"     value="com.xxx.controller"/&gt;
 *      &lt;property name="templatePath"      value="template/controller.ftl"/&gt;
 *      &lt;property name="fileName"          value="XXXController.java"/&gt;
 *      &lt;property name="templateFormatter" value="xxx.FreemarkerTemplateFormatter"/&gt;
 * &lt;/plugin&gt;
 * </pre>
 */
public class TemplateFilePlugin extends PluginAdapter {
    /**
     * 默认的模板格式化类
     */
    public static final String DEFAULT_TEMPLATEFORMATTER = "xin.jeasy.mybatis.generator.format.FreemarkerTemplateFormatter";
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
    private String templateFormatterClass;
    private Set<TableClass> cacheTables;

    /**
     * 列转换为字段
     *
     * @param introspectedColumn
     * @return
     */
    public static Field convertToJavaBeansField(IntrospectedColumn introspectedColumn) {
        FullyQualifiedJavaType fqjt = introspectedColumn.getFullyQualifiedJavaType();
        String property = introspectedColumn.getJavaProperty();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(fqjt);
        field.setName(property);
        return field;
    }

    /**
     * 读取文件
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    protected String read(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String line = reader.readLine();
        while (line != null) {
            stringBuffer.append(line).append("\n");
            line = reader.readLine();
        }
        return stringBuffer.toString();
    }

    @Override
    public boolean validate(List<String> warnings) {
        boolean right = true;
        if (!StringUtility.stringHasValue(fileName)) {
            warnings.add("没有配置 \"fileName\" 文件名模板，因此不会生成任何额外代码!");
            right = false;
        }
        if (!StringUtility.stringHasValue(templatePath)) {
            warnings.add("没有配置 \"templatePath\" 模板路径，因此不会生成任何额外代码!");
            right = false;
        } else {
            try {
                URL resourceUrl = null;
                try {
                    resourceUrl = ObjectFactory.getResource(templatePath);
                } catch (Exception e) {
                    warnings.add("本地加载\"templatePath\" 模板路径失败，尝试 URL 方式获取!");
                }
                if (resourceUrl == null) {
                    resourceUrl = new URL(templatePath);
                }
                InputStream inputStream = resourceUrl.openConnection().getInputStream();
                templateContent = read(inputStream);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                warnings.add("读取模板文件出错: " + e.getMessage());
                right = false;
            }
        }
        if (!StringUtility.stringHasValue(templateFormatterClass)) {
            templateFormatterClass = DEFAULT_TEMPLATEFORMATTER;
            warnings.add("没有配置 \"templateFormatterClass\" 模板处理器，使用默认的处理器!");
        }
        try {
            templateFormatter = Class.forName(templateFormatterClass).newInstance();
        } catch (Exception e) {
            warnings.add("初始化 templateFormatter 出错:" + e.getMessage());
            right = false;
        }
        if (!right) {
            return false;
        }
        int errorCount = 0;
        if (!StringUtility.stringHasValue(targetProject)) {
            errorCount++;
            warnings.add("没有配置 \"targetProject\" 路径!");
        }
        if (!StringUtility.stringHasValue(targetPackage)) {
            errorCount++;
            warnings.add("没有配置 \"targetPackage\" 路径!");
        }
        if (errorCount >= 2) {
            warnings.add("由于没有配置任何有效路径，不会生成任何额外代码!");
            return false;
        }
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> list = new ArrayList<GeneratedJavaFile>();
        TableClass tableClass = TableColumnBuilder.build(introspectedTable);
        if ("TRUE".equalsIgnoreCase(singleMode)) {
            list.add(new GenerateByTemplateFile(tableClass, (TemplateFormatter) templateFormatter, properties, targetProject, targetPackage, fileName, templateContent));
        } else {
            cacheTables.add(tableClass);
        }
        return list;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        List<GeneratedJavaFile> list = new ArrayList<GeneratedJavaFile>();
        if (cacheTables != null && cacheTables.size() > 0) {
            list.add(new GenerateByListTemplateFile(cacheTables, (ListTemplateFormatter) templateFormatter, properties, targetProject, targetPackage, fileName, templateContent));
        }
        return list;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.singleMode = properties.getProperty("singleMode", "true");
        if (!"TRUE".equalsIgnoreCase(singleMode)) {
            this.cacheTables = new LinkedHashSet<TableClass>();
        }
        this.targetProject = properties.getProperty("targetProject");
        this.targetPackage = properties.getProperty("targetPackage");
        this.templatePath = properties.getProperty("templatePath");
        this.fileName = properties.getProperty("fileName");
        this.templateFormatterClass = properties.getProperty("templateFormatter");
    }
}
