import geb.Page
import geb.spock.GebReportingSpec

class OneControllerFunctionalSpec extends GebReportingSpec {

    def "test oneController index"(){

        given: "Go to careChallenge index page ..."
            to CareChallengeIndexPage

        when: "Make weather update request"
            makeRequest()

        then: "Temperature value with degree (&deg;) character will appear"
            berlinWeatherResultDiv.text().contains("°");
            walthamWeatherResultDiv.text().contains("°");
            otherCityWeatherResultDiv.text().contains("°");
    }
}

class CareChallengeIndexPage extends Page{

    private  static final INITIAL_WIDGET_CONTENT = ''

    static url = "care/index"

    static at = {
        title=="Care Challenge Home"
    }

    static content = {
        //Berlin weather refresh button result widget
        berlinWeatherRefreshButton { $("#berlinWeatherRefreshButton") }
        berlinWeatherResultDiv { $("#berlin_weather_widget") }
        //waltham weather refresh button and result widget
        walthamWeatherRefreshButton { $("#walthamWeatherRefreshButton") }
        walthamWeatherResultDiv { $("#waltham_weather_widget") }
        //Other city search field and result widget
        cityField { $("input", id: "cityNameInput") }
        formSubmitButton { $("#cityWeatherSearchForm").children().find("button") }
        otherCityWeatherResultDiv { $("#other_weather_widget") }
    }

    def makeRequest() {
        //check for Berlin weather
        berlinWeatherRefreshButton.click()
        waitFor (10) { berlinWeatherResultDiv.text()!= INITIAL_WIDGET_CONTENT }
        //check for Walthum weather
        walthamWeatherRefreshButton.click()
        waitFor (10) { walthamWeatherResultDiv.text()!= INITIAL_WIDGET_CONTENT }
        //check for search city weather form submission
        cityField.value("Addis Ababa")
        formSubmitButton.click()
        waitFor (10) { otherCityWeatherResultDiv.text()!= INITIAL_WIDGET_CONTENT }
    }
}