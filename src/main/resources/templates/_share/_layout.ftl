<!DOCTYPE html>
<#macro layout title headsection="" scriptsection="">
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${title}</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <#--<link rel="shortcut icon" type="image/x-icon" href="${ResourceFile.FaviconIcon}"  media="screen"/>-->
    <#include "_style.ftl"/>
    <#include "_script.ftl"/>
    ${headsection}
    <script>
        function detectBrowser() {
            var browser = navigator.appName
            if (navigator.userAgent.indexOf("MSIE") > 0) {
                var b_version = navigator.appVersion
                var version = b_version.split(";");
                var trim_Version = version[1].replace(/[ ]/g, "");
                if ((browser == "Netscape" || browser == "Microsoft Internet Explorer")) {
                    if (trim_Version == 'MSIE8.0' || trim_Version == 'MSIE7.0' || trim_Version == 'MSIE6.0') {
                        alert('请使用IE9.0版本以上进行访问');
                        return;
                    }
                }
            }
        }
        detectBrowser();
    </script>
</head>
<body class="black">
    <#include "_header.ftl"/>
    <#nested/>
    <#include "_footer.ftl"/>
${scriptsection}
<div class="shadow" style="display:none;" ></div>
</body>
</html>
</#macro>