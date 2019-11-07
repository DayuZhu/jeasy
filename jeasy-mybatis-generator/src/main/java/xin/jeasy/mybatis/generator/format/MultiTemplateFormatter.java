package xin.jeasy.mybatis.generator.format;

import xin.jeasy.mybatis.generator.model.TableClass;

import java.util.List;
import java.util.Properties;

/**
 * 功能描述:
 *
 * @interface:MultiTemplateFormatter
 * @projectName:jeasy01
 * @author:Dayu
 * @date: 2019/11/7 17:23
 */
public interface MultiTemplateFormatter {

    String getFormattedContent(TableClass tableClass,
                               Properties properties,
                               String targetPackage,
                               List<String> templateContent);
}
