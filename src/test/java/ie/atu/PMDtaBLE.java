package ie.atu;

public class PMDtaBLE {
    public static void main(String[] args) {
        PatientManager.setTable_patient_medical_data(2, "rtetertert",
                BloodManager.get_blood_typeID("A+"),
                "NULL", "NULL", "NULL", "NULL");
    }
}
