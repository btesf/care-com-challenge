package care.com.challenge

import care.com.challenge.dto.OpenWeatherData
import grails.converters.JSON

class CareController {

    def openWeatherMapService
    public static final int STATUS_OK = 200;

    def index() {

        render view: 'index'
    }

    def fetchWeatherDataForCity(String cityName){

        boolean status = false; //execution status
        OpenWeatherData data = null //json response data from service
        String responseMessage;

        try{

            if(!cityName){

                throw new Exception("City name cannot be empty")
            }

            data = openWeatherMapService.getWeatherInfoForCity(cityName)

            if(data.statusCode == STATUS_OK) {

                status = true;

            } else {

                responseMessage = data.statusMessage
            }

        } catch(e){

            log.error(e.message, e);
            responseMessage = e.message
        }

        def responseData = [
                'status'  : status,
                'data'    : data,
                'message' : responseMessage
        ]

        render responseData as JSON
    }
}
