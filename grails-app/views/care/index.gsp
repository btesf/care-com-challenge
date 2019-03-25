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
            <span class="header1"> Current Weather </span>
        </div>
    </div>
    <div class="row">
        <div class="top-margined-wrapper">
            <div class="six columns center">
                <div class="weather_wrapper" id="berlin_weather_widget">
                    <div class="emptyCard">
                        <!-- empty content at the beginning -->
                    </div>
                </div>
                <br/>
                <div>
                    <button class="button-primary" id="berlinWeatherRefreshButton" value="Berlin">Refresh</button>
                </div>
            </div>
            <div class="six columns center">
                <div class="weather_wrapper" id="waltham_weather_widget">
                    <div class="emptyCard">
                        <!-- empty content at the beginning -->
                    </div>
                </div>
                <br/>
                <div>
                    <button class="button-primary" id="walthamWeatherRefreshButton" value="Waltham">Refresh</button>
                </div>
            </div>
        </div>
    </div>
    <form id="cityWeatherSearchForm">
        <div class="row">
            <div class="top-margined-wrapper">
                <div class="one columns">&nbsp;</div>
                <div class="ten columns center">
                    <div class="row">
                        <div class="twelve column">
                            <input type="text" placeholder="City name (e.g. London)" id="cityNameInput" style="width: 60%">
                            <button type="submit" class="button-primary">Go</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="twelve column center">
                            <br/>
                            <div class="weather_wrapper" id="other_weather_widget">
                                <div class="emptyCard">
                                    <!-- empty content at the beginning -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="footer">
                        <span id="highest_temperature_span"></span>
                    </div>
                </div>
                <div class="one columns">&nbsp;</div>
            </div>
        </div>
    </form>
</body>
</html>