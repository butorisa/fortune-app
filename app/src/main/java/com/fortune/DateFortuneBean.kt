package com.fortune

import com.fasterxml.jackson.annotation.JsonProperty

class DateFortuneBean {
    @JsonProperty("2020/08/28")
    var today: List<FortuneResultBean>? = null
}