(function() {
    $(document).ready(function () {

        $("button#berlinWeatherRefreshButton").click(function () {
            var $button = $(this);
            //keep the original button text to replace it later
            var buttonOriginalText = $button.text();
            $button.attr("disabled", true);

            var onSuccessCallBack = function(data){
                createWeatherWidget("berlin_weather_widget", $button.val(), data.temprature, data.windSpeed, data.weatherIcon);
                $button.text(buttonOriginalText);
                $button.removeAttr("disabled");
            };

            var onFailCallBack = function(){
                $button.text(buttonOriginalText);
                $button.removeAttr("disabled");
            };

            $button.text("Loading ...");
            requestWeatherInfo($button.val(), onSuccessCallBack, onFailCallBack);
        });

        $("button#walthamWeatherRefreshButton").click(function () {
            var $button = $(this);
            //keep the original button text to replace it later
            var buttonOriginalText = $button.text();
            $button.attr("disabled", true);

            var onSuccessCallBack = function(data){
                createWeatherWidget("waltham_weather_widget", $button.val(), data.temprature, data.windSpeed, data.weatherIcon);
                $button.text(buttonOriginalText);
                $button.removeAttr("disabled");
            };

            var onFailCallBack = function(){
                $button.text(buttonOriginalText);
                $button.removeAttr("disabled");
            };

            $button.text("Loading ...");
            requestWeatherInfo($button.val(), onSuccessCallBack, onFailCallBack);
        });

        $("#cityWeatherSearchForm").submit(function () {
            var $form = $(this);
            $searchInput = $form.find(":text");

            var onSuccessCallBack = function(data){
                createWeatherWidget("other_weather_widget", $searchInput.val(), data.temprature, data.windSpeed, data.weatherIcon);
            };

            var onFailCallBack = function(){
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
                '                            <span class="conditions"><img src="' + weatherIcon + '"/></span>' +
                '                            <div class="info">' +
                '                                <span class="wind">' + windSpeed + ' KPH</span>' +
                '                            </div>' +
                '                        </div>' +
                '                    </div>';

            $("#" + target).replaceWith(widgetContent)
        }

        //fetch current temprature on load
        $("button#berlinWeatherRefreshButton").trigger('click')
        $("button#walthamWeatherRefreshButton").trigger('click')
    });
})();