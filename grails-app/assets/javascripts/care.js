(function() {
    $(document).ready(function () {

        $("button#berlinWeatherRefreshButton").click(function () {
            var $button = $(this);
            //keep the original button text to replace it later
            var buttonOriginalText = $button.text();
            enableButton($button, false);

            var onSuccessCallBack = function(data){
                createWeatherWidget("berlin_weather_widget", $button.val(), data.temprature, data.windSpeed, data.weatherIcon);
                $button.text(buttonOriginalText);
                enableButton($button, true);
                updateHighestTemperature();
            };

            var onFailCallBack = function(){
                $button.text(buttonOriginalText);
                enableButton($button, true);
            };

            $button.text("Loading ...");
            requestWeatherInfo($button.val(), onSuccessCallBack, onFailCallBack);
        });

        $("button#walthamWeatherRefreshButton").click(function () {
            var $button = $(this);
            //keep the original button text to replace it later
            var buttonOriginalText = $button.text();
            enableButton($button, false);

            var onSuccessCallBack = function(data){
                createWeatherWidget("waltham_weather_widget", $button.val(), data.temprature, data.windSpeed, data.weatherIcon);
                $button.text(buttonOriginalText);
                enableButton($button, true);
                updateHighestTemperature();
            };

            var onFailCallBack = function(){
                $button.text(buttonOriginalText);
                enableButton($button, true);
            };

            $button.text("Loading ...");
            requestWeatherInfo($button.val(), onSuccessCallBack, onFailCallBack);
        });

        $("#cityWeatherSearchForm").submit(function () {
            var $form = $(this);
            var $searchInput = $form.find(":text");
            var $goButton = $form.find("button");
            enableButton($goButton, false);

            var onSuccessCallBack = function(data){
                createWeatherWidget("other_weather_widget", $searchInput.val(), data.temprature, data.windSpeed, data.weatherIcon);
                enableButton($goButton, true);
                updateHighestTemperature();
            };

            var onFailCallBack = function(){
                enableButton($goButton, true);
                $("#other_weather_widget").html("<!-- city not found -->")
            };

            requestWeatherInfo($searchInput.val(), onSuccessCallBack, onFailCallBack);

            return false;
        });

        //trigger form submit on enter key press
        $("#cityNameInput").keydown(function(event){
            var keyCode = (event.keyCode ? event.keyCode : event.which);
            if (keyCode == 13) {
                $('#cityWeatherSearchForm').trigger('submit');
                return false;
            }
        });

        function requestWeatherInfo(cityName, onSuccessCallBack, onFailCallBack){

            $.ajax({
                //url: 'http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=b231606340553d9174136f7f083904b3',
                url: appBaseUrl + 'care/fetchWeatherDataForCity?cityName='+ cityName,
                method: 'GET',
                success: function (data) {
                    if(data.status === true){
                        onSuccessCallBack(data.data);

                    } else {
                        $.notify(data.message, "error");
                        onFailCallBack();
                    }
                },
                error: function(xhr, status, error){
                    var errorMessage = xhr.status + ': ' + xhr.statusText
                    console.log(errorMessage)
                    $.notify('Oops something went wrong. Please try again later. ', "error");
                    onFailCallBack();
                }
            });
        }

        function createWeatherWidget(target, city, temprature, windSpeed, weatherIcon){

            var widgetContent = '<div class="weatherCard">' +
                '                        <div class="currentTemp">' +
                '                            <span class="temp">' + temprature + '&deg;</span>' +
                '                            <span class="location">' + city + '</span>' +
                '                        </div>' +
                '                        <div class="currentWeather">' +
                '                            <span class="conditions"><img src="' + weatherIcon + '" class="img_60_percent_width"/></span>' +
                '                            <div class="info">' +
                '                                <span class="wind">' + windSpeed + ' KPH</span>' +
                '                            </div>' +
                '                        </div>' +
                '                    </div>';

            $("#" + target).html(widgetContent)
        }

        function enableButton($button, isEnabled){

            if(isEnabled){
                $button.removeAttr("disabled");
            } else {
                $button.attr("disabled", true);
            }
        }

        function updateHighestTemperature(){

            $.ajax({
                url: appBaseUrl + 'care/getRecordHighTemperature',
                method: 'GET',
                success: function (data) {
                    var temperatureRecord = data.data
                    if(data.status === true){
                        $("#highest_temperature_span").html("Record high: " + temperatureRecord.temperature + "&deg; in " + temperatureRecord.cityName)
                    }
                },
                error: function(xhr, status, error){
                    var errorMessage = xhr.status + ': ' + xhr.statusText
                    console.log(errorMessage)
                }
            });
        }

        //fetch current temprature on load
        $("button#berlinWeatherRefreshButton").trigger('click')
        $("button#walthamWeatherRefreshButton").trigger('click')
    });
})();