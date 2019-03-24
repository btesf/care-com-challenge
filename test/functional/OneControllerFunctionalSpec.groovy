import geb.Page
import geb.spock.GebReportingSpec

class OneControllerFunctionalSpec extends GebReportingSpec {

    def "test oneController index"(){

        given: "Go to careChallenge index page ..."
        to CareChallengeIndexPage

        when: "Make weather update request"
        makeRequest()

        then:
        berlinWeatherResultDiv.text().contains("°");
        walthamWeatherResultDiv.text().contains("°");
        otherCitySpan.text().contains("°");
    }
}

class CareChallengeIndexPage extends Page{

    private  static final DEFAULT_SPAN_CONTENT = ''

    static url = "care/index"

    static at = {
        title=="Care Challenge Home"
    }

    static content = {
        //Berlin weather refresh button and span
        berlinWeatherRefreshButton { $("#berlinWeatherRefreshButton") }
        berlinWeatherResultDiv { $("#berlin_weather_widget div.weatherCard") }
        berlinWeatherResultDiv { $("#berlin_weather_widget").children().find("div") }
        //Berlin weather refresh button
        walthamWeatherRefreshButton { $("#walthamWeatherRefreshButton") }
        walthamWeatherResultDiv { $("#waltham_weather_widget  div.weatherCard") }
        //city search field
        cityField { $("input", id: "cityNameInput") }
        formSubmitButton { $("#cityWeatherSearchForm").children().find("button") }
        otherCityWeatherResultDiv { $("#other_weather_widget") }
    }

    def makeRequest() {
        //check for Berlin weather
        berlinWeatherRefreshButton.click()
        waitFor (30) { berlinWeatherResultDiv.text()!= DEFAULT_SPAN_CONTENT }
        //check for Walthum weather
        walthamWeatherRefreshButton.click()
        waitFor (30) { walthamWeatherResultDiv.text()!= DEFAULT_SPAN_CONTENT }
        //check for search city weather form submission
        cityField.value("Addis Ababa")
        formSubmitButton.click()
        waitFor (30) { otherCitySpan.text()!= DEFAULT_SPAN_CONTENT }
    }
}