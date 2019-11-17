package com.sc.act.api.commons.web.base;

import com.sc.act.api.commons.web.constant.CommonConstant;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @ClassName BaseController
 * @Description 特殊通用的Controller方法都在这里实现
 */
@Validated
public class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    @InitBinder
    protected void initBinder(WebDataBinder binder, HttpServletRequest request, HttpServletResponse response) {
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            public void setAsText(String text) {
                this.setValue(text == null ? null : HtmlUtils.htmlEscape(text.trim(), CommonConstant.ENCODING_UTF8));
            }

            public String getAsText() {
                Object value = this.getValue();
                return value != null ? value.toString() : CommonConstant.STRING_EMPTY;
            }
        });

        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String text) {
                try {
                    this.setValue(DateUtils.parseDate(text, "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"));
                } catch (Exception e) {
                    LOG.error("initBinder:Date日期转换失败", e);
                }
            }
        });
    }
}
