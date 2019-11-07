package xin.jeasy.mybatis.generator.model;

/**
 * 功能描述:
 *
 * @className:TemplateInfo
 * @projectName:jeasy01
 * @author:Dayu
 * @date: 2019/11/7 16:39
 */
public class TemplateInfo {

    private String templatePath;
    private String mapperSuffix;
    private String fileName;
    private String targetPackage;

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getMapperSuffix() {
        return mapperSuffix;
    }

    public void setMapperSuffix(String mapperSuffix) {
        this.mapperSuffix = mapperSuffix;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }
}
