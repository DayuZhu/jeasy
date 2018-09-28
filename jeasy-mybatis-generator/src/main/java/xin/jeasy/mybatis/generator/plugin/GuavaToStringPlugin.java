package xin.jeasy.mybatis.generator.plugin;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * 使用 Guava 的 ToStringHelper 生成 toString() 方法
 */
public class GuavaToStringPlugin extends PluginAdapter {

    /**
     * 排除不需要生成 toString() 的属性
     */
    private static final String SERIAL_VERSION_UID = "serialVersionUID";
    private static final List<String> NON_TO_STRING_FIELDS = Collections.singletonList(SERIAL_VERSION_UID);
    private boolean useToStringFromRoot;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.useToStringFromRoot = StringUtility.isTrue(properties.getProperty("useToStringFromRoot"));
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
                                                      IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return true;
    }

    /**
     * 生成 toString() 方法
     */
    private void generateToString(IntrospectedTable introspectedTable, TopLevelClass topLevelClass) {
        topLevelClass.addImportedType("com.google.common.base.MoreObjects");
        // 首先创建一个 Method 对象，注意，这个 Method 是
        // org.mybatis.generator.api.dom.java.Method，
        // 这个 Method 是 MBG 中对对象 DOM 的一个抽象，因为我们要添加方法，所以先创建一个
        Method method = new Method();

        // 设置这个方法的可见性为 public，代码已经在一步一步构建这个方法了
        method.setVisibility(JavaVisibility.PUBLIC);

        // 设置方法的返回类型，这里注意一下的就是，returnType 是一个 FullyQualifiedJavaType
        // 这个 FullyQualifiedJavaType 是 MGB 中对 Java 中的类型的一个 DOM 封装，这个类在整个 MBG
        // 中大量使用
        // FullyQualifiedJavaType 提供了几个静态的方法，比如 getStringInstance()，就直接返回了一个对
        // String 类型的封装
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());

        // 设置方法的名称，至此，方法签名已经装配完成
        method.setName("toString");

        // 判断当前 MBG 运行的环境是否支持 Java5（这里就可以看出来 IntrospectedTable
        // 类的作用了，主要是查询生成环境的作用）
        if (introspectedTable.isJava5Targeted()) {
            // 如果支持 Java5，就在方法上面生成一个 @Override 标签
            method.addAnnotation("@Override");
        }

        // 访问上下文对象（这个 context 对象是在 PluginAdapter 初始化完成后，通过 setContext 方法设置进去的，
        // 通过 getCommentGenerator 方法得到注释生成器，并调用 addGeneralMethodComment
        // 为当前生成的方法添加注释；
        // 因为我们没有提供自己的注释生成器，所以默认的注释生成器只是说明方法是通过 MBG 生成的，对应的是哪个表而已；
        // 这句代码其实非常有意义，通过这句代码，我们基本就可能了解到 MBG 中对于方法注释的生成方式了；
        this.context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        // OK，调用 addBodyLine 开始为方法添加代码了
        // 可以看到，确实，只是简单的把要生成的代码通过 String 拼装到了 method 的 body 中而已；
        // 想到了什么？确实，我想到了 Servlet 的输出方法。MBG 默认的方法体具体的实现，就是像 Servlet 那样通过 String
        // 输出的；
        // 所以，这才会为我们后面准备用 Velocity 来重写 MBG 提供了依据，我们只是给 MBG 添加一个 MVC 的概念；
        method.addBodyLine("return MoreObjects.toStringHelper(this).omitNullValues()");

        StringBuilder builder = new StringBuilder();
        // 通过 topLevelClass 得到当前类的所有字段，注意，这里的 Field 是
        // org.mybatis.generator.api.dom.java.Field
        // 这个 Field 是 MBG 对字段的一个 DOM 封装
        for (Field field : topLevelClass.getFields()) {
            // 重置 StringBuilder
            builder.setLength(0);
            // 得到字段的名称
            String fieldName = field.getName();
            // 添加字段的输出代码
            if (!NON_TO_STRING_FIELDS.contains(fieldName)) {
                builder.append("        .add(\"").append(fieldName).append("\", ").append(fieldName).append(")");
                method.addBodyLine(builder.toString());
            }
        }
        // 如果实体类有父类，则调用父类的 toString() 方法
        if ((this.useToStringFromRoot) && (topLevelClass.getSuperClass() != null)) {
            method.addBodyLine("        .add(\"super class\", super.toString())");
        }
        // 把这个字段的 toString 输出到代码中；所以才看到我们最后生成的代码结果中，每一个字段在 toString 方法中各占一行；
        method.addBodyLine("        .toString();");

        // 把拼装好的方法 DOM 添加到 topLevelClass 中，完成方法添加；
        topLevelClass.addMethod(method);
    }

}