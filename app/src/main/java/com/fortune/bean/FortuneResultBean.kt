package com.fortune.bean

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 占いAPIレスポンスBean
 */
class FortuneResultBean {
    @JsonProperty("content")
    var content: String? = null

    @JsonProperty("money")
    var money: String? = null

    @JsonProperty("job")
    var job: String? = null

    @JsonProperty("love")
    var love: String? = null

    @JsonProperty("total")
    var total: String? = null

    @JsonProperty("item")
    var item: String? = null

    @JsonProperty("color")
    var color: String? = null

    @JsonProperty("day")
    var day: String? = null

    @JsonProperty("rank")
    var rank: String? = null

    @JsonProperty("sign")
    var sign: String? = null
}