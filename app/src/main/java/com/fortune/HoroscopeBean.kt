package com.fortune

import com.fasterxml.jackson.annotation.JsonProperty

class HoroscopeBean {
    @JsonProperty("horoscope")
    var horoscope: DateFortuneBean? = null
}