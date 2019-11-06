package zkt.mybatis.generator.junit;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * 功能描述:
 *
 * @className:UnmergeableXmlMappersPlugin
 * @projectName:jeasy01
 * @author:Dayu
 * @date: 2019/11/1 11:21
 */
public class UnmergeableXmlMappersPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap,
                                   IntrospectedTable introspectedTable) {
        sqlMap.setMergeable(false);
        return true;
    }
}