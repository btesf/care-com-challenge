package care.com.challenge

class TemperatureRecord {

    String cityName
    Double temperature
    Date date

    static mapping = {
        id generator: 'identity'
    }

    static constraints = {
    }
}
