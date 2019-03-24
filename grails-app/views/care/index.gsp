<%--
  Created by IntelliJ IDEA.
  User: bereket
  Date: 23/3/19
  Time: 3:52 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="care"/>
    <title>Care Challenge Home</title>

</head>
<body>
    <div class="row">
        <div class="twelve columns center">
            <h2> Weather info </h2>
        </div>
    </div>
    <div class="row">
        <div class="six columns">
            Berlin:
            Temprature: <span id="berlinTempSpan"></span>
            <div>
                <button class="button-primary" id="berlinWeatherRefreshButton" value="Berlin">Refresh</button>
            </div>
        </div>
        <div class="six columns">
            Waltham
            Temprature: <span id="walthamTempSpan"></span>
            <div>
                <button class="button-primary" id="walthamWeatherRefreshButton" value="Waltham">Refresh</button>
            </div>
        </div>
    </div>
    <form id="cityWeatherSearchForm">
        <div class="row">
            <div class="six columns">
                <label for="cityNameInput">City</label>
                    <input class="u-full-width" type="text" placeholder="City name (e.g. London)" id="cityNameInput">
                <button type="submit" class="button-primary">Go</button>
            </div>
            <div class="six columns">
                <h3>Search result</h3>
                <span id="otherCitySpan"></span>
            </div>
        </div>
    </form>
    <script type="text/javascript">

    </script>
</body>
</html>