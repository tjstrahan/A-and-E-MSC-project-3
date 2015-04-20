package jdbc;

import java.sql.SQLException;
import java.text.ParseException;

public class AdHocJDBCTest {

	public static void main(String[] args) {

		
		ReceptionistAccess ra = new ReceptionistAccess();
		GeneralAccess gA = new GeneralAccess();
		DoctorAccess doc = new DoctorAccess();
		HospitalManagerAccess hMa = new HospitalManagerAccess();
		int NHSnumber;
		int staffID;
		
		try {
			
			NHSnumber = ReceptionistAccess.lookUpPatientNHSNumber("Alice", "Hanna", "1945-06-05");
			ra.updateFirstLineOfAddress(NHSnumber, "The Manse");
			ra.updateSecondLineOfAddress(NHSnumber, "North Road");
			ra.updateThirdLineOfAddress(NHSnumber, "Milebush");
			ra.updateCity(NHSnumber, "Carrickfergus");
			ra.updatePostcode(NHSnumber, "BT38 6ST");
			ra.updateContactNumber(NHSnumber, 447557648697L);
			ra.addFirstAllergies(NHSnumber, "Penicillin");
			ra.addMoreAllergies(NHSnumber, "Ciproxin");
			ra.addFirstKnownConditions(NHSnumber, "Heart disease");
			ra.addMoreKnownConditions(NHSnumber, "Epilepsy");
			ra.updateNextOfKin(NHSnumber, "Johnny Vegas");
			gA.displayPatientByNHSNumber(111121111);
			
			staffID = 700300;
			hMa.setOnCall(1, staffID);
			
			Thread timeClear = new Thread(new QueueAccessClearDischargeTime(111121111));
			timeClear.start();

			Thread notesClear = new Thread(new QueueAccessClearDischargeNotes(111121111));
			notesClear.start();

			Thread.sleep(200);
			
			gA.displayPatientByNHSNumber(111121111);
			
			Thread setTime = new Thread(new QueueAccessAddDischargeTime(111121111));
			setTime.start();
		
			doc.writeNotesOnPatientRecord(111121111, "Compound fracture of tibula and fibula. Could require physio and OT follow-up");
			gA.displayPatientByNHSNumber(111121111);
			doc.displayPatientByNHSNumber(111121111);
		} catch (SQLException | ParseException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
