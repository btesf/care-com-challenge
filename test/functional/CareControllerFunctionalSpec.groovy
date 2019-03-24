import geb.spock.GebReportingSpec;

class CareControllerFunctionalSpec extends GebReportingSpec {

    def "test index"(){

        when: "load ..."
        go "care/index"

        then: "ssss"
        $("#first h2").text() ==
                "Weather info"
    }

}