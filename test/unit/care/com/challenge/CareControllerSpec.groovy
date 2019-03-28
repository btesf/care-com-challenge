package care.com.challenge

import care.com.challenge.dto.OpenWeatherData
import grails.test.mixin.TestFor
import spock.lang.Specification
import wslite.json.JSONObject

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CareController)
class CareControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    /**
     * if the OpenWeatherData.statusCode is 200, response.json.status will be true and temprature will have some value,
     * Otherwise, response.json.status will be false and we don't expect any temprature value
     */
    void "test fetchWeatherDataForCity (cityName)"() {

        given: "a mocked openWeatherMapService"

        def mockOpenWeatherMapService = mockFor(OpenWeatherMapService)
        mockOpenWeatherMapService.demand.getWeatherInfoForCity { String cityName ->

            return weatherData;
        }

        controller.openWeatherMapService = mockOpenWeatherMapService.createMock()

        when:
        controller.fetchWeatherDataForCity('London')

        then:

            response.json.status == status
            response.json.data.temprature.equals(expectedTemprature)

        where:
        cityName        | status | expectedTemprature | weatherData
        "Berlin"        | true   | 213d               | new OpenWeatherData('statusCode' : 200, 'temprature' : 213d)
        "Unknown City"  | false  | null               | new OpenWeatherData('statusCode' : 404, 'temprature' : null)
    }
}
