package org.TheEmperorPR.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class ShowsSearchResponse(
        @JsonProperty("hits")
        val hits: List<Hit>
) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        data class Hit(
                @JsonProperty("TITLE")
                var title: String,

                @JsonProperty("ID")
                var id: String,

                @JsonProperty("POSTER_URL")
                var posterUrl: String,

                @JsonProperty("RDATE")
                var rdate: String? = null,

                @JsonProperty("TYPE_NAME")
                var typeName: String? = null,

                @JsonProperty("GROUP_TITLE")
                var groupTitle: String? = null,

                @JsonProperty("REGION")
                var region: String? = null,

                @JsonProperty("IS_STREAM")
                var isStream: Boolean = false,

                @JsonProperty("IS_TREND")
                var isTrend: Boolean = false,

                @JsonProperty("IS_ONLINE")
                var isOnline: Boolean = false,

                @JsonProperty("CODE")
                var code: String? = null
        )
}
