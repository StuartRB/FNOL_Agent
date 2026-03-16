package com.example.agent.constants;

public class ExtractionConstants {
    public static String EXTRACTION_DATA_ELEMENTS = """
            MANDATORY:
            1. policy_number
            2. date_of_loss 
            3. time_of_loss 
            4. type_of_loss ('auto' or 'property'
            5. reporter_full_name 
            6. reporter_contact_details
            7. insured_vehicle_or_property_details
            8. loss_description 
            
            OPTIONAL: 
            1. fatalities_indicator 
            2. injuries_indicator 
            3. incident_location 
            4. other_vehicle_details
            5. police_report_number
            6. police_department
            """;
}
