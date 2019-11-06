package xin.jeasy.mybatis.generator.model;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

import java.util.List;
import java.util.Objects;

/**
 * @projectName:jeasy01
 * @author:
 * @date:
 */
public class TableClass {

    private IntrospectedTable introspectedTable;

    private String tableName;
    private String tableComment;
    private String variableName;
    private String lowerCaseName;
    private String shortClassName;
    private String fullClassName;
    private String packageName;
    private FullyQualifiedJavaType type;

    private List<ColumnField> pkFields;
    private List<ColumnField> baseFields;
    private List<ColumnField> blobFields;
    private List<ColumnField> allFields;

    public List<ColumnField> getAllFields() {
        return allFields;
    }

    public void setAllFields(List<ColumnField> allFields) {
        this.allFields = allFields;
    }

    public List<ColumnField> getBaseFields() {
        return baseFields;
    }

    public void setBaseFields(List<ColumnField> baseFields) {
        this.baseFields = baseFields;
    }

    public List<ColumnField> getBlobFields() {
        return blobFields;
    }

    public void setBlobFields(List<ColumnField> blobFields) {
        this.blobFields = blobFields;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public IntrospectedTable getIntrospectedTable() {
        return introspectedTable;
    }

    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        this.introspectedTable = introspectedTable;
    }

    public String getLowerCaseName() {
        return lowerCaseName;
    }

    public void setLowerCaseName(String lowerCaseName) {
        this.lowerCaseName = lowerCaseName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<ColumnField> getPkFields() {
        return pkFields;
    }

    public void setPkFields(List<ColumnField> pkFields) {
        this.pkFields = pkFields;
    }

    public String getShortClassName() {
        return shortClassName;
    }

    public void setShortClassName(String shortClassName) {
        this.shortClassName = shortClassName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public FullyQualifiedJavaType getType() {
        return type;
    }

    public void setType(FullyQualifiedJavaType type) {
        this.type = type;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableClass that = (TableClass) o;
        return Objects.equals(introspectedTable, that.introspectedTable) &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(tableComment, that.tableComment) &&
                Objects.equals(variableName, that.variableName) &&
                Objects.equals(lowerCaseName, that.lowerCaseName) &&
                Objects.equals(shortClassName, that.shortClassName) &&
                Objects.equals(fullClassName, that.fullClassName) &&
                Objects.equals(packageName, that.packageName) &&
                Objects.equals(type, that.type) &&
                Objects.equals(pkFields, that.pkFields) &&
                Objects.equals(baseFields, that.baseFields) &&
                Objects.equals(blobFields, that.blobFields) &&
                Objects.equals(allFields, that.allFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(introspectedTable, tableName, tableComment, variableName, lowerCaseName, shortClassName, fullClassName, packageName, type, pkFields, baseFields, blobFields, allFields);
    }
}
