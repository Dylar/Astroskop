package de.bornholdtlee.snh.enums;


import de.bornholdtlee.snh.R;
import lombok.Getter;

@Getter
public enum ReportLocation {
    SINGLEHOUSE(R.string.fragment_report_form_location_singlehouse, "Einzelhaus"),
    MULTIHOUSE(R.string.fragment_report_form_location_multihouse, "Mehrfamilienhaus"),
    REST(R.string.fragment_report_form_location_rest, "Sonstiger Ort");

    private final int textId;
    private final String jsonString;

    ReportLocation(int textId, String jsonString) {
        this.textId = textId;
        this.jsonString = jsonString;
    }
}
