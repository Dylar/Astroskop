package de.bornholdtlee.snh.enums;


import de.bornholdtlee.snh.R;
import lombok.Getter;

@Getter
public enum ReportDetails {
    NO_DETAILS(R.string.fragment_report_form_no_details, "Keine weiteren Details bekannt"),
    WITH_POWER(R.string.fragment_report_form_with_power, "Das Haus ist teilweise ohne Stromversorgung"),
    WITHOUT_POWER(R.string.fragment_report_form_without_power, "Das Haus ist komplett ohne Stromversorgung"),
    MORE_HOUSES(R.string.fragment_report_form_more_houses, "Weitere Häuser in der Straße sind betroffen");

    private final int textId;
    private final String jsonString;

    ReportDetails(int textId, String jsonString) {
        this.textId = textId;
        this.jsonString = jsonString;
    }
}
