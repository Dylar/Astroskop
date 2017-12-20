package de.bitb.astroskop.model;

import de.bitb.astroskop.enums.ReportDetails;
import de.bitb.astroskop.enums.ReportLocation;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Report {

    private ReportLocation location;
    private String moreDetails = "";
    private String locationDetails;
    private ReportDetails details;

    private String streetName;
    private String cityName;
    private String streetNumber;
    private String plz;

    private String title;
    private String customerName;
    private String customerFamilyName;
    private String company;
    private String phone;
    private String email;
}
