
package com.jeffwc.whistlenl.model.track;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TracksResult {

    @SerializedName("results")
    @Expose
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

}
