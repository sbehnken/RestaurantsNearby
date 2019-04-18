package com.sbehnken.restaurantsnearby.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timezone {

    @SerializedName("timezone_identifier")
    @Expose
    private String timezoneIdentifier;
    @SerializedName("timezone_abbr")
    @Expose
    private String timezoneAbbr;
    @SerializedName("utc_offset_sec")
    @Expose
    private Integer utcOffsetSec;
    @SerializedName("is_dst")
    @Expose
    private String isDst;

    public String getTimezoneIdentifier() {
        return timezoneIdentifier;
    }

    public void setTimezoneIdentifier(String timezoneIdentifier) {
        this.timezoneIdentifier = timezoneIdentifier;
    }

    public String getTimezoneAbbr() {
        return timezoneAbbr;
    }

    public void setTimezoneAbbr(String timezoneAbbr) {
        this.timezoneAbbr = timezoneAbbr;
    }

    public Integer getUtcOffsetSec() {
        return utcOffsetSec;
    }

    public void setUtcOffsetSec(Integer utcOffsetSec) {
        this.utcOffsetSec = utcOffsetSec;
    }

    public String getIsDst() {
        return isDst;
    }

    public void setIsDst(String isDst) {
        this.isDst = isDst;
    }

}
