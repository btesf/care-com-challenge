package care.com.challenge.dto

class OpenWeatherData {

    Integer statusCode
    String statusMessage
    String weatherMain
    String weatherDescription
    String weatherIcon
    Double temprature
    Double tempMin
    Double tempMax
    String windSpeed
    String windDegree

    /*
     Full url for an image source is formed from icon information
     */
    void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = "http://openweathermap.org/img/w/${weatherIcon}.png"
    }
}
