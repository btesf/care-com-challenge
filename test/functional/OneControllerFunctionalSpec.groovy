import geb.Page
import geb.spock.GebReportingSpec

class OneControllerFunctionalSpec extends GebReportingSpec {

    def "test oneController index"(){

        given: "Go to careChallenge index page ..."
        to CareChallengeIndexPage

        when: "Make weather update request"
        makeRequest()

        then:
        berlinWeatherResultSpan.text().contains("°");
        walthamWeatherResultSpan.text().contains("°");
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
        berlinWeatherResultSpan { $("#berlinTempSpan") }
        //Berlin weather refresh button
        walthamWeatherRefreshButton { $("#walthamWeatherRefreshButton") }
        walthamWeatherResultSpan { $("#walthamTempSpan") }
    }

    def makeRequest() {
        //check for Berlin weather
        berlinWeatherRefreshButton.click()
        waitFor (30) { berlinWeatherResultSpan.text()!= DEFAULT_SPAN_CONTENT }
        //check for Walthum weather
        walthamWeatherRefreshButton.click()
        waitFor (30) { walthamWeatherResultSpan.text()!= DEFAULT_SPAN_CONTENT }
    }
}