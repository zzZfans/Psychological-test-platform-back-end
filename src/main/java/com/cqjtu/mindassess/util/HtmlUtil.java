package com.cqjtu.mindassess.util;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.*;

public class HtmlUtil extends HTMLEditorKit.ParserCallback {

    private static HtmlUtil html2Text = new HtmlUtil();
    StringBuffer s;
    public HtmlUtil() {
    }

    /**
     * 获取富文本内容
     * @param str
     * @return
     */
    public static String getContent(String str) {
        try {
            html2Text.parse(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html2Text.getText();
    }


    public void parse(String str) throws IOException {
        InputStream iin = new ByteArrayInputStream(str.getBytes());
        Reader in = new InputStreamReader(iin);
        s = new StringBuffer();
        ParserDelegator delegator = new ParserDelegator();
        delegator.parse(in, this, Boolean.TRUE);
        iin.close();
        in.close();
    }

    @Override
    public void handleText(char[] text, int pos) {
        s.append(text);
    }

    public String getText() {
        return s.toString();
    }

}