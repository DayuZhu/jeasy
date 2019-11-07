package xin.jeasy.mybatis.generator.file;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import xin.jeasy.mybatis.generator.format.TemplateMultiFormatter;
import xin.jeasy.mybatis.generator.model.TableClass;

import java.util.Properties;

/**
 * @projectName:jeasy01
 * @author:
 * @date:
 */
public class GenerateByMultiTemplateFile extends GeneratedJavaFile {


    public static final String ENCODING = "UTF-8";

    private String targetPackage;

    private String fileName;

    private String templateContent;

    private Properties properties;

    private TableClass tableClass;

    private TemplateMultiFormatter templateFormatter;

    private String mapperSuffix;

    public GenerateByMultiTemplateFile(TableClass tableClass, TemplateMultiFormatter templateFormatter, Properties properties,
                                       String targetProject, String targetPackage, String fileName, String templateContent, String mapperSuffix) {
        super(null, targetProject, ENCODING, null);
        this.targetProject = targetProject;
        this.targetPackage = targetPackage;
        this.fileName = fileName;
        this.templateContent = templateContent;
        this.properties = properties;
        this.tableClass = tableClass;
        this.templateFormatter = templateFormatter;
        this.mapperSuffix = mapperSuffix;
    }

    @Override
    public CompilationUnit getCompilationUnit() {
        return null;
    }

    @Override
    public String getFileName() {
        return templateFormatter.getFormattedContent(tableClass, properties, targetPackage, fileName, mapperSuffix);
    }

    @Override
    public String getFormattedContent() {
        return templateFormatter.getFormattedContent(tableClass, properties, targetPackage, templateContent, mapperSuffix);
    }

    @Override
    public String getTargetPackage() {
        return templateFormatter.getFormattedContent(tableClass, properties, targetPackage, targetPackage, mapperSuffix);
    }

    @Override
    public boolean isMergeable() {
        return false;
    }

}
