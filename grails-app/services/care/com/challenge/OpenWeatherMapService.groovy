package care.com.challenge

import care.com.challenge.dto.OpenWeatherData
import grails.transaction.Transactional
import wslite.json.JSONObject
import wslite.rest.ContentType
import wslite.rest.RESTClient
import wslite.rest.RESTClientException
import wslite.rest.Response

class OpenWeatherMapServiceException extends RuntimeException{

    OpenWeatherMapServiceException(String message) {
        super(message)
    }
}

@Transactional
class OpenWeatherMapService {

    def grailsApplication
    private final String BASE_PATH = "http://api.openweathermap.org/data/2.5/"

    OpenWeatherData getWeatherInfoForCity(String cityName){

        JSONObject jsonObject = makeWebServiceCall(cityName)

        if(jsonObject == null){

            throw new OpenWeatherMapServiceException("Empty weather information retrieved")
        }

        OpenWeatherData openWeatherData = convertJsonResponseToResponseObject(jsonObject)

        return openWeatherData
    }

    private JSONObject makeWebServiceCall(String cityName) {

        RESTClient client = new RESTClient(BASE_PATH)
        Response response = null
        String apiKey = grailsApplication.config.openWeatherMapAPIKey.toString();

        try {

            response = client.get(path: "/weather?APPID=${apiKey}&q=${cityName}&units=metric",
                    accept: ContentType.JSON,
                    headers: [:],
                    connectTimeout: 5000,
                    readTimeout: 10000,
                    followRedirects: false,
                    useCaches: false,
                    sslTrustAllCerts: true)

        } catch (Exception e) {

            String errorMessage

            if(e instanceof RESTClientException){//indicates 404 error

                errorMessage = "City is not found"

            } else {

                errorMessage = e.message
            }

            log.error(errorMessage, e)
            throw new OpenWeatherMapServiceException(errorMessage);
        }

        if (response != null) {

            if (response.statusCode != 200) {

                throw new OpenWeatherMapServiceException("Failed to get expected response from OpenWeatherMap.org")
            }

            return response.json

        } else {

            throw new OpenWeatherMapServiceException("Empty response for request.")
        }
    }

    private OpenWeatherData convertJsonResponseToResponseObject(JSONObject jsonObject){

        OpenWeatherData data = new OpenWeatherData();

        data.statusCode = jsonObject.containsKey("cod") ?  jsonObject.getInt("cod") : null;
        data.statusMessage = jsonObject.containsKey("message") ?  jsonObject.get("message") : null;

        if(jsonObject.containsKey("weather")) {

            JSONObject jsonChildObject = (JSONObject) jsonObject.get("weather")

            data.weatherMain = jsonChildObject.containsKey("main") ?  jsonChildObject.get("main") : null;
            data.weatherDescription = jsonChildObject.containsKey("description") ?  jsonChildObject.get("description") : null;
            data.weatherIcon = jsonChildObject.containsKey("icon") ?  jsonChildObject.get("icon") : null;
        }

        if(jsonObject.containsKey("main")) {

            JSONObject jsonChildObject = (JSONObject) jsonObject.get("main")

            data.temprature = jsonChildObject.containsKey("temp") ?  jsonChildObject.get("temp") : null;
            data.tempMin = jsonChildObject.containsKey("temp_min") ?  jsonChildObject.get("temp_min") : null;
            data.tempMax = jsonChildObject.containsKey("temp_max") ?  jsonChildObject.get("temp_max") : null;
        }

        if(jsonObject.containsKey("wind")) {

            JSONObject jsonChildObject = (JSONObject) jsonObject.get("wind")

            data.windSpeed = jsonChildObject.containsKey("speed") ?  jsonChildObject.get("speed") : null;
            data.windDegree = jsonChildObject.containsKey("deg") ?  jsonChildObject.get("deg") : null;
        }

        return data;
    }
}
