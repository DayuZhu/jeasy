package xin.jeasy.mybatis.generator.util;

import org.mybatis.generator.internal.ObjectFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

/**
 * 功能描述:
 *
 * @className:CommonUtils
 * @projectName:jeasy01
 * @author:Dayu
 * @date: 2019/11/7 15:08
 */
public class CommonUtility {

    private CommonUtility() {
        throw new IllegalStateException("CommonUtility class");
    }


    /**
     * 获取模板内容
     *
     * @param templatePath 模板地址
     * @return
     * @throws IOException
     */
    public static String getTemplateContent(String templatePath) throws IOException {

        URL resourceUrl = ObjectFactory.getResource(templatePath);
        if (resourceUrl == null) {
            resourceUrl = new URL(templatePath);
        }
        InputStream inputStream = resourceUrl.openConnection().getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = reader.readLine();

        while (line != null) {
            stringBuilder.append(line).append("\n");
            line = reader.readLine();
        }
        return stringBuilder.toString();

    }
}
