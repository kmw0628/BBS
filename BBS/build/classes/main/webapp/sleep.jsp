<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <title>Random Sleep</title>
</head>
<body>
    <%
        int mind = 1;
        int maxd = 8000;
        String mindParam = request.getParameter("mind");
        String maxdParam = request.getParameter("maxd");
        if (maxdParam != null) {
            try {
                maxd = Integer.parseInt(maxdParam);
                if (maxd < mind) {
                    maxd = mind;
                }
            } catch (NumberFormatException e) {
            }
        }
        if (mindParam != null) {
            try {
                mind = Integer.parseInt(mindParam);
                if (maxd < mind) {
                    mind = maxd;
                }
            } catch (NumberFormatException e) {
            }
        }


        int randomDelay = mind + (int) (Math.random() * (maxd - mind + 1));

        try {
            Thread.sleep(randomDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    %>

    <h1>sleep Test</h1>
    <p>elTime: <%= randomDelay %> ms</p>
    <p>paramList : mind, maxd</p>
    <p>result:success</p>
</body>
</html>
