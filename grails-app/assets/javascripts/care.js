(function() {
    $(document).ready(function () {

        $("button#berlinWeatherRefreshButton").click(function () {
            $button = $(this);
            //keep the original button text to replace it later
            var buttonOriginalText = $button.text();

            var onSuccessCallBack = function(data){
                $('#berlinTempSpan').text(data.temprature + '°');
                $button.text(buttonOriginalText);
            };

            var onFailCallBack = function(){
                $('#berlinTempSpan').text('');
                $button.text(buttonOriginalText);
            };

            $button.text("Loading ...");
            requestWeatherInfo($button.val(), onSuccessCallBack, onFailCallBack);
        });

        $("button#walthamWeatherRefreshButton").click(function () {
            $button = $(this);
            //keep the original button text to replace it later
            var buttonOriginalText = $button.text();

            var onSuccessCallBack = function(data){
                $('#walthamTempSpan').text(data.temprature + '°');
                $button.text(buttonOriginalText);
            };

            var onFailCallBack = function(){
                $('#walthamTempSpan').text('');
                $button.text(buttonOriginalText);
            };

            $button.text("Loading ...");
            requestWeatherInfo($button.val(), onSuccessCallBack, onFailCallBack);
        });

        $("#cityWeatherSearchForm").submit(function () {
            $form = $(this);
            $searchInput = $form.find(":text");

            var onSuccessCallBack = function(data){
                $('#otherCitySpan').text(data.temprature + '°');
            };

            var onFailCallBack = function(){
                $('#otherCitySpan').text('');
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

        //fetch current temprature on load
        $("button#berlinWeatherRefreshButton").trigger('click')
        $("button#walthamWeatherRefreshButton").trigger('click')
    });
})();