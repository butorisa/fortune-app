package com.fortune.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

class FortuneResultModel : RealmObject() {
    @PrimaryKey
    var horoscope: String? = null
    @Required
    var rank: String? = null
}