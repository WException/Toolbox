package com.Niss.Toolbox_Service.utils;

public class StaticUtils {

    /*
     * 图片要存放的路径，一般为服务器(Tomcat)下的某个文件夹
     * */
    public static final String IMGPATH = "C:\\Program Files\\Java\\apache-tomcat-8.5.68\\webapps\\Toolbox_Img\\";
//    public static final String IMGPATH = "/www/server/tomcat/webapps/WYQC_Service/Toolbox_img/";

    /*
     * 返回给用户的图片路径的前缀
     * */
    public static final String RETURNIMGPATH = "http://localhost:8080/Toolbox_Img/";
//    public static final String RETURNIMGPATH = "https://zhihuilvyou.top/Toolbox_img/";


    /*
     * 代码粘贴模板
     * */
    public static final String HTML = "<!DOCTYPE html>\n" +
            "<html lang=\"\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Code</title>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div>\n" +
            "        <textarea id=\"code\"></textarea>\n" +
            "    </div>\n" +
            "    <script src=\"https://zhihuilvyou.top/Toolbox_img/static/codemirror.js\"></script>\n" +
            "    <script src=\"https://zhihuilvyou.top/Toolbox_img/static/css.js\"></script>\n" +
            "    <script src=\"https://zhihuilvyou.top/Toolbox_img/static/javascript.js\"></script>\n" +
            "    <script src=\"https://zhihuilvyou.top/Toolbox_img/static/python.js\"></script>\n" +
            "    <script src=\"https://zhihuilvyou.top/Toolbox_img/static/xml.js\"></script>\n" +
            "    <link rel=\"stylesheet\" href=\"https://zhihuilvyou.top/Toolbox_img/static/codemirror.css\">\n" +
            "    <link rel=\"stylesheet\" href=\"https://zhihuilvyou.top/Toolbox_img/static/idea.css\">\n" +
            "    <script>\n" +
            "        var config = {\n" +
            "            lineNumbers: true, //显示行号\n" +
            "            indentUnit: 4, //缩进单位为4\n" +
            "            styleActiveLine: true, //当前行背景高亮\n" +
            "            matchBrackets: true, //括号匹配\n" +
            "            lineWrapping: true, //自动换行\n" +
            "            theme: 'idea', //编辑器主题\n" +
            "        }\n" +
            "        config[\"mode\"] = \"text/x-python\"\n" +
            "        var editor = CodeMirror.fromTextArea(document.getElementById(\"code\"), config);\n" +
            "        editor.setSize(\"50vw\",\"80vh\")\n" +
            "        editor.setValue(\"import\")\n" +
            "    </script>\n" +
            "</body>\n" +
            "\n" +
            "</html>";

    public static final String HTML1 = "<!DOCTYPE html>\n" +
            "<html lang=\"\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Code</title>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div>\n" +
            "        <textarea id=\"code\"></textarea>\n" +
            "    </div>\n" +
            "    <script src=\"https://zhihuilvyou.top/Toolbox_img/static/codemirror.js\"></script>\n" +
            "    <script src=\"https://zhihuilvyou.top/Toolbox_img/static/css.js\"></script>\n" +
            "    <script src=\"https://zhihuilvyou.top/Toolbox_img/static/javascript.js\"></script>\n" +
            "    <script src=\"https://zhihuilvyou.top/Toolbox_img/static/python.js\"></script>\n" +
            "    <script src=\"https://zhihuilvyou.top/Toolbox_img/static/xml.js\"></script>\n" +
            "    <link rel=\"stylesheet\" href=\"https://zhihuilvyou.top/Toolbox_img/static/codemirror.css\">\n" +
            "    <link rel=\"stylesheet\" href=\"https://zhihuilvyou.top/Toolbox_img/static/idea.css\">\n" +
            "    <script>\n" +
            "        var config = {\n" +
            "            lineNumbers: true, //显示行号\n" +
            "            indentUnit: 4, //缩进单位为4\n" +
            "            styleActiveLine: true, //当前行背景高亮\n" +
            "            matchBrackets: true, //括号匹配\n" +
            "            lineWrapping: true, //自动换行\n" +
            "            theme: 'idea', //编辑器主题\n" +
            "        }\n" +
            "        config[\"mode\"] = \"";
    public static final String HTML2 = "\"\n" +
            "        var editor = CodeMirror.fromTextArea(document.getElementById(\"code\"), config);\n" +
            "        editor.setSize(\"50vw\",\"80vh\")\n" +
            "        editor.setValue(`";
    public static final String HTML3 = "`)\n" +
            "    </script>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
}
