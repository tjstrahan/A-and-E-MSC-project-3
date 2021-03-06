package jdbc;

import hospital.HospitalManager;
import hospital.SendSMS;

import java.sql.SQLException;
import java.text.ParseException;

public class AdHocJDBCTest {
	

	public static void main(String[] args) {

		
		ReceptionistAccess ra = new ReceptionistAccess();
		GeneralAccess gA = new GeneralAccess();
		DoctorAccess doc = new DoctorAccess();
		HospitalManager hM = new HospitalManager();
		int NHSnumber;
		int staffID;

		try {
			hM.populateMedicalTeam();
			hM.setOnCallTeam();
			//SendSMS.checkSMSCredit();
			//SendSMS.sendSMSOnCallTeam();
			Thread sms1 = new Thread(new SendSMS(1));
			sms1.start();
			Thread sms2 = new Thread(new SendSMS(2));
			sms2.start();

			
			//System.out.println(HospitalManager.onCallContactNumbers);
			
			
			
/*
			NHSnumber = ReceptionistAccess.lookUpPatientNHSNumber("Alice",
					"Hanna", "1945-06-05");
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

			Thread timeClear = new Thread(new QueueAccessClearDischargeTime(
					111121111));
			timeClear.start();

			Thread notesClear = new Thread(new QueueAccessClearDischargeNotes(
					111121111));
			notesClear.start();

			Thread.sleep(200);

			gA.displayPatientByNHSNumber(111121111);

			Thread setTime = new Thread(new QueueAccessAddDischargeTime(
					111121111));
			setTime.start();

			doc.writeNotesOnPatientRecord(111121111,
					"Compound fracture of tibula and fibula. Could require physio and OT follow-up");
			gA.displayPatientByNHSNumber(111121111);
			doc.displayPatientByNHSNumber(111121111);
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
