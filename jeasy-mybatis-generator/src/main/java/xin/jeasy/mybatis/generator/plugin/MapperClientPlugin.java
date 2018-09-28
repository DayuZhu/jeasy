package xin.jeasy.mybatis.generator.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class MapperClientPlugin extends PluginAdapter {
    private static final String jlInteger = "java.lang.Integer";
    private static final String juList = "java.util.List";
    private static final String apacheParm = "org.apache.ibatis.annotations.Param";

    private String baseInterface;
    private int genericsNum = 2;

    @Override
    public boolean validate(List<String> warnings) {
        this.baseInterface = this.properties.getProperty("baseInterface");
        this.genericsNum = Integer.parseInt(this.properties.getProperty("genericsNum"));
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {
        Set<FullyQualifiedJavaType> set = interfaze.getSuperInterfaceTypes();
        String domainName = interfaze.getType().getShortName().replaceAll("(.*)Mapper", "$1");

        String superName = null;
        if (this.genericsNum == 0) {
            superName = this.baseInterface;
        } else if (this.genericsNum == 1) {
            superName = this.baseInterface.substring(this.baseInterface.lastIndexOf(".") + 1) + "<" + domainName + ">";
        } else {
            String type = introspectedTable.getExampleType();
            superName = this.baseInterface.substring(this.baseInterface.lastIndexOf(".") + 1) + "<" + domainName + ","
                    + type.substring(type.lastIndexOf(".") + 1) + ">";
        }
        set.add(new FullyQualifiedJavaType(superName));

        if (!isSamePackage(interfaze.getType().getFullyQualifiedName(), this.baseInterface)) {
            interfaze.addImportedType(new FullyQualifiedJavaType(this.baseInterface));
        }
        List<Method> list = new ArrayList<>();
        interfaze.getMethods().forEach((m) -> {
            list.add(m);
        });

        Set<FullyQualifiedJavaType> iTypes = interfaze.getImportedTypes();
        Iterator<FullyQualifiedJavaType> it = iTypes.iterator();
        Map<String, FullyQualifiedJavaType> map = new HashMap<>();
        //
        while (it.hasNext()) {
            FullyQualifiedJavaType jt = it.next();
            if (jlInteger.equals(jt.getFullyQualifiedName()) || juList.equals(jt.getFullyQualifiedName())
                    || apacheParm.equals(jt.getFullyQualifiedName())) {
                map.put(jt.getFullyQualifiedName(), jt);
                it.remove();
            }
        }

        interfaze.getMethods().clear();
        int k = list.size();
        for (int i = 0; i < k; i++) {
            if (!list.get(i).getName().contains("WithBLOBs")) {
                continue;
            }
            for (String str : list.get(i).getReturnType().getImportList()) {
                if (juList.equals(str)) {
                    iTypes.add(map.get(juList));
                }
            }

            for (Parameter par : list.get(i).getParameters()) {
                if (par.getAnnotations().contains("@Param(\"record\")")) {
                    iTypes.add(map.get(apacheParm));
                }
            }
            interfaze.getMethods().add(list.get(i));
        }

        return true;
    }

    private boolean isSamePackage(String name1, String name2) {
        String package1 = name1.substring(0, name1.lastIndexOf("."));
        String package2 = name2.substring(0, name2.lastIndexOf("."));

        return package1.equals(package2);
    }
}